package com.spring.voluptuaria.controller;

import com.spring.voluptuaria.model.Empresa;
import com.spring.voluptuaria.model.Empresa;
import com.spring.voluptuaria.model.TipoEmpresa;
import com.spring.voluptuaria.service.EmpresaService;
import com.spring.voluptuaria.service.TipoEmpresaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
public class EmpresaController {
    @Autowired
    EmpresaService empresaService;
    @Autowired
    TipoEmpresaService tipoEmpresaService;

    @RequestMapping(value = "/pesquisaEmpresa", method = RequestMethod.GET)
    public ModelAndView preparaPesquisa(){
        ModelAndView mv = new ModelAndView("pesquisaEmpresa");

        mv.addObject("empresas", empresaService.findAll());

        return mv;
    }

    @RequestMapping(value = "/manterEmpresa", method = RequestMethod.GET)
    public ModelAndView preparaManter(@RequestParam String operacao,
                                @RequestParam(required = false) Long cod){

            ModelAndView mv;
            mv = new ModelAndView("manterEmpresa");
            mv.addObject("operacao", operacao);
            mv.addObject("tipos",tipoEmpresaService.findAll());

            if (!operacao.equals("Adicionar")) {
                mv.addObject("empresa", empresaService.findById(cod));
            }

        return mv;
    }
    @RequestMapping(value = "/manterEmpresa", method = RequestMethod.POST)
    public ModelAndView form(@RequestParam String operacao, Empresa empresa) {

       if(operacao.equals("Excluir")){
           empresaService.delete(empresa);
       }
       else{
           empresa.setTipoEmpresa(tipoEmpresaService.findById(empresa.getIdTipoEmpresa()));
           empresaService.save(empresa);
       }
       return preparaPesquisa();
    }


}
