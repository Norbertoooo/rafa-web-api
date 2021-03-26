package com.rafa.web.api.web;

import com.rafa.web.api.domain.Paciente;
import com.rafa.web.api.service.PacienteService;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/pacientes")
@Slf4j
public class PacienteResource {

    private final PacienteService pacienteService;

    public PacienteResource(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @GetMapping
    public ResponseEntity<List<Paciente>> listarPacientes() {
        return ResponseEntity.ok(pacienteService.listarPacientes());
    }

    @GetMapping("/terapeuta")
    public ResponseEntity<List<Paciente>> listarPacientesDeUmTerapeuta(HttpServletRequest request) throws NotFoundException {
        return ResponseEntity.ok(pacienteService.listarPacientesDeUmTerapeuta(request));
    }

    @GetMapping("/responsavel")
    public ResponseEntity<List<Paciente>> listarPacientesDeUmResponsavel(HttpServletRequest request) throws NotFoundException {
        return ResponseEntity.ok(pacienteService.listarPacientesDeUmResponsavel(request));
    }

}
