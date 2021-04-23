package com.spring.voluptuaria.controller;

import com.spring.voluptuaria.model.Empresa;
import com.spring.voluptuaria.model.Passagem;
import com.spring.voluptuaria.model.Passagem;
import com.spring.voluptuaria.service.EmpresaService;
import com.spring.voluptuaria.service.PacoteService;
import com.spring.voluptuaria.service.PassagemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
public class PassagemController {
    @Autowired
    PassagemService passagemService;
    @Autowired
    EmpresaService empresaService;
    @Autowired
    PacoteService pacoteService;

    @GetMapping(value = "/pesquisaPassagem")
    public ModelAndView preparaPesquisa(){
        ModelAndView mv = new ModelAndView("pesquisaPassagem");
        mv.addObject("passagens", passagemService.findAll());
        return mv;
    }

    @GetMapping(value = "/manterPassagem")
    public ModelAndView preparaManter(@RequestParam String operacao,
                                @RequestParam(required = false) Long cod){
        ModelAndView mv;

        mv = new ModelAndView("manterPassagem");
        mv.addObject("operacao", operacao);
        mv.addObject("pacotes", pacoteService.findAll());
        mv.addObject("empresas", empresaService.findAllByTipo("AEREA"));

        if (!operacao.equals("Adicionar")) {
            mv.addObject("passagem", passagemService.findById(cod));
        }

        return mv;
    }

    @PostMapping(value = "/manterPassagem")
    public ModelAndView formulario(@RequestParam String operacao, Passagem passagem) {

        if(operacao.equals("Excluir")){
            passagemService.delete(passagem);
        }
        else{
            passagem.setEmpresa(empresaService.findById(passagem.getIdEmpresa()));
            passagem.setPacote(pacoteService.findById(passagem.getIdPacote()));
            passagemService.save(passagem);
        }

        return preparaPesquisa();
    }

}
