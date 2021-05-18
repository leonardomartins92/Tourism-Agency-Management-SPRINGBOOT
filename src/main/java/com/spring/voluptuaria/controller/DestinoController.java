package com.spring.voluptuaria.controller;

import com.spring.voluptuaria.exception.NotFoundException;
import com.spring.voluptuaria.model.Destino;
import com.spring.voluptuaria.service.DestinoService;
import com.spring.voluptuaria.service.EmpresaService;
import com.spring.voluptuaria.service.PacoteService;
import com.spring.voluptuaria.utils.Method;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
@RestController
@Slf4j
public class DestinoController {

    private DestinoService destinoService;
    private PacoteService pacoteService;
    private EmpresaService empresaService;
    private Method method;

    @Autowired
    public DestinoController(DestinoService destinoService, PacoteService pacoteService, EmpresaService empresaService, Method method) {
        this.destinoService = destinoService;
        this.pacoteService = pacoteService;
        this.empresaService = empresaService;
        this.method = method;
    }

    @GetMapping(path = "/pesquisaDestino")
    public ModelAndView preparaPesquisa(){
        ModelAndView mv = new ModelAndView("pesquisaDestino");
        mv.addObject("destinos", destinoService.findAll());
        return mv;
    }

    @GetMapping(path = "/manterDestino")
    public ModelAndView preparaManter(@RequestParam String operacao,
                                      @RequestParam(required = false) Long cod) throws NotFoundException {
        ModelAndView mv;
        mv = new ModelAndView("manterDestino");
        mv.addObject("pacotes", pacoteService.findAll());
        mv.addObject("empresas", empresaService.findAllByTipo(2L));
        mv.addObject("operacao", operacao);
        mv.addObject("metodo", method.verificaMetodo(operacao));
        if(cod != null){
            mv.addObject("destino", destinoService.findById(cod));
        }
        return mv;
    }

    @PostMapping(path = "/manterDestino")
    public ModelAndView salvarDestino(Destino destino) throws NotFoundException {
            destinoService.save(destino);
            return preparaPesquisa();
    }

    @PutMapping(path = "/manterDestino")
    public ModelAndView editarDestino(Destino destino) throws NotFoundException {
         return salvarDestino(destino);
    }

    @DeleteMapping(path = "/manterDestino")
    public ModelAndView deletarDestino(Destino destino) {
            destinoService.delete(destino);
            return preparaPesquisa();
    }


}
