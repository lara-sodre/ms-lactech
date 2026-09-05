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
@Table(name = "DOADORA")
public class Doadora {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String cpf;

    @Column(nullable = false, length = 255)
    private String nome;

    @Column(nullable = false)
    private LocalDate dataNascimento;

    @Column(nullable = false, length = 20)
    private String telefone;

    @Column(nullable = false, length = 255)
    private String email;

    @Column(nullable = false, length = 255)
    private String endereco;

    @Column(nullable = false, length = 255)
    private String bairro;

    @Column(nullable = false, length = 255)
    private String cidade;

    @Column(nullable = false, length = 9)
    private String cep;

    @Column(nullable = false, length = 20)
    private String usuario;

    @Column(nullable = false, length = 255)
    private String senha;

    @OneToOne(mappedBy = "doadora", cascade = CascadeType.ALL, orphanRemoval = true)
    private Informacao informacao;

    @OneToMany(mappedBy = "doadora", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Coleta> coletas = new ArrayList<>();

    @OneToMany(mappedBy = "doadora")
    private List<Consulta> consultas = new ArrayList<>();

    @OneToMany(mappedBy = "doadora")
    private List<Avaliacao> avaliacoes = new ArrayList<>();
}