package com.github.lara.sodre.ms_lactech.dto;

import com.github.lara.sodre.ms_lactech.entities.Coleta;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ColetaResponseDTO {

    private Long id;
    private LocalDate data;
    private Double volume;
    private String status;
    private String observacoes;
    private DoadoraResponseDTO doadora;

    public ColetaResponseDTO(Coleta coleta) {
        id = coleta.getId();
        data = coleta.getData();
        volume = coleta.getVolume();
        status = coleta.getStatus();
        observacoes = coleta.getObservacoes();
        doadora = new DoadoraResponseDTO(coleta.getDoadora());
    }
}