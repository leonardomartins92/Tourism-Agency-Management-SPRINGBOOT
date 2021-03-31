package com.spring.voluptuaria.repository;

import com.spring.voluptuaria.model.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
}
