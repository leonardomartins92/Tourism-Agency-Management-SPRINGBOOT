package com.spring.voluptuaria.service;

import com.spring.voluptuaria.exception.NotFoundException;
import com.spring.voluptuaria.model.TipoEmpresa;
import com.spring.voluptuaria.repository.TipoEmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoEmpresaService {

    private TipoEmpresaRepository tipoEmpresaRepository;

    @Autowired
    public TipoEmpresaService(TipoEmpresaRepository tipoEmpresaRepository) {
        this.tipoEmpresaRepository = tipoEmpresaRepository;
    }

    public List<TipoEmpresa> findAll() {

        return tipoEmpresaRepository.findAll();
    }

    public TipoEmpresa findById(Long id) throws NotFoundException {
        return tipoEmpresaRepository.findById(id)
                .orElseThrow(()->new NotFoundException(id));
    }

}
