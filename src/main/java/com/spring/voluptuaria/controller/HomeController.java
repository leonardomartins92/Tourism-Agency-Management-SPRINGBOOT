package com.spring.voluptuaria.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @RequestMapping(value = "/homeFuncionario", method = RequestMethod.GET)
    public ModelAndView preparaOperacao(){
        ModelAndView mv = new ModelAndView("homeFuncionario");
        return mv;
    }
}
