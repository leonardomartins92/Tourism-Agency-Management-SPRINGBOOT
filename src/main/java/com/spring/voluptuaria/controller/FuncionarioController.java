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

        log.info("operacao:"+operacao);
        log.info("cod:"+cod);

            mv = new ModelAndView("manterFuncionario");
            mv.addObject("operacao", operacao);
            mv.addObject("tipos", tipoFuncionarioService.findAll());

            if (!operacao.equals("Adicionar")) {
                mv.addObject("funcionario", funcionarioService.findById(cod));
            }

        return mv;
    }
    @RequestMapping(value = "/manterFuncionario", method = RequestMethod.POST)
    public ModelAndView formulario(@RequestParam String operacao, Funcionario funcionario) {
        ModelAndView mv;

        if(operacao.equals("Excluir")){
            funcionarioService.delete(funcionario);
        }

        else{
            funcionario.setTipoFuncionario(tipoFuncionarioService.findById(funcionario.getIdTipoFuncionario()));
            if (operacao.equals("Adicionar")){
                funcionarioService.save(funcionario);
            }
            else if(operacao.equals("Editar")){

            }
        }

        return  preparaPesquisa();
    }

}
