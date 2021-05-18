package com.spring.voluptuaria.service;

import com.spring.voluptuaria.exception.NotFoundException;
import com.spring.voluptuaria.model.Cliente;
import com.spring.voluptuaria.repository.ClienteRepository;
import com.spring.voluptuaria.utils.Method;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@Service
public class ClienteService{

    private ClienteRepository clienteRepository;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    public Cliente findById(Long id) throws NotFoundException {
        return clienteRepository.findById(id)
                .orElseThrow(()->new NotFoundException(id));
    }
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente save(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(Cliente cliente){
        clienteRepository.delete(cliente);
    }

    }
