package com.spring.voluptuaria.service;

import com.spring.voluptuaria.model.Passagem;
import com.spring.voluptuaria.repository.PassagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PassagemService {

    @Autowired
    PassagemRepository passagemRepository;

    public List<Passagem> findAll() {
        return passagemRepository.findAll();
    }

    public Passagem findById(Long id) {
        return passagemRepository.findById(id).get();
    }

    public Passagem save(Passagem passagem) {
        return passagemRepository.save(passagem);
    }
    public void delete(Passagem passagem){
        passagemRepository.delete(passagem);
    }

}
