package com.spring.voluptuaria.controller;

import com.spring.voluptuaria.exception.NotFoundException;
import com.spring.voluptuaria.model.Pacote;
import com.spring.voluptuaria.service.*;
import com.spring.voluptuaria.utils.Method;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@Slf4j
public class PacoteController {

    private PacoteService pacoteService;
    private FuncionarioService funcionarioService;
    private ClienteService clienteService;
    private DestinoService destinoService;
    private EmpresaService empresaService;
    private PassagemService passagemService;
    private Method method;

    @Autowired
    public PacoteController(PacoteService pacoteService, FuncionarioService funcionarioService, ClienteService clienteService,
                            DestinoService destinoService, EmpresaService empresaService, PassagemService passagemService, Method method) {
        this.pacoteService = pacoteService;
        this.funcionarioService = funcionarioService;
        this.clienteService = clienteService;
        this.destinoService = destinoService;
        this.empresaService = empresaService;
        this.passagemService = passagemService;
        this.method = method;
    }

    @GetMapping(path = "/pesquisaPacote")
    public ModelAndView preparaPesquisa(){
        ModelAndView mv = new ModelAndView("pesquisaPacote");
        mv.addObject("pacotes", pacoteService.findAll());
        return mv;
    }

    @GetMapping(path = "/manterPacote")
    public ModelAndView preparaManter(@RequestParam String operacao,
                                      @RequestParam(required = false) Long cod) throws NotFoundException {
            ModelAndView mv;
            mv = new ModelAndView("manterPacote");
            mv.addObject("funcionarios",funcionarioService.findAll());
            mv.addObject("clientes", clienteService.findAll());
            mv.addObject("empresas", empresaService.findAllByTipo(1L));
            mv.addObject("operacao", operacao);
            mv.addObject("metodo", method.verificaMetodo(operacao));
            if(cod != null){
                mv.addObject("funcionario", pacoteService.findById(cod).getFuncionario());
                mv.addObject("pacote", pacoteService.findById(cod));
                mv.addObject("destinos", destinoService.findAllWithId(cod));
                mv.addObject("passagens", passagemService.findAllWithId(cod));
            }
            return mv;
    }

    @PostMapping(path = "/manterPacote")
    public ModelAndView salvarPacote(Pacote pacote) throws NotFoundException {
           pacoteService.save(pacote);
           return  preparaPesquisa();
    }

    @PutMapping(path = "/manterPacote")
    public ModelAndView editarPacote(Pacote pacote) throws NotFoundException {
            pacoteService.save(pacote);
            return  preparaPesquisa();
    }

    @DeleteMapping(path = "/manterPacote")
    public ModelAndView deletarPacote(Pacote pacote) {
            pacoteService.delete(pacote);
            return  preparaPesquisa();
    }

}
