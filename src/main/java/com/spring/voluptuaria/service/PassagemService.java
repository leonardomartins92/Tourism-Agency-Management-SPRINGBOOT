package com.spring.voluptuaria.service;

import com.spring.voluptuaria.exception.NotFoundException;
import com.spring.voluptuaria.model.Passagem;
import com.spring.voluptuaria.repository.PassagemRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class PassagemService {

    private final PassagemRepository passagemRepository;
    private final EmpresaService empresaService;
    private final PacoteService pacoteService;

    public List<Passagem> findAll() {
        return passagemRepository.findAll();
    }

    public Passagem findById(Long id) throws NotFoundException {

        return passagemRepository.findById(id).orElseThrow(()->new NotFoundException(id));
    }

    public Passagem save(Passagem passagem) throws NotFoundException {
         passagem.setEmpresa(empresaService.findById(passagem.getIdEmpresa()));
         passagem.setPacote(pacoteService.findById(passagem.getIdPacote()));
         return passagemRepository.save(passagem);
    }

    public void delete(Passagem passagem){
        passagemRepository.delete(passagem);
    }

    public List<Passagem> findAllWithId(Long cod){
        return  findAll().stream()
                .filter(p->p.getIdPacote()==cod)
                .collect(Collectors.toList());
        }

}
