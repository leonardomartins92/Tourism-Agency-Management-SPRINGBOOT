package com.spring.voluptuaria.service;

import com.spring.voluptuaria.model.Pacote;
import com.spring.voluptuaria.repository.PacoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PacoteService {

    @Autowired
    PacoteRepository pacoteRepository;

    public List<Pacote> findAll() {
        return pacoteRepository.findAll();
    }

    public Pacote findById(Long id) {
        return pacoteRepository.findById(id).get();
    }

    public Pacote save(Pacote pacote) {
        return pacoteRepository.save(pacote);
    }
    public void delete(Pacote pacote){
        pacoteRepository.delete(pacote);
    }
    public List<Pacote> findPacotesByFuncionario_Id(Long id){
        return pacoteRepository.findPacotesByFuncionario_Id(id);
    }
}
