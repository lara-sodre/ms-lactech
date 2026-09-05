package com.github.lara.sodre.ms_lactech.dto;

import com.github.lara.sodre.ms_lactech.entities.Avaliacao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AvaliacaoResponseDTO {

    private Long id;
    private LocalDate data;
    private Integer nota;
    private String comentario;
    private DoadoraResponseDTO doadora;
    private ApoioResponseDTO apoio;
    private ProfissionalResponseDTO profissional;
    private ConsultaResponseDTO consulta;

    public AvaliacaoResponseDTO(Avaliacao avaliacao) {
        id = avaliacao.getId();
        data = avaliacao.getData();
        nota = avaliacao.getNota();
        comentario = avaliacao.getComentario();
        doadora = new DoadoraResponseDTO(avaliacao.getDoadora());
        apoio = new ApoioResponseDTO(avaliacao.getApoio());
        profissional = new ProfissionalResponseDTO(avaliacao.getProfissional());
        consulta = new ConsultaResponseDTO(avaliacao.getConsulta());
    }
}