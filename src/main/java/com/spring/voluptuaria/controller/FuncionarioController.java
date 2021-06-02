package com.spring.voluptuaria.controller;

import com.spring.voluptuaria.exception.NotFoundException;
import com.spring.voluptuaria.model.Funcionario;
import com.spring.voluptuaria.service.FuncionarioService;
import com.spring.voluptuaria.service.TipoFuncionarioService;
import com.spring.voluptuaria.utils.Method;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class FuncionarioController {

    private final FuncionarioService funcionarioService;
    private final TipoFuncionarioService tipoFuncionarioService;
    private final Method method;


    @GetMapping(path = "/pesquisaFuncionario")
    public ModelAndView preparaPesquisa(){
        ModelAndView mv = new ModelAndView("pesquisaFuncionario");
        mv.addObject("funcionarios", funcionarioService.findAll());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        mv.addObject("tipo",authentication.getAuthorities().toString().contains("ROLE_ADMIN"));
        return mv;
    }

    @GetMapping(path = "/manterFuncionario")
    public ModelAndView preparaManter(@RequestParam String operacao,
                                      @RequestParam(required = false) Long cod) throws NotFoundException {
        ModelAndView mv;
        mv = new ModelAndView("manterFuncionario");
        mv.addObject("tipos", tipoFuncionarioService.findAll());
        mv.addObject("operacao", operacao);
        mv.addObject("metodo", method.verificaMetodo(operacao));
        if(cod != null){
            mv.addObject("funcionario", funcionarioService.findById(cod));
        }
        return mv;
    }

    @PostMapping(path = "/manterFuncionario")
    public ModelAndView salvarFuncionario(Funcionario funcionario) throws NotFoundException {
        funcionarioService.save(funcionario);
        return  preparaPesquisa();
    }

    @DeleteMapping(path = "/manterFuncionario")
    public ModelAndView deletarFuncionario(Funcionario funcionario) {
        funcionarioService.delete(funcionario);
        return  preparaPesquisa();
    }

    @PutMapping(path = "/manterFuncionario")
    public ModelAndView formulario(Funcionario funcionario, String novaSenha) throws Exception {
        funcionarioService.updateFuncionario(funcionario, novaSenha);
        return  preparaPesquisa();
    }


}
