package com.spring.voluptuaria.controller;

import com.spring.voluptuaria.exception.NotFoundException;
import com.spring.voluptuaria.model.Cliente;
import com.spring.voluptuaria.service.ClienteService;
import com.spring.voluptuaria.utils.Method;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.validation.Valid;

@AllArgsConstructor(onConstructor = @__(@Autowired))
@RestController
public class ClienteController {

    private final ClienteService clienteService;
    private final Method method;

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
    public ModelAndView salvaCliente(@Valid Cliente cliente) {
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






