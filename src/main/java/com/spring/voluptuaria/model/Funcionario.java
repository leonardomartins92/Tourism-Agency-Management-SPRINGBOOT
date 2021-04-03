package com.spring.voluptuaria.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String cpf;
    @NotNull
    private String nome;
    @NotNull
    private String telefone;
    @Column(nullable = false, unique = true)
    private String email;
    @NotNull
    private String logradouro;
    @NotNull
    private String numero;
    private String complemento;
    @NotNull
    private String uf;
    @NotNull
    private String localidade;
    @NotNull
    private String cep;
    @NotNull
    private String senha;
    @ManyToOne
    private TipoFuncionario tipoFuncionario;
    private Long idTipoFuncionario;
    private String roles;

    public Funcionario(Long id, String cpf, String nome, String telefone, String email, String logradouro, String numero, String complemento, String uf, String localidade, String cep, String senha, TipoFuncionario tipoFuncionario) {
        this.id = id;
        this.cpf = cpf;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.uf = uf;
        this.localidade = localidade;
        this.cep = cep;
        this.senha = senha;
        this.tipoFuncionario = tipoFuncionario;

    }


}
