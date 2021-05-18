package com.spring.voluptuaria.controller;

import com.spring.voluptuaria.exception.NotFoundException;
import com.spring.voluptuaria.model.Passagem;
import com.spring.voluptuaria.service.EmpresaService;
import com.spring.voluptuaria.service.PacoteService;
import com.spring.voluptuaria.service.PassagemService;
import com.spring.voluptuaria.utils.Method;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@Slf4j
public class PassagemController {

    private PassagemService passagemService;
    private EmpresaService empresaService;
    private PacoteService pacoteService;
    private Method method;

    public PassagemController(PassagemService passagemService, EmpresaService empresaService, PacoteService pacoteService, Method method) {
        this.passagemService = passagemService;
        this.empresaService = empresaService;
        this.pacoteService = pacoteService;
        this.method = method;
    }

    @GetMapping(path = "/pesquisaPassagem")
    public ModelAndView preparaPesquisa(){
        ModelAndView mv = new ModelAndView("pesquisaPassagem");
        mv.addObject("passagens", passagemService.findAll());
        return mv;
    }

    @GetMapping(path = "/manterPassagem")
    public ModelAndView preparaManter(@RequestParam String operacao,
                                      @RequestParam(required = false) Long cod) throws NotFoundException {
        ModelAndView mv;
        mv = new ModelAndView("manterPassagem");
        mv.addObject("pacotes", pacoteService.findAll());
        mv.addObject("empresas", empresaService.findAllByTipo(2L));
        mv.addObject("operacao", operacao);
        mv.addObject("metodo", method.verificaMetodo(operacao));
        if(cod != null){
            mv.addObject("passagem", passagemService.findById(cod));
        }
        return mv;
    }

    @DeleteMapping(path = "/manterPassagem")
    public ModelAndView deletarPassagem(Passagem passagem) {
         passagemService.delete(passagem);
         return preparaPesquisa();
    }

    @PostMapping(path = "/manterPassagem")
    public ModelAndView salvarPassagem(Passagem passagem) throws NotFoundException {
            passagemService.save(passagem);
            return preparaPesquisa();
    }

    @PutMapping(path = "/manterPassagem")
    public ModelAndView editarPassagem(Passagem passagem) throws NotFoundException {
       return salvarPassagem(passagem);

    }

}
