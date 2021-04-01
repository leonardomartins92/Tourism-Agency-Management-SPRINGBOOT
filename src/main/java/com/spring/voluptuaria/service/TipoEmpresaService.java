package com.spring.voluptuaria.service;

import com.spring.voluptuaria.model.TipoEmpresa;
import com.spring.voluptuaria.repository.TipoEmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TipoEmpresaService {
    @Autowired
    TipoEmpresaRepository tipoEmpresaRepository;

    public List<TipoEmpresa> findAll() {
        return tipoEmpresaRepository.findAll();
    }

    public TipoEmpresa findById(Long id) {
        return tipoEmpresaRepository.findById(id).get();
    }

    public TipoEmpresa save(TipoEmpresa tipoEmpresa) {
        return tipoEmpresaRepository.save(tipoEmpresa);
    }
    public void delete(TipoEmpresa tipoEmpresa){
        tipoEmpresaRepository.delete(tipoEmpresa);
    }
    public void update(TipoEmpresa tipoEmpresa){
        tipoEmpresaRepository.delete(findById(tipoEmpresa.getId()));
        tipoEmpresaRepository.save(tipoEmpresa);
    }
}
