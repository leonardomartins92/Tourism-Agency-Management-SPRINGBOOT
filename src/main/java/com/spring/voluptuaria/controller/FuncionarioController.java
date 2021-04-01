package com.spring.voluptuaria.controller;

import com.spring.voluptuaria.model.Funcionario;
import com.spring.voluptuaria.service.FuncionarioService;
import com.spring.voluptuaria.service.TipoFuncionarioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@Slf4j
public class FuncionarioController {
    @Autowired
    FuncionarioService funcionarioService;
    @Autowired
    TipoFuncionarioService tipoFuncionarioService;

    @RequestMapping(value = "/pesquisaFuncionario", method = RequestMethod.GET)
    public ModelAndView preparaPesquisa(){
        ModelAndView mv = new ModelAndView("pesquisaFuncionario");
        List<Funcionario> funcionarios = funcionarioService.findAll();
        mv.addObject("funcionarios", funcionarios);
        return mv;
    }

    @RequestMapping(value = "/manterFuncionario", method = RequestMethod.GET)
    public ModelAndView getView(@RequestParam String acao, @RequestParam String operacao,
                                @RequestParam(required = false) Long cod){
        ModelAndView mv;
        log.info("ação:"+acao);
        log.info("operacao:"+operacao);
        log.info("cod:"+cod);

        if(acao.equals("preparaOperacao")){

            mv = new ModelAndView("manterFuncionario");
            mv.addObject("operacao", operacao);
            mv.addObject("tipos", tipoFuncionarioService.findAll());

            if (!operacao.equals("Adicionar")) {
                mv.addObject("funcionario", funcionarioService.findById(cod));
            }
        }
        else {
            mv = new ModelAndView("pesquisaFuncionario");
        }

        return mv;
    }


}
