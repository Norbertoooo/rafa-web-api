package com.rafa.web.api.web;

import com.rafa.web.api.config.jwt.JwtTokenUtil;
import com.rafa.web.api.domain.Paciente;
import com.rafa.web.api.service.PacienteService;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/pacientes")
@Slf4j
public class PacienteResource {

    private final PacienteService pacienteService;
    private final JwtTokenUtil jwtTokenUtil;

    public PacienteResource(PacienteService pacienteService, JwtTokenUtil jwtTokenUtil) {
        this.pacienteService = pacienteService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @GetMapping
    public ResponseEntity<Page<Paciente>> listarPacientes() {
        return ResponseEntity.ok(pacienteService.listarPacientes(0, 10));
    }

    @GetMapping("/terapeuta")
    public ResponseEntity<Page<Paciente>> listarPacientesDeUmTerapeuta(HttpServletRequest request) throws NotFoundException {
        String email = jwtTokenUtil.obterEmailLogado(request);
        log.debug("Request para listar pacientes do terapeuta: {}", email);
        return ResponseEntity.ok(pacienteService.listarPacientesDeUmTerapeuta(0, 10, email));
    }

    @GetMapping("/responsavel")
    public ResponseEntity<Page<Paciente>> listarPacientesDeUmResponsavel(HttpServletRequest request) throws NotFoundException {
        String email = jwtTokenUtil.obterEmailLogado(request);
        return ResponseEntity.ok(pacienteService.listarPacientesDeUmResponsavel(0, 10, email));
    }

}
