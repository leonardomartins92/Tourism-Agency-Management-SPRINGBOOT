package com.spring.voluptuaria.service;

import com.spring.voluptuaria.model.Empresa;
import com.spring.voluptuaria.repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public List<Empresa> findAllByTipo(String tipo){
        List<Empresa> empresas = new ArrayList<>();

        for(Empresa empresa:findAll()){
            if(empresa.getTipoEmpresa().getTipo().equals(tipo)){
                empresas.add(empresa);
            }
        }
        return empresas;
    }

}
