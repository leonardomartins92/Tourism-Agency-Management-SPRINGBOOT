package com.spring.voluptuaria.controller;

import com.spring.voluptuaria.exception.NotFoundException;
import com.spring.voluptuaria.model.Pacote;
import com.spring.voluptuaria.service.*;
import com.spring.voluptuaria.utils.Method;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PacoteController {

    private final PacoteService pacoteService;
    private final FuncionarioService funcionarioService;
    private final ClienteService clienteService;
    private final DestinoService destinoService;
    private final EmpresaService empresaService;
    private final PassagemService passagemService;
    private final Method method;

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
