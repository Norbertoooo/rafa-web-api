package com.rafa.web.api.web;

import com.rafa.web.api.domain.Paciente;
import com.rafa.web.api.service.PacienteService;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/pacientes")
@Slf4j
public class PacienteResource {

    private final PacienteService pacienteService;

    public PacienteResource(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @GetMapping
    public ResponseEntity<Page<Paciente>> listarPacientes() {
        return ResponseEntity.ok(pacienteService.listarPacientes(0, 10));
    }

    @GetMapping("/terapeuta")
    public ResponseEntity<Page<Paciente>> listarPacientesDeUmTerapeuta(HttpServletRequest request) throws NotFoundException {
        return ResponseEntity.ok(pacienteService.listarPacientesDeUmTerapeuta(0, 10, request));
    }

    @GetMapping("/responsavel")
    public ResponseEntity<Page<Paciente>> listarPacientesDeUmResponsavel(HttpServletRequest request) throws NotFoundException {
        return ResponseEntity.ok(pacienteService.listarPacientesDeUmResponsavel(0, 10, request));
    }

}
