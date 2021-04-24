package com.spring.voluptuaria.controller;

import com.spring.voluptuaria.model.Funcionario;
import com.spring.voluptuaria.service.FuncionarioService;
import com.spring.voluptuaria.service.PacoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;
@Controller
public class HomeController {
    @Autowired
    FuncionarioService funcionarioService;
    @Autowired
    PacoteService pacoteService;

    @GetMapping(value = {"/homeFuncionario","/"})
    public ModelAndView preparaOperacao(){
        ModelAndView mv = new ModelAndView("homeFuncionario");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<Funcionario> funcionario = funcionarioService.findFuncionarioByCpf(authentication.getName());
        mv.addObject("nome", funcionario.get().getNome());
        mv.addObject("pacotes",pacoteService.countPacotesByFuncionario(funcionario.get().getId()));

        return mv;
    }
}