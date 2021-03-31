package com.spring.voluptuaria.controller;

import com.spring.voluptuaria.model.Cliente;
import com.spring.voluptuaria.service.ClienteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
@Slf4j
@Controller
public class ClienteController {
    @Autowired
    ClienteService clienteService;

    @RequestMapping(value = "/pesquisaCliente", method = RequestMethod.GET)
    public ModelAndView preparaPesquisa(){
        ModelAndView mv = new ModelAndView("pesquisaCliente");
        List<Cliente> clientes = clienteService.findAll();
        mv.addObject("clientes", clientes);
        return mv;
    }

    @RequestMapping(value = "/manterCliente", method = RequestMethod.GET)
    public ModelAndView getView(@RequestParam String acao, @RequestParam String operacao,
                        @RequestParam(required = false) Long cod){
        ModelAndView mv;
        log.info("ação:"+acao);
        log.info("operacao:"+operacao);
        log.info("cod:"+cod);

        if(acao.equals("preparaOperacao")){

            mv = new ModelAndView("manterCliente");
            mv.addObject("operacao", operacao);

            if (!operacao.equals("Adicionar")) {
                 mv.addObject("cliente", clienteService.findById(cod));
            }
        }
        else {
            mv = new ModelAndView("pesquisaCliente");
        }

        return mv;
    }


}






