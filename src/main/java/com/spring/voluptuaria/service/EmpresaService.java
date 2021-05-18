package com.spring.voluptuaria.service;

import com.spring.voluptuaria.exception.NotFoundException;
import com.spring.voluptuaria.model.Empresa;
import com.spring.voluptuaria.repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmpresaService {

   private EmpresaRepository empresaRepository;
   private TipoEmpresaService tipoEmpresaService;

   @Autowired
   public EmpresaService(EmpresaRepository empresaRepository, TipoEmpresaService tipoEmpresaService) {
        this.empresaRepository = empresaRepository;
        this.tipoEmpresaService = tipoEmpresaService;
    }

    public List<Empresa> findAll() {
        return empresaRepository.findAll();
    }

    public Empresa findById(Long id) throws NotFoundException {
        return empresaRepository.findById(id).orElseThrow(()-> new NotFoundException(id));
    }
    @ResponseStatus(HttpStatus.CREATED)
    public Empresa save(Empresa empresa) throws NotFoundException {
        empresa.setTipoEmpresa(tipoEmpresaService.findById(empresa.getIdTipoEmpresa()));
        return empresaRepository.save(empresa);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(Empresa empresa){
        empresaRepository.delete(empresa);
    }

    public List<Empresa> findAllByTipo(Long tipo){
       return findAll().stream().filter(empresa -> empresa.getIdTipoEmpresa() == tipo)
               .collect(Collectors.toList());


    }

}
