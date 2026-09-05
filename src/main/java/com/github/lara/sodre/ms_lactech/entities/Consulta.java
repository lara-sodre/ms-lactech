package com.github.lara.sodre.ms_lactech.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "CONSULTA")
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate data;

    @Column(nullable = false, length = 10)
    private String horario;

    @Column(nullable = false, length = 150)
    private String motivo;

    @Column(length = 200)
    private String informacoesAdicionais;

    @Column(nullable = false, length = 20)
    private String status;

    @ManyToOne
    @JoinColumn(name = "DOADORA_ID_DOADORA", nullable = false)
    private Doadora doadora;

    @ManyToOne
    @JoinColumn(name = "APOIO_ID_APOIO", nullable = false)
    private Apoio apoio;

    @ManyToOne
    @JoinColumn(name = "PROFISSIONAL_ID_PROFISSIONAL", nullable = false)
    private Profissional profissional;

    @OneToMany(mappedBy = "consulta")
    private List<Avaliacao> avaliacoes = new ArrayList<>();
}