package com.spring.voluptuaria.controller;

import com.spring.voluptuaria.model.Cliente;
import com.spring.voluptuaria.service.ClienteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
public class ClienteController {
    @Autowired
    ClienteService clienteService;

    @GetMapping(value = "/pesquisaCliente")
    public ModelAndView preparaPesquisa(){
        ModelAndView mv = new ModelAndView("pesquisaCliente");
        mv.addObject("clientes", clienteService.findAll());
        return mv;
    }

    @GetMapping(value = "/manterCliente")
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

    @PostMapping(value = "/manterCliente")
    public ModelAndView formulario(@RequestParam String operacao, Cliente cliente) {

      if(operacao.equals("Excluir")){
          clienteService.delete(cliente);
      }
      else{
          clienteService.save(cliente);
      }
         return preparaPesquisa();
    }


}






