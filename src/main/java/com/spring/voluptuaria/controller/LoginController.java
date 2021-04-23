package com.spring.voluptuaria.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
public class LoginController {
    @Autowired
    HomeController homeController;

     @GetMapping(value ={"/login"})
    public ModelAndView carregaOperacao(){
        ModelAndView mv = new ModelAndView("login");
        return mv;
    }
    @PostMapping(value ="/login")
    public ModelAndView autenticaOperacao(){
        return homeController.preparaOperacao();
    }
}
