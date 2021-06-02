package com.spring.voluptuaria.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@AllArgsConstructor(onConstructor = @__(@Autowired))
@RestController
public class LoginController {

    private final HomeController homeController;

     @GetMapping(value ={"/login"})
    public ModelAndView carregaOperacao(){
         return new ModelAndView("login");

    }
    @PostMapping(value ="/login")
    public ModelAndView autenticaOperacao(){
        return homeController.preparaOperacao();
    }
}
