package com.github.lara.sodre.ms_lactech.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "AVALIACAO")
public class Avaliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate data;

    @Column(nullable = false)
    private Integer nota;

    @Column(length = 500)
    private String comentario;

    @ManyToOne
    @JoinColumn(name = "DOADORA_ID_DOADORA", nullable = false)
    private Doadora doadora;

    @ManyToOne
    @JoinColumn(name = "APOIO_ID_APOIO", nullable = false)
    private Apoio apoio;

    @ManyToOne
    @JoinColumn(name = "PROFISSIONAL_ID_PROFISSIONAL", nullable = false)
    private Profissional profissional;

    @ManyToOne
    @JoinColumn(name = "CONSULTA_ID_CONSULTA", nullable = false)
    private Consulta consulta;
}