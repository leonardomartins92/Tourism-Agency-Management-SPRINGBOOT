package com.spring.voluptuaria.repository;

import com.spring.voluptuaria.model.Pacote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PacoteRepository extends JpaRepository<Pacote, Long> {
    List<Pacote> findPacotesByFuncionario_Id(Long id);
}
