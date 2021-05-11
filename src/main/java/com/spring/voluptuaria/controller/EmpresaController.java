package com.spring.voluptuaria.controller;

import com.spring.voluptuaria.model.Empresa;
import com.spring.voluptuaria.model.Empresa;
import com.spring.voluptuaria.model.Funcionario;
import com.spring.voluptuaria.model.TipoEmpresa;
import com.spring.voluptuaria.service.EmpresaService;
import com.spring.voluptuaria.service.FuncionarioService;
import com.spring.voluptuaria.service.TipoEmpresaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Controller
public class EmpresaController {
    @Autowired
    EmpresaService empresaService;
    @Autowired
    TipoEmpresaService tipoEmpresaService;
    @Autowired
    FuncionarioService funcionarioService;

    @GetMapping(value = "/pesquisaEmpresa")
    public ModelAndView preparaPesquisa(){
        ModelAndView mv = new ModelAndView("pesquisaEmpresa");
        mv.addObject("empresas", empresaService.findAll());

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        var roles = authentication.getAuthorities().toString().contains("ROLE_ADMIN");
        mv.addObject("tipo", roles );
        return mv;
    }

    @GetMapping(value = "/manterEmpresa")
    public ModelAndView preparaManter(@RequestParam String operacao,
                                @RequestParam(required = false) Long cod){

            ModelAndView mv;
            mv = new ModelAndView("manterEmpresa");
            mv.addObject("operacao", operacao);
            mv.addObject("tipos",tipoEmpresaService.findAll());

            if (!operacao.equals("Adicionar")) {
                mv.addObject("empresa", empresaService.findById(cod));
            }

        return mv;
    }
    @PostMapping(value = "/manterEmpresa")
    public ModelAndView form(@RequestParam String operacao, Empresa empresa) {

       if(operacao.equals("Excluir")){
           empresaService.delete(empresa);
       }
       else{
           empresa.setTipoEmpresa(tipoEmpresaService.findById(empresa.getIdTipoEmpresa()));
           empresaService.save(empresa);
       }
       return preparaPesquisa();
    }


}
