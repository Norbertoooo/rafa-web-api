package com.rafa.web.api.web;

import com.rafa.web.api.domain.Paciente;
import com.rafa.web.api.service.PacienteService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pacientes")
@CrossOrigin(origins = "*")
public class PacienteResource {

    private final PacienteService pacienteService;

    public PacienteResource(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @GetMapping
    public ResponseEntity<Page<Paciente>> listarPacientes() {
        return ResponseEntity.ok(pacienteService.listarPacientes(0, 10));
    }

    @GetMapping("/terapeuta/{id}")
    public ResponseEntity<Page<Paciente>> listarPacientesDeUmTerapeuta(@RequestParam Long terapeutaId) {
        return ResponseEntity.ok(pacienteService.listarPacientesDeUmTerapeuta(10, 0, terapeutaId));
    }

    @GetMapping("/responsavel/{id}")
    public ResponseEntity<Page<Paciente>> listarPacientesDeUmResponsavel(@RequestParam Long terapeutaId) {
        return ResponseEntity.ok(pacienteService.listarPacientesDeUmResponsavel(10, 0, terapeutaId));
    }

}
