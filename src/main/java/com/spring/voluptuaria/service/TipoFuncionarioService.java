package com.spring.voluptuaria.service;

import com.spring.voluptuaria.model.TipoFuncionario;
import com.spring.voluptuaria.repository.TipoFuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoFuncionarioService {
    @Autowired
    TipoFuncionarioRepository tipoFuncionarioRepository;

    public List<TipoFuncionario> findAll() {
        return tipoFuncionarioRepository.findAll();
    }

    public TipoFuncionario findById(Long id) {
        return tipoFuncionarioRepository.findById(id).get();
    }

    public TipoFuncionario save(TipoFuncionario tipoFuncionario) {
        return tipoFuncionarioRepository.save(tipoFuncionario);
    }
    public void delete(TipoFuncionario tipoFuncionario){
        tipoFuncionarioRepository.delete(tipoFuncionario);
    }
    public void update(TipoFuncionario tipoFuncionario){
        tipoFuncionarioRepository.delete(findById(tipoFuncionario.getId()));
        tipoFuncionarioRepository.save(tipoFuncionario);
    }
}
