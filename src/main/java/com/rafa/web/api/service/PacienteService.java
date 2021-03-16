package com.rafa.web.api.service;

import com.rafa.web.api.domain.Paciente;
import com.rafa.web.api.repository.PacienteRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PacienteService {

    private final PacienteRepository pacienteRepository;

    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    public Page<Paciente> listarPacientes(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return pacienteRepository.findAll(pageable);
    }

    public Page<Paciente> listarPacientesDeUmTerapeuta(Integer page, Integer size, Long id) {
        Pageable pageable = PageRequest.of(page, size);
        return pacienteRepository.findAllByIdTerapeuta(id, pageable);
    }

    public Page<Paciente> listarPacientesDeUmResponsavel(Integer page, Integer size, Long id) {
        Pageable pageable = PageRequest.of(page, size);
        return pacienteRepository.findAllByIdResponsavel(id, pageable);
    }

}
