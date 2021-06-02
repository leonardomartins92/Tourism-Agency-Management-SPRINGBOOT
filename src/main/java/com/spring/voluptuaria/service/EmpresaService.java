package com.spring.voluptuaria.service;

import com.spring.voluptuaria.exception.NotFoundException;
import com.spring.voluptuaria.model.Empresa;
import com.spring.voluptuaria.repository.EmpresaRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class EmpresaService {

   private final EmpresaRepository empresaRepository;
   private final TipoEmpresaService tipoEmpresaService;

    public List<Empresa> findAll() {
        return empresaRepository.findAll();
    }

    public Empresa findById(Long id) throws NotFoundException {
        return empresaRepository.findById(id).orElseThrow(()-> new NotFoundException(id));
    }

    public Empresa save(Empresa empresa) throws NotFoundException {
        empresa.setTipoEmpresa(tipoEmpresaService.findById(empresa.getIdTipoEmpresa()));
        return empresaRepository.save(empresa);
    }

    public void delete(Empresa empresa){
        empresaRepository.delete(empresa);
    }

    public List<Empresa> findAllByTipo(Long tipo){
       return findAll().stream().filter(empresa -> empresa.getIdTipoEmpresa() == tipo)
               .collect(Collectors.toList());


    }

}
