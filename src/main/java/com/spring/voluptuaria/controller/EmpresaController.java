package com.spring.voluptuaria.controller;

import com.spring.voluptuaria.exception.NotFoundException;
import com.spring.voluptuaria.model.Empresa;
import com.spring.voluptuaria.service.EmpresaService;
import com.spring.voluptuaria.service.FuncionarioService;
import com.spring.voluptuaria.service.TipoEmpresaService;
import com.spring.voluptuaria.utils.Method;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@RestController
public class EmpresaController {

    private EmpresaService empresaService;
    private TipoEmpresaService tipoEmpresaService;
    private FuncionarioService funcionarioService;
    private Method method;

    public EmpresaController(EmpresaService empresaService, TipoEmpresaService tipoEmpresaService, FuncionarioService funcionarioService, Method method) {
        this.empresaService = empresaService;
        this.tipoEmpresaService = tipoEmpresaService;
        this.funcionarioService = funcionarioService;
        this.method = method;
    }

    @GetMapping(path = "/pesquisaEmpresa")
    public ModelAndView preparaPesquisa(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        var roles = authentication.getAuthorities().toString().contains("ROLE_ADMIN");

        ModelAndView mv = new ModelAndView("pesquisaEmpresa");
        mv.addObject("empresas", empresaService.findAll());
        mv.addObject("tipo", roles );
        return mv;
    }

    @GetMapping(path = "/manterEmpresa")
    public ModelAndView preparaManter(@RequestParam String operacao,
                                      @RequestParam(required = false) Long cod) throws NotFoundException {
            ModelAndView mv = new ModelAndView("manterEmpresa");
            mv.addObject("tipos",tipoEmpresaService.findAll());
             mv.addObject("operacao", operacao);
        mv.addObject("metodo", method.verificaMetodo(operacao));
            if(cod != null){
                mv.addObject("empresa", empresaService.findById(cod));
            }
            return mv;
    }

    @PostMapping(path = "/manterEmpresa")
    public ModelAndView salvaEmpresa(Empresa empresa) throws NotFoundException {
           empresaService.save(empresa);
           return preparaPesquisa();
    }

    @PutMapping(path = "/manterEmpresa")
    public ModelAndView editaEmpresa(Empresa empresa) throws NotFoundException {
        return salvaEmpresa(empresa);
    }

    @DeleteMapping(path = "/manterEmpresa")
    public ModelAndView deletaEmpresa(Empresa empresa){
        empresaService.delete(empresa);
        return preparaPesquisa();
    }


}
