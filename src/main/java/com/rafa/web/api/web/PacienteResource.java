package com.rafa.web.api.web;

import com.rafa.web.api.domain.Paciente;
import com.rafa.web.api.domain.Responsavel;
import com.rafa.web.api.service.PacienteService;
import javassist.NotFoundException;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/pacientes")
@Slf4j
public class PacienteResource {

    private final PacienteService pacienteService;

    public PacienteResource(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @GetMapping
    public ResponseEntity<List<Paciente>> listarPacientes() {
        log.info("Requisação para listar pacientes");
        return ResponseEntity.ok(pacienteService.listarPacientes());
    }

    @GetMapping("/{nome}")
    public ResponseEntity<Paciente> buscarPacientePeloNome(@PathVariable String nome) {
        log.info("Requisação para buscar paciente de nome: {}", nome);
        return ResponseEntity.ok(pacienteService.buscarPacientePeloNome(nome));
    }

    @GetMapping("/terapeuta")
    public ResponseEntity<List<Paciente>> listarPacientesDeUmTerapeuta(HttpServletRequest request) throws NotFoundException {
        log.info("Requisação para listar pacientes do terapeuta logado");
        return ResponseEntity.ok(pacienteService.listarPacientesDeUmTerapeuta(request));
    }

    @GetMapping("/responsavel")
    public ResponseEntity<List<Paciente>> listarPacientesDeUmResponsavel(HttpServletRequest request) throws NotFoundException {
        log.info("Requisação para listar pacientes do responsavel logado");
        return ResponseEntity.ok(pacienteService.listarPacientesDeUmResponsavel(request));
    }

    @PostMapping()
    public ResponseEntity<Paciente> cadastrarPaciente(@RequestBody Paciente paciente, HttpServletRequest request) throws Exception {
        log.info("Requisação cadastrar paciente: {}", paciente);
        return ResponseEntity.status(HttpStatus.CREATED).body(pacienteService.cadastrarPaciente(request, paciente));
    }

}
