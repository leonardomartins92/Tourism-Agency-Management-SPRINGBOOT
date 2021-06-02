package com.spring.voluptuaria.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Pacote {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Funcionario funcionario;

    private Long idFuncionario;
    private Long idCliente;

    public Pacote(Long id, Cliente cliente, Funcionario funcionario) {
        this.id = id;
        this.cliente = cliente;
        this.funcionario = funcionario;
    }


}
