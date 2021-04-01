package com.spring.voluptuaria.model;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class Passagem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String origem;

    @NotNull
    private String destino;

    @NotNull
   // @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private String dataIda;

    @NotNull
   // @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private String dataVolta;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Pacote pacote;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Empresa empresa;
    private Long idPacote;
    private Long idEmpresa;

    public Passagem(Long id, String origem, String destino, String dataIda, String dataVolta, Pacote pacote, Empresa empresa) {
        this.id = id;
        this.origem = origem;
        this.destino = destino;
        this.dataIda = dataIda;
        this.dataVolta = dataVolta;
        this.pacote = pacote;
        this.empresa = empresa;
    }
}
