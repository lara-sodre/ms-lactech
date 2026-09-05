package com.github.lara.sodre.ms_lactech.service;

import com.github.lara.sodre.ms_lactech.dto.ConsultaRequestDTO;
import com.github.lara.sodre.ms_lactech.dto.ConsultaResponseDTO;
import com.github.lara.sodre.ms_lactech.entities.Apoio;
import com.github.lara.sodre.ms_lactech.entities.Consulta;
import com.github.lara.sodre.ms_lactech.entities.Doadora;
import com.github.lara.sodre.ms_lactech.entities.Profissional;
import com.github.lara.sodre.ms_lactech.exceptions.DatabaseException;
import com.github.lara.sodre.ms_lactech.exceptions.ResourceNotFoundException;
import com.github.lara.sodre.ms_lactech.repositories.ApoioRepository;
import com.github.lara.sodre.ms_lactech.repositories.ConsultaRepository;
import com.github.lara.sodre.ms_lactech.repositories.DoadoraRepository;
import com.github.lara.sodre.ms_lactech.repositories.ProfissionalRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private DoadoraRepository doadoraRepository;

    @Autowired
    private ApoioRepository apoioRepository;

    @Autowired
    private ProfissionalRepository profissionalRepository;

    @Transactional(readOnly = true)
    public List<ConsultaResponseDTO> findAllConsultas() {

        return consultaRepository.findAll()
                .stream().map(ConsultaResponseDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public ConsultaResponseDTO findConsultaById(Long id) {

        Consulta consulta = consultaRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado. ID: " + id)
        );

        return new ConsultaResponseDTO(consulta);
    }

    @Transactional
    public ConsultaResponseDTO saveConsulta(ConsultaRequestDTO inputDto) {

        Consulta consulta = new Consulta();
        copyDtoToConsulta(inputDto, consulta);
        consulta = consultaRepository.save(consulta);
        return new ConsultaResponseDTO(consulta);
    }

    @Transactional
    public ConsultaResponseDTO updateConsulta(Long id, ConsultaRequestDTO inputDto) {

        try {
            Consulta consulta = consultaRepository.getReferenceById(id);
            copyDtoToConsulta(inputDto, consulta);
            consulta = consultaRepository.save(consulta);
            return new ConsultaResponseDTO(consulta);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Recurso não encontrado. ID: " + id);
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void deleteConsultaById(Long id) {

        if (!consultaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Recurso não encontrado. ID: " + id);
        }

        try {
            consultaRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Não foi possível excluir consulta. Existem avaliações associadas a ela");
        }
    }

    private void copyDtoToConsulta(ConsultaRequestDTO inputDto, Consulta consulta) {

        consulta.setData(inputDto.getData());
        consulta.setHorario(inputDto.getHorario());
        consulta.setMotivo(inputDto.getMotivo());
        consulta.setInformacoesAdicionais(inputDto.getInformacoesAdicionais());
        consulta.setStatus(inputDto.getStatus());

        Doadora doadora = doadoraRepository.findById(inputDto.getDoadoraId()).orElseThrow(
                () -> new DatabaseException("Não foi possível salvar Consulta. Doadora inexistente. "
                        + "(ID: " + inputDto.getDoadoraId() + ")")
        );

        Apoio apoio = apoioRepository.findById(inputDto.getApoioId()).orElseThrow(
                () -> new DatabaseException("Não foi possível salvar Consulta. Apoio inexistente. "
                        + "(ID: " + inputDto.getApoioId() + ")")
        );

        Profissional profissional = profissionalRepository.findById(inputDto.getProfissionalId()).orElseThrow(
                () -> new DatabaseException("Não foi possível salvar Consulta. Profissional inexistente. "
                        + "(ID: " + inputDto.getProfissionalId() + ")")
        );

        consulta.setDoadora(doadora);
        consulta.setApoio(apoio);
        consulta.setProfissional(profissional);
    }

}