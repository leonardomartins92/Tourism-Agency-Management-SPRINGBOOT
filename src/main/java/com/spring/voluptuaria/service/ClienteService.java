package com.spring.voluptuaria.service;

import com.spring.voluptuaria.model.Cliente;
import com.spring.voluptuaria.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ClienteService{

    @Autowired
    ClienteRepository clienteRepository;

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

   public Cliente findById(Long id) {
        return clienteRepository.findById(id).get();
    }

   public Cliente save(Cliente cliente) {
        return clienteRepository.save(cliente);
    }
    public void delete(Cliente cliente){
        clienteRepository.delete(cliente);
    }
    public void update(Cliente cliente){
        Cliente clienteBD = findById(cliente.getId());


    }

}
