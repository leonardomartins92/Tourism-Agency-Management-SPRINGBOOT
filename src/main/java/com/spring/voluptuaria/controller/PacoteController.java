package com.spring.voluptuaria.controller;

import com.spring.voluptuaria.model.Empresa;
import com.spring.voluptuaria.model.Pacote;
import com.spring.voluptuaria.model.Destino;
import com.spring.voluptuaria.model.Passagem;
import com.spring.voluptuaria.service.*;
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
public class PacoteController {
    @Autowired
    PacoteService pacoteService;
    @Autowired
    FuncionarioService funcionarioService;
    @Autowired
    ClienteService clienteService;
    @Autowired
    DestinoService destinoService;
    @Autowired
    EmpresaService empresaService;
    @Autowired
    PassagemService passagemService;

    @GetMapping(value = "/pesquisaPacote")
    public ModelAndView preparaPesquisa(){
        ModelAndView mv = new ModelAndView("pesquisaPacote");
        mv.addObject("pacotes", pacoteService.findAll());
        return mv;
    }

    @GetMapping(value = "/manterPacote")
    public ModelAndView getView(@RequestParam String operacao,
                                @RequestParam(required = false) Long cod){
            ModelAndView mv;
            mv = new ModelAndView("manterPacote");
            mv.addObject("operacao", operacao);
            mv.addObject("funcionarios",funcionarioService.findAll());
            mv.addObject("clientes", clienteService.findAll());
            mv.addObject("empresas", empresaService.findAllByTipo("ACOMODAÇÃO"));

            if (!operacao.equals("Adicionar")) {
                mv.addObject("pacote", pacoteService.findById(cod));
                mv.addObject("destinos", destinoService.findAllWithId(cod));
                mv.addObject("passagens", passagemService.findAllWithId(cod));
            }
        return mv;
    }

    @PostMapping(value = "/manterPacote")
    public ModelAndView formulario(@RequestParam String operacao, Pacote pacote) {

       if(operacao.equals("Excluir")){
           pacoteService.delete(pacote);
       }
       else{
           pacote.setCliente(clienteService.findById(pacote.getIdCliente()));
           pacote.setFuncionario(funcionarioService.findById(pacote.getIdFuncionario()));
           pacoteService.save(pacote);
       }

        return  preparaPesquisa();
    }

}
