package com.spring.voluptuaria.model;

import com.sun.istack.NotNull;
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
public class Empresa {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false,unique = true)
    private String cnpj;
    @NotNull
    private String nome;
    @NotNull
    private String telefone;
    @Column(nullable = false,unique = true)
    private String email;
    @NotNull
    private String logradouro;
    @NotNull
    private String numero;
    private String complemento;
    @NotNull
    private String uf;
    @NotNull
    private String cep;
    @NotNull
    private String localidade;
    @ManyToOne
    private TipoEmpresa tipoEmpresa;
    private Long idTipoEmpresa;

    public Empresa(Long id, String cnpj, String nome, String telefone, String email, String logradouro, String numero, String complemento, String uf, String cep, String localidade, TipoEmpresa tipoEmpresa) {
        this.id = id;
        this.cnpj = cnpj;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.uf = uf;
        this.cep = cep;
        this.localidade = localidade;
        this.tipoEmpresa = tipoEmpresa;
    }
}
