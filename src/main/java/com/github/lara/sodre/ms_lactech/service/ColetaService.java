package com.github.lara.sodre.ms_lactech.service;

import com.github.lara.sodre.ms_lactech.dto.ColetaRequestDTO;
import com.github.lara.sodre.ms_lactech.dto.ColetaResponseDTO;
import com.github.lara.sodre.ms_lactech.entities.Coleta;
import com.github.lara.sodre.ms_lactech.entities.Doadora;
import com.github.lara.sodre.ms_lactech.exceptions.DatabaseException;
import com.github.lara.sodre.ms_lactech.exceptions.ResourceNotFoundException;
import com.github.lara.sodre.ms_lactech.repositories.ColetaRepository;
import com.github.lara.sodre.ms_lactech.repositories.DoadoraRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ColetaService {

    @Autowired
    private ColetaRepository coletaRepository;

    @Autowired
    private DoadoraRepository doadoraRepository;

    @Transactional(readOnly = true)
    public List<ColetaResponseDTO> findAllColeta() {

        return coletaRepository.findAll()
                .stream().map(ColetaResponseDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public ColetaResponseDTO findColetaById(Long id) {

        Coleta coleta = coletaRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado. ID: " + id)
        );

        return new ColetaResponseDTO(coleta);
    }

    @Transactional
    public ColetaResponseDTO saveColeta(ColetaRequestDTO inputDto) {

        Coleta coleta = new Coleta();
        copyDtoToColeta(inputDto, coleta);
        coleta = coletaRepository.save(coleta);
        return new ColetaResponseDTO(coleta);
    }

    @Transactional
    public ColetaResponseDTO updateColeta(Long id, ColetaRequestDTO inputDto) {

        try {
            Coleta coleta = coletaRepository.getReferenceById(id);
            copyDtoToColeta(inputDto, coleta);
            coleta = coletaRepository.save(coleta);
            return new ColetaResponseDTO(coleta);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Recurso não encontrado. ID: " + id);
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void deleteColetaById(Long id) {

        if (!coletaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Recurso não encontrado. ID: " + id);
        }

        try {
            coletaRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Não foi possível excluir coleta");
        }
    }

    private void copyDtoToColeta(ColetaRequestDTO inputDto, Coleta coleta) {

        coleta.setData(inputDto.getData());
        coleta.setVolume(inputDto.getVolume());
        coleta.setStatus(inputDto.getStatus());
        coleta.setObservacoes(inputDto.getObservacoes());

        Doadora doadora = doadoraRepository.findById(inputDto.getDoadoraId()).orElseThrow(
                () -> new DatabaseException("Não foi possível salvar Coleta. Doadora inexistente. "
                        + "(ID: " + inputDto.getDoadoraId() + ")")
        );

        coleta.setDoadora(doadora);
    }
}