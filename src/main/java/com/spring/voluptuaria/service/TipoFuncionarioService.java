package com.spring.voluptuaria.service;

import com.spring.voluptuaria.model.TipoFuncionario;
import com.spring.voluptuaria.repository.TipoFuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TipoFuncionarioService {

    @Autowired
    TipoFuncionarioRepository tipofuncionarioRepository;

    public List<TipoFuncionario> findAll() {
        return tipofuncionarioRepository.findAll();
    }

    public TipoFuncionario findById(Long id) {
        return tipofuncionarioRepository.findById(id).get();
    }

    public TipoFuncionario save(TipoFuncionario tipofuncionario) {
        return tipofuncionarioRepository.save(tipofuncionario);
    }
    public void delete(TipoFuncionario tipofuncionario){
        tipofuncionarioRepository.delete(tipofuncionario);
    }
    public void update(TipoFuncionario tipofuncionario){
        tipofuncionarioRepository.delete(findById(tipofuncionario.getId()));
        tipofuncionarioRepository.save(tipofuncionario);
    }
}
