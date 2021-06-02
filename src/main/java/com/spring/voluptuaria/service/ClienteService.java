package com.spring.voluptuaria.service;

import com.spring.voluptuaria.exception.NotFoundException;
import com.spring.voluptuaria.model.Cliente;
import com.spring.voluptuaria.repository.ClienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class ClienteService{

    private final ClienteRepository clienteRepository;

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    public Cliente findById(Long id) throws NotFoundException {
        return clienteRepository.findById(id)
                .orElseThrow(()->new NotFoundException(id));
    }

    public Cliente save(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public void delete(Cliente cliente){
        clienteRepository.delete(cliente);
    }

    }
