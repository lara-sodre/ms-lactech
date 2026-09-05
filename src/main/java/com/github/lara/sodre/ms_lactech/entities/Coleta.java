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
@Table(name = "COLETA")
public class Coleta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate data;

    @Column(nullable = false)
    private Double volume;

    @Column(nullable = false, length = 20)
    private String status;

    @Column(length = 200)
    private String observacoes;

    @ManyToOne
    @JoinColumn(name = "DOADORA_ID_DOADORA", nullable = false)
    private Doadora doadora;
}