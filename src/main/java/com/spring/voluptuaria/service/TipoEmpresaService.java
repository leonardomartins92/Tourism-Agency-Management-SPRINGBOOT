package com.spring.voluptuaria.service;

import com.spring.voluptuaria.exception.NotFoundException;
import com.spring.voluptuaria.model.TipoEmpresa;
import com.spring.voluptuaria.repository.TipoEmpresaRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class TipoEmpresaService {

    private final TipoEmpresaRepository tipoEmpresaRepository;

    public List<TipoEmpresa> findAll() {

        return tipoEmpresaRepository.findAll();
    }

    public TipoEmpresa findById(Long id) throws NotFoundException {
        return tipoEmpresaRepository.findById(id)
                .orElseThrow(()->new NotFoundException(id));
    }

}
