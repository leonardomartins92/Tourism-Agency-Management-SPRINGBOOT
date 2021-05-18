package com.spring.voluptuaria.service;

import com.spring.voluptuaria.exception.NotFoundException;
import com.spring.voluptuaria.model.Destino;
import com.spring.voluptuaria.repository.DestinoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DestinoService {

   private DestinoRepository destinoRepository;
   private EmpresaService empresaService;
   private PacoteService pacoteService;

   @Autowired
    public DestinoService(DestinoRepository destinoRepository, EmpresaService empresaService, PacoteService pacoteService) {
        this.destinoRepository = destinoRepository;
        this.empresaService = empresaService;
        this.pacoteService = pacoteService;
    }

    public List<Destino> findAll() {
        return destinoRepository.findAll();
    }

    public Destino findById(Long id) throws NotFoundException {
        return destinoRepository.findById(id).orElseThrow(()-> new NotFoundException(id));
    }

    @ResponseStatus(HttpStatus.CREATED)
    public Destino save(Destino destino) throws NotFoundException {
        destino.setEmpresa(empresaService.findById(destino.getIdEmpresa()));
        destino.setPacote(pacoteService.findById(destino.getIdPacote()));
        return destinoRepository.save(destino);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(Destino destino){
        destinoRepository.delete(destino);
    }

    public List<Destino> findAllWithId(Long cod){
        return findAll().stream()
                .filter(destino -> destino.getId()==cod)
                .collect(Collectors.toList());

    }

}
