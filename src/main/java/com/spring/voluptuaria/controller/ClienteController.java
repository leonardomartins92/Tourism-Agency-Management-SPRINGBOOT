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

@Slf4j
@Controller
public class ClienteController {
    @Autowired
    ClienteService clienteService;

    @RequestMapping(value = "/pesquisaCliente", method = RequestMethod.GET)
    public ModelAndView preparaPesquisa(){
        ModelAndView mv = new ModelAndView("pesquisaCliente");
        mv.addObject("clientes", clienteService.findAll());
        return mv;
    }

    @RequestMapping(value = "/manterCliente", method = RequestMethod.GET)
    public ModelAndView preparaManter(@RequestParam String operacao,
                        @RequestParam(required = false) Long cod){
        ModelAndView mv;

            mv = new ModelAndView("manterCliente");
            mv.addObject("operacao", operacao);

            if (!operacao.equals("Adicionar")) {
                 mv.addObject("cliente", clienteService.findById(cod));
            }
        return mv;
    }

    @RequestMapping(value = "/manterCliente", method = RequestMethod.POST)
    public ModelAndView formulario(@RequestParam String operacao, Cliente cliente) {

        switch (operacao){
            case ("Excluir"):
                clienteService.delete(cliente);
                break;
            case("Editar"):
                clienteService.update(cliente);
                break;
            case("Adicionar"):
                clienteService.save(cliente);
                break;
        }

        return preparaPesquisa();
    }


}






