package com.spring.voluptuaria.repository;

import com.spring.voluptuaria.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
}
