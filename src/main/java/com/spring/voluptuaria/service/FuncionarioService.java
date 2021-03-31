package com.spring.voluptuaria.service;

import com.spring.voluptuaria.model.Funcionario;
import com.spring.voluptuaria.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class FuncionarioService {

    @Autowired
    FuncionarioRepository funcionarioRepository;

    public List<Funcionario> findAll() {
        return funcionarioRepository.findAll();
    }

    public Funcionario findById(Long id) {
        return funcionarioRepository.findById(id).get();
    }

    public Funcionario save(Funcionario funcionario) {
        return funcionarioRepository.save(funcionario);
    }
    public void delete(Funcionario funcionario){
        funcionarioRepository.delete(funcionario);
    }
    public void update(Funcionario funcionario){
        funcionarioRepository.delete(findById(funcionario.getId()));
        funcionarioRepository.save(funcionario);
    }
}
