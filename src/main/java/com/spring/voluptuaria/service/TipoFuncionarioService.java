package com.spring.voluptuaria.service;

import com.spring.voluptuaria.exception.NotFoundException;
import com.spring.voluptuaria.model.TipoFuncionario;
import com.spring.voluptuaria.repository.TipoFuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoFuncionarioService {

   private TipoFuncionarioRepository tipoFuncionarioRepository;

   @Autowired
    public TipoFuncionarioService(TipoFuncionarioRepository tipoFuncionarioRepository) {
        this.tipoFuncionarioRepository = tipoFuncionarioRepository;
    }

    public List<TipoFuncionario> findAll() {
        return tipoFuncionarioRepository.findAll();
    }

    public TipoFuncionario findById(Long id) throws NotFoundException {
        return tipoFuncionarioRepository.findById(id)
                .orElseThrow(()-> new NotFoundException(id));
    }

}
