package com.spring.voluptuaria.controller;

import com.spring.voluptuaria.model.Funcionario;
import com.spring.voluptuaria.model.Funcionario;
import com.spring.voluptuaria.service.FuncionarioService;
import com.spring.voluptuaria.service.TipoFuncionarioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@Slf4j
public class FuncionarioController {
    @Autowired
    FuncionarioService funcionarioService;
    @Autowired
    TipoFuncionarioService tipoFuncionarioService;

    @RequestMapping(value = "/pesquisaFuncionario", method = RequestMethod.GET)
    public ModelAndView preparaPesquisa(){
        ModelAndView mv = new ModelAndView("pesquisaFuncionario");
        List<Funcionario> funcionarios = funcionarioService.findAll();
        mv.addObject("funcionarios", funcionarios);
        return mv;
    }

    @RequestMapping(value = "/manterFuncionario", method = RequestMethod.GET)
    public ModelAndView getView(@RequestParam String operacao,
                                @RequestParam(required = false) Long cod){
        ModelAndView mv;

            mv = new ModelAndView("manterFuncionario");
            mv.addObject("operacao", operacao);
            mv.addObject("tipos", tipoFuncionarioService.findAll());

            if (!operacao.equals("Adicionar")) {
                mv.addObject("funcionario", funcionarioService.findById(cod));
            }

        return mv;
    }
    @RequestMapping(value = "/manterFuncionario", method = RequestMethod.POST)
    public ModelAndView formulario(@RequestParam String operacao, Funcionario funcionario, String novaSenha) {
        ModelAndView mv;

        if(operacao.equals("Excluir")){
            funcionarioService.delete(funcionario);
        }

        else{
            if(funcionario.getIdTipoFuncionario()== 1) {
                funcionario.setRoles("ROLE_ADMIN");
            }
            else{
                funcionario.setRoles("ROLE_USER");
            }
           funcionario.setTipoFuncionario(tipoFuncionarioService.findById(funcionario.getIdTipoFuncionario()));

            if(operacao.equals("Editar")){

                if( funcionario.getSenha() == "" || funcionario.getSenha() == null) {
                    funcionario.setSenha(funcionarioService.findById(funcionario.getId()).getSenha());
                }
                else{
                    if(funcionario.getSenha().equals(funcionarioService.findById(funcionario.getId()).getSenha())) {
                    funcionario.setSenha(novaSenha);
                   }
                    else{
                        mv = getView(operacao, funcionario.getId());
                        mv.addObject("erro",1);
                        return mv;
                    }
                }
            }
            funcionarioService.save(funcionario);
        }
        return  preparaPesquisa();
    }
}
