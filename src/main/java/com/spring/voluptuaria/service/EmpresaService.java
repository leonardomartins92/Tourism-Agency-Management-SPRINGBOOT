package com.spring.voluptuaria.service;

import com.spring.voluptuaria.model.Empresa;
import com.spring.voluptuaria.repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EmpresaService {

    @Autowired
    EmpresaRepository empresaRepository;

    public List<Empresa> findAll() {
        return empresaRepository.findAll();
    }

    public Empresa findById(Long id) {
        return empresaRepository.findById(id).get();
    }

    public Empresa save(Empresa empresa) {
        return empresaRepository.save(empresa);
    }
    public void delete(Empresa empresa){
        empresaRepository.delete(empresa);
    }
    public void update(Empresa empresa){
        empresaRepository.delete(findById(empresa.getId()));
        empresaRepository.save(empresa);
    }
}
