package com.spring.voluptuaria.service;

import com.spring.voluptuaria.exception.NotFoundException;
import com.spring.voluptuaria.model.Destino;
import com.spring.voluptuaria.repository.DestinoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class DestinoService {

   private final DestinoRepository destinoRepository;
   private final EmpresaService empresaService;
   private final PacoteService pacoteService;

    public List<Destino> findAll() {
        return destinoRepository.findAll();
    }

    public Destino findById(Long id) throws NotFoundException {
        return destinoRepository.findById(id).orElseThrow(()-> new NotFoundException(id));
    }

    public Destino save(Destino destino) throws NotFoundException {
        destino.setEmpresa(empresaService.findById(destino.getIdEmpresa()));
        destino.setPacote(pacoteService.findById(destino.getIdPacote()));
        return destinoRepository.save(destino);
    }

    public void delete(Destino destino){
        destinoRepository.delete(destino);
    }

    public List<Destino> findAllWithId(Long cod){
        return findAll().stream()
                .filter(destino -> destino.getId()==cod)
                .collect(Collectors.toList());

    }

}
