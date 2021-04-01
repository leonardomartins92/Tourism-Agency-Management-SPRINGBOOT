package com.spring.voluptuaria.model;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Data
@NoArgsConstructor
@Entity
public class Destino {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
   // @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private String inicio;
    @NotNull
   // @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private String fim;

    private Long idPacote;
    private Long idEmpresa;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Pacote pacote;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Empresa empresa;

    public Destino(Long id, String inicio, String fim, Pacote pacote, Empresa empresa) {
        this.id = id;
        this.inicio = inicio;
        this.fim = fim;
        this.pacote = pacote;
        this.empresa = empresa;
    }


}
