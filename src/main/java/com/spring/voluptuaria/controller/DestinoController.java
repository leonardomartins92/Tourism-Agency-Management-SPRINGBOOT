package com.spring.voluptuaria.controller;

import com.spring.voluptuaria.model.Destino;
import com.spring.voluptuaria.model.Empresa;
import com.spring.voluptuaria.service.DestinoService;
import com.spring.voluptuaria.service.EmpresaService;
import com.spring.voluptuaria.service.PacoteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
@Controller
@Slf4j
public class DestinoController {
    @Autowired
    DestinoService destinoService;
    @Autowired
    PacoteService pacoteService;
    @Autowired
    EmpresaService empresaService;

    @GetMapping(value = "/pesquisaDestino")
    public ModelAndView preparaPesquisa(){
        ModelAndView mv = new ModelAndView("pesquisaDestino");
        mv.addObject("destinos", destinoService.findAll());
        return mv;
    }

    @GetMapping(value = "/manterDestino")
    public ModelAndView getView(@RequestParam String operacao,
                                @RequestParam(required = false) Long cod){
        ModelAndView mv;

            mv = new ModelAndView("manterDestino");
            mv.addObject("operacao", operacao);
            mv.addObject("pacotes", pacoteService.findAll());
            mv.addObject("empresas", empresaService.findAllByTipo("ACOMODAÇÃO"));

            if (!operacao.equals("Adicionar")) {
                mv.addObject("destino", destinoService.findById(cod));
            }

        return mv;
    }

    @PostMapping(value = "/manterDestino")
    public ModelAndView formulario(@RequestParam String operacao, Destino destino) {

        if(operacao.equals("Excluir")){
            destinoService.delete(destino);
        }
        else{
            destino.setEmpresa(empresaService.findById(destino.getIdEmpresa()));
            destino.setPacote(pacoteService.findById(destino.getIdPacote()));
            destinoService.save(destino);
        }

        return preparaPesquisa();
    }


}
