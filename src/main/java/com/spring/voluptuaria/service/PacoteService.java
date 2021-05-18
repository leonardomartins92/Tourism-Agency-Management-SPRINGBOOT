package com.spring.voluptuaria.service;

import com.spring.voluptuaria.exception.NotFoundException;
import com.spring.voluptuaria.model.Pacote;
import com.spring.voluptuaria.repository.PacoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@Service
public class PacoteService {

    private PacoteRepository pacoteRepository;
    private ClienteService clienteService;
    private FuncionarioService funcionarioService;

    @Autowired
    public PacoteService(PacoteRepository pacoteRepository, ClienteService clienteService, FuncionarioService funcionarioService) {
        this.pacoteRepository = pacoteRepository;
        this.clienteService = clienteService;
        this.funcionarioService = funcionarioService;
    }

    public List<Pacote> findAll() {
        return pacoteRepository.findAll();
    }

    public Pacote findById(Long id) throws NotFoundException {
        return pacoteRepository.findById(id).orElseThrow(()-> new NotFoundException(id));
    }

    @ResponseStatus(HttpStatus.CREATED)
    public Pacote save(Pacote pacote) throws NotFoundException {
        pacote.setCliente(clienteService.findById(pacote.getIdCliente()));
        pacote.setFuncionario(funcionarioService.findById(pacote.getIdFuncionario()));
        return pacoteRepository.save(pacote);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(Pacote pacote){
        pacoteRepository.delete(pacote);
    }

    public int countPacotesByFuncionario(Long id){
        List<Pacote> pacotes = pacoteRepository.findPacotesByFuncionario_Id(id);
        if (pacotes == null){
            return 0;
        }
        else{
            return pacotes.size();
        }
    }
}
