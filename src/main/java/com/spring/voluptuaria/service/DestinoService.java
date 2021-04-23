package com.spring.voluptuaria.service;

import com.spring.voluptuaria.model.Destino;
import com.spring.voluptuaria.repository.DestinoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class DestinoService {

    @Autowired
    DestinoRepository destinoRepository;

    public List<Destino> findAll() {
        return destinoRepository.findAll();
    }
    public Destino findById(Long id) {
        return destinoRepository.findById(id).get();
    }
    public Destino save(Destino destino) {
        return destinoRepository.save(destino);
    }
    public void delete(Destino destino){
        destinoRepository.delete(destino);
    }
    public List<Destino> findAllWithId(Long cod){
        List<Destino> destinos = new ArrayList<>();
        for(Destino destino: findAll()){
            if(destino.getIdPacote() == cod){
                destinos.add(destino);
            }
        }
        return destinos;
    }

}
