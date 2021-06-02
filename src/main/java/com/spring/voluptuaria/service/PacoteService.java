package com.spring.voluptuaria.service;

import com.spring.voluptuaria.exception.NotFoundException;
import com.spring.voluptuaria.model.Pacote;
import com.spring.voluptuaria.repository.PacoteRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@AllArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class PacoteService {

    private final PacoteRepository pacoteRepository;
    private final ClienteService clienteService;
    private final FuncionarioService funcionarioService;

    public List<Pacote> findAll() {
        return pacoteRepository.findAll();
    }

    public Pacote findById(Long id) throws NotFoundException {
        return pacoteRepository.findById(id).orElseThrow(()-> new NotFoundException(id));
    }

    public Pacote save(Pacote pacote) throws NotFoundException {
        pacote.setCliente(clienteService.findById(pacote.getIdCliente()));
        pacote.setFuncionario(funcionarioService.findById(pacote.getIdFuncionario()));
        return pacoteRepository.save(pacote);
    }

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
