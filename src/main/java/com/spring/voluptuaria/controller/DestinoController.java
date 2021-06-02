package com.spring.voluptuaria.controller;

import com.spring.voluptuaria.exception.NotFoundException;
import com.spring.voluptuaria.model.Destino;
import com.spring.voluptuaria.service.DestinoService;
import com.spring.voluptuaria.service.EmpresaService;
import com.spring.voluptuaria.service.PacoteService;
import com.spring.voluptuaria.utils.Method;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class DestinoController {

    private final DestinoService destinoService;
    private final PacoteService pacoteService;
    private final EmpresaService empresaService;
    private final Method method;

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
