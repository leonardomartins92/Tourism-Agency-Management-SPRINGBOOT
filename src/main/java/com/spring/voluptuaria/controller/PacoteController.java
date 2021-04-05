package com.spring.voluptuaria.controller;

import com.spring.voluptuaria.model.Empresa;
import com.spring.voluptuaria.model.Pacote;
import com.spring.voluptuaria.model.Destino;
import com.spring.voluptuaria.service.*;
import com.spring.voluptuaria.service.PacoteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

    @RequestMapping(value = "/pesquisaPacote", method = RequestMethod.GET)
    public ModelAndView preparaPesquisa(){
        ModelAndView mv = new ModelAndView("pesquisaPacote");
        List<Pacote> pacotes = pacoteService.findAll();
        mv.addObject("pacotes", pacotes);
        return mv;
    }

    @RequestMapping(value = "/manterPacote", method = RequestMethod.GET)
    public ModelAndView getView(@RequestParam String operacao,
                                @RequestParam(required = false) Long cod){
        ModelAndView mv;


            mv = new ModelAndView("manterPacote");
            mv.addObject("operacao", operacao);
            mv.addObject("funcionarios",funcionarioService.findAll());
            mv.addObject("clientes", clienteService.findAll());



            List<Empresa> empresas = new ArrayList<>();

        for(Empresa empresa:empresaService.findAll()){
            if(empresa.getTipoEmpresa().getTipo().equals("ACOMODAÇÃO")){
                empresas.add(empresa);
            }
        }
             mv.addObject("empresas", empresas);

            if (!operacao.equals("Adicionar")) {
                mv.addObject("pacote", pacoteService.findById(cod));
                List<Destino> destinos = new ArrayList<>();
                for(Destino destino: destinoService.findAll()){
                    if(destino.getIdPacote() == cod){
                        destinos.add(destino);
                    }
                }
                mv.addObject("destinos", destinos);
            }
        return mv;
    }

    @RequestMapping(value = "/manterPacote", method = RequestMethod.POST)
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
