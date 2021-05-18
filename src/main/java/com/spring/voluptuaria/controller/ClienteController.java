package com.spring.voluptuaria.controller;

import com.spring.voluptuaria.exception.NotFoundException;
import com.spring.voluptuaria.model.Cliente;
import com.spring.voluptuaria.service.ClienteService;
import com.spring.voluptuaria.utils.Method;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class ClienteController {

    private ClienteService clienteService;
    private Method method;

    @Autowired
    public ClienteController(ClienteService clienteService, Method method) {
        this.clienteService = clienteService;
        this.method = method;
    }

    @GetMapping(path = "/pesquisaCliente")
    public ModelAndView preparaPesquisa(){
        ModelAndView mv = new ModelAndView("pesquisaCliente");
        mv.addObject("clientes", clienteService.findAll());
        return mv;
    }

    @GetMapping(path = "/manterCliente")
    public ModelAndView preparaManter(@RequestParam String operacao,
                                      @RequestParam(required = false) Long cod ) throws NotFoundException {
        ModelAndView mv;
        mv = new ModelAndView("manterCliente");
        mv.addObject("operacao",operacao);
        mv.addObject("metodo", method.verificaMetodo(operacao));
        if(cod != null){
            mv.addObject("cliente", clienteService.findById(cod));
        }
        return mv;
    }

    @PostMapping(path = "/manterCliente")
    public ModelAndView salvaCliente(Cliente cliente) {
         clienteService.save(cliente);
         return preparaPesquisa();
    }

    @DeleteMapping(path = "/manterCliente")
    public ModelAndView deletaCliente(Cliente cliente) {
          clienteService.delete(cliente);
          return preparaPesquisa();
    }
    @PutMapping(path = "/manterCliente")
    public ModelAndView atualizaCliente(Cliente cliente) {
         return salvaCliente(cliente);
    }




}






