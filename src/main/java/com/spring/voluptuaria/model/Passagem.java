package com.spring.voluptuaria.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataIda;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataVolta;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Pacote pacote;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Empresa empresa;
    private Long idPacote;
    private Long idEmpresa;

    public Passagem(Long id, String origem, String destino, LocalDate dataIda, LocalDate dataVolta, Pacote pacote, Empresa empresa) {
        this.id = id;
        this.origem = origem;
        this.destino = destino;
        this.dataIda = dataIda;
        this.dataVolta = dataVolta;
        this.pacote = pacote;
        this.empresa = empresa;
    }
}
