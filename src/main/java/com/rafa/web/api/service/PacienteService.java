package com.rafa.web.api.service;

import com.rafa.web.api.domain.Paciente;
import com.rafa.web.api.repository.PacienteRepository;
import com.rafa.web.api.web.exceptionHandler.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

import static com.rafa.web.api.shared.Constantes.Erro.PACIENTE_NAO_ENCONTRADO;

@Service
public class PacienteService {

    private final PacienteRepository pacienteRepository;
    private final TerapeutaService terapeutaService;

    public PacienteService(PacienteRepository pacienteRepository, TerapeutaService terapeutaService) {
        this.pacienteRepository = pacienteRepository;
        this.terapeutaService = terapeutaService;
    }

    public Page<Paciente> listarPacientes(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return pacienteRepository.findAll(pageable);
    }

    public Page<Paciente> listarPacientesDeUmTerapeuta(Integer page, Integer size, HttpServletRequest request) throws javassist.NotFoundException {
        Long id = terapeutaService.buscarTerapeutaPeloEmail(request).getId();
        Pageable pageable = PageRequest.of(page, size);
        return pacienteRepository.findAllByIdTerapeuta(id, pageable);
    }

    public Page<Paciente> listarPacientesDeUmResponsavel(Integer page, Integer size, HttpServletRequest request) throws javassist.NotFoundException {
        Long id = terapeutaService.buscarTerapeutaPeloEmail(request).getId();
        Pageable pageable = PageRequest.of(page, size);
        return pacienteRepository.findAllByIdResponsavel(id, pageable);
    }

    public Paciente buscarPacientePeloId(Long id) {
        return pacienteRepository.findById(id).orElseThrow(() -> new NotFoundException(PACIENTE_NAO_ENCONTRADO));
    }


}
