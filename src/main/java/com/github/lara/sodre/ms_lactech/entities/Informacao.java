package com.github.lara.sodre.ms_lactech.entities;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "INFORMACAO")
public class Informacao {

    @Id
    @Column(name = "ID_INFORMACAO")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String idadeBebe;

    @Column(nullable = false, length = 5)
    private String amamentando;

    @Column(nullable = false, length = 5)
    private String usoMedicamento;

    @Column(nullable = false, length = 5)
    private String metodoColeta;

    @Column(length = 255)
    private String alergia;

    @Column(length = 255)
    private String doencaCronicas;

    @Column(length = 255)
    private String observacoes;

    @Column(nullable = false, length = 5)
    private String fuma;

    @Column(nullable = false, length = 5)
    private String alcool;

    @Column(nullable = false, length = 5)
    private String consentimento;

    @OneToOne
    @JoinColumn(name = "DOADORA_ID_DOADORA", nullable = false, unique = true)
    private Doadora doadora;
}