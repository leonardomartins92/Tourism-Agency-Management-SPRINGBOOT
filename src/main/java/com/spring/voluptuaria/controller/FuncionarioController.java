package com.spring.voluptuaria.controller;

import com.spring.voluptuaria.model.Funcionario;
import com.spring.voluptuaria.model.Funcionario;
import com.spring.voluptuaria.model.TipoEmpresa;
import com.spring.voluptuaria.model.TipoFuncionario;
import com.spring.voluptuaria.service.FuncionarioService;
import com.spring.voluptuaria.service.TipoEmpresaService;
import com.spring.voluptuaria.service.TipoFuncionarioService;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@Slf4j
public class FuncionarioController {
    @Autowired
    FuncionarioService funcionarioService;
    @Autowired
    TipoFuncionarioService tipoFuncionarioService;
    @Autowired
    TipoEmpresaService tipoEmpresaService;

    @GetMapping(value = "/pesquisaFuncionario")
    public ModelAndView preparaPesquisa(){
        ModelAndView mv = new ModelAndView("pesquisaFuncionario");
        mv.addObject("funcionarios", funcionarioService.findAll());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        mv.addObject("tipo",authentication.getAuthorities().toString().contains("ROLE_ADMIN"));
        return mv;
    }

    @GetMapping(value = "/manterFuncionario")
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
    @PostMapping(value = "/manterFuncionario")
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

    @GetMapping("/primeiroAcesso")
    public ModelAndView configurar(){
        ModelAndView mv = new ModelAndView("primeiroAcesso");

        TipoFuncionario gerente = new TipoFuncionario(1L,"GERENTE");
        TipoFuncionario vendedor = new TipoFuncionario(2L, "VENDEDOR");
        TipoEmpresa aerea = new TipoEmpresa(1L, "AEREA");
        TipoEmpresa acomodacao = new TipoEmpresa(2L, "ACOMODACAO");
        Funcionario funcionario = new Funcionario(1L,"100","ADMIN", "99978", "adm@email",
                "Rua da ADM", "1", "MG", "Juiz de Fora",
                "36090000","123", gerente );
        funcionario.setRoles("ROLE_ADMIN");
        funcionario.setIdTipoFuncionario(1L);
        tipoFuncionarioService.save(gerente);
        tipoFuncionarioService.save(vendedor);
        funcionarioService.save(funcionario);
        tipoEmpresaService.save(acomodacao);
        tipoEmpresaService.save(aerea);
        return mv;

    }
}
