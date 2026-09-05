package com.github.lara.sodre.ms_lactech.controller;

import com.github.lara.sodre.ms_lactech.dto.ColetaRequestDTO;
import com.github.lara.sodre.ms_lactech.dto.ColetaResponseDTO;
import com.github.lara.sodre.ms_lactech.service.ColetaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/coletas")
public class ColetaController {

    @Autowired
    private ColetaService coletaService;

    @GetMapping
    public ResponseEntity<List<ColetaResponseDTO>> getAllColetas() {

        List<ColetaResponseDTO> coletas = coletaService.findAllColeta();

        return ResponseEntity.ok(coletas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ColetaResponseDTO> getColetaById(@PathVariable Long id) {

        ColetaResponseDTO coletaDto = coletaService.findColetaById(id);
        return ResponseEntity.ok(coletaDto);
    }

    @PostMapping
    public ResponseEntity<ColetaResponseDTO> createColeta(@RequestBody @Valid
                                                          ColetaRequestDTO inputDTO) {

        ColetaResponseDTO coletaDto = coletaService.saveColeta(inputDTO);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(coletaDto.getId())
                .toUri();

        return ResponseEntity.created(uri).body(coletaDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ColetaResponseDTO> updateColeta(@PathVariable Long id,
                                                          @Valid @RequestBody
                                                          ColetaRequestDTO inputDTO) {

        ColetaResponseDTO coletaDto = coletaService.updateColeta(id, inputDTO);
        return ResponseEntity.ok(coletaDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteColeta(@PathVariable Long id) {

        coletaService.deleteColetaById(id);

        return ResponseEntity.noContent().build();
    }

}