package com.spring.voluptuaria.service;

import com.spring.voluptuaria.exception.NotFoundException;
import com.spring.voluptuaria.model.Passagem;
import com.spring.voluptuaria.repository.PassagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PassagemService {

    private PassagemRepository passagemRepository;
    private EmpresaService empresaService;
    private PacoteService pacoteService;

    public PassagemService(PassagemRepository passagemRepository, EmpresaService empresaService, PacoteService pacoteService) {
        this.passagemRepository = passagemRepository;
        this.empresaService = empresaService;
        this.pacoteService = pacoteService;
    }

    public List<Passagem> findAll() {
        return passagemRepository.findAll();
    }

    public Passagem findById(Long id) throws NotFoundException {

        return passagemRepository.findById(id).orElseThrow(()->new NotFoundException(id));
    }
    @ResponseStatus(HttpStatus.CREATED)
    public Passagem save(Passagem passagem) throws NotFoundException {
         passagem.setEmpresa(empresaService.findById(passagem.getIdEmpresa()));
         passagem.setPacote(pacoteService.findById(passagem.getIdPacote()));
         return passagemRepository.save(passagem);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(Passagem passagem){
        passagemRepository.delete(passagem);
    }

    public List<Passagem> findAllWithId(Long cod){
        return  findAll().stream()
                .filter(p->p.getIdPacote()==cod)
                .collect(Collectors.toList());
        }

}
