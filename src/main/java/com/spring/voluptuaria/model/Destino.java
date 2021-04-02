package com.spring.voluptuaria.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Calendar;

@Data
@NoArgsConstructor
@Entity
public class Destino {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
  //  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate inicio;
    @NotNull
  //  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fim;

    private Long idPacote;
    private Long idEmpresa;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Pacote pacote;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Empresa empresa;

    public Destino(Long id, LocalDate inicio, LocalDate fim, Pacote pacote, Empresa empresa) {
        this.id = id;
        this.inicio = inicio;
        this.fim = fim;
        this.pacote = pacote;
        this.empresa = empresa;
    }


}
