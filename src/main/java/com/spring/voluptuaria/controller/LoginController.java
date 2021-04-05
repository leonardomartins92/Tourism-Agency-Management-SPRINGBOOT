package com.spring.voluptuaria.controller;

import com.spring.voluptuaria.model.Funcionario;
import com.spring.voluptuaria.model.Pacote;
import com.spring.voluptuaria.service.FuncionarioService;
import com.spring.voluptuaria.service.PacoteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
public class LoginController {

    @Autowired
    FuncionarioService funcionarioService;
    @Autowired
    PacoteService pacoteService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView autenticaOperacao(){
        ModelAndView mv = new ModelAndView("login");
        return mv;
    }

    @RequestMapping(value = {"/", "", "homeFuncionario"}, method = RequestMethod.GET)
    public ModelAndView preparaOperacao(){
        ModelAndView mv = new ModelAndView("homeFuncionario");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userCPF = authentication.getName();
        Optional<Funcionario> funcionario = funcionarioService.findFuncionarioByCpf(userCPF);
        mv.addObject("nome", funcionario.get().getNome());
        List<Pacote> pacotes = pacoteService.findPacotesByFuncionario_Id(funcionario.get().getId());
        if (pacotes == null || pacotes.size() == 0){
            mv.addObject("pacotes", 0 );
        }
        else{
            mv.addObject("pacotes", pacotes.size());
        }

        return mv;
    }


}
