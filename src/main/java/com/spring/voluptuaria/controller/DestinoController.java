package com.spring.voluptuaria.controller;

import com.spring.voluptuaria.model.Destino;
import com.spring.voluptuaria.service.DestinoService;
import com.spring.voluptuaria.service.EmpresaService;
import com.spring.voluptuaria.service.PacoteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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

    @RequestMapping(value = "/pesquisaDestino", method = RequestMethod.GET)
    public ModelAndView preparaPesquisa(){
        ModelAndView mv = new ModelAndView("pesquisaDestino");
        List<Destino> destinos = destinoService.findAll();
        mv.addObject("destinos", destinos);
        return mv;
    }

    @RequestMapping(value = "/manterDestino", method = RequestMethod.GET)
    public ModelAndView getView(@RequestParam String acao, @RequestParam String operacao,
                                @RequestParam(required = false) Long cod){
        ModelAndView mv;
        log.info("ação:"+acao);
        log.info("operacao:"+operacao);
        log.info("cod:"+cod);

        if(acao.equals("preparaOperacao")){

            mv = new ModelAndView("manterDestino");
            mv.addObject("operacao", operacao);
            mv.addObject("empresas", empresaService.findAll());
            mv.addObject("pacotes", pacoteService.findAll());


            if (!operacao.equals("Adicionar")) {
                mv.addObject("destino", destinoService.findById(cod));
            }
        }
        else {
            mv = new ModelAndView("pesquisaDestino");
        }

        return mv;
    }


}
