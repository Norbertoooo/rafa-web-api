package com.rafa.web.api.service;

import com.rafa.web.api.config.jwt.JwtTokenUtil;
import com.rafa.web.api.domain.Paciente;
import com.rafa.web.api.domain.Terapeuta;
import com.rafa.web.api.repository.PacienteRepository;
import com.rafa.web.api.web.exceptionHandler.NotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

import static com.rafa.web.api.domain.Perfil.RESPONSAVEL;
import static com.rafa.web.api.shared.Constantes.Erro.PACIENTE_NAO_ENCONTRADO;
import static com.rafa.web.api.shared.Constantes.SENHA_PADRAO_RESPONSAVEL;

@Service
public class PacienteService {

    private final PacienteRepository pacienteRepository;
    private final TerapeutaService terapeutaService;
    private final ResponsavelService responsavelService;
    private final JwtTokenUtil jwtTokenUtil;
    private final BCryptPasswordEncoder passwordEncoder;

    public PacienteService(PacienteRepository pacienteRepository, TerapeutaService terapeutaService, ResponsavelService responsavelService, JwtTokenUtil jwtTokenUtil, BCryptPasswordEncoder passwordEncoder) {
        this.pacienteRepository = pacienteRepository;
        this.terapeutaService = terapeutaService;
        this.responsavelService = responsavelService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Paciente> listarPacientes() {
        return pacienteRepository.findAll();
    }

    public List<Paciente> listarPacientesDeUmTerapeuta(HttpServletRequest request) throws javassist.NotFoundException {
        Long id = terapeutaService.buscarTerapeutaPeloEmail(request).getId();
        return pacienteRepository.findAllByIdTerapeuta(id);
    }

    public List<Paciente> listarPacientesDeUmResponsavel(HttpServletRequest request) throws javassist.NotFoundException {
        String email = jwtTokenUtil.obterEmailLogado(request);
        Long id = responsavelService.buscarResponsavelPeloEmail(email).getId();
        return pacienteRepository.findAllByResponsaveis_Id(id);
    }

    public Paciente buscarPacientePeloId(Long id) {
        return pacienteRepository.findById(id).orElseThrow(() -> new NotFoundException(PACIENTE_NAO_ENCONTRADO));
    }

    public Paciente buscarPacientePeloNome(String nome) {
        return pacienteRepository.findByNomeCompletoLike(nome).orElseThrow(() -> new NotFoundException(PACIENTE_NAO_ENCONTRADO));
    }

    public Paciente cadastrarPaciente(HttpServletRequest request, Paciente paciente) throws javassist.NotFoundException {
        Terapeuta terapeuta = terapeutaService.buscarTerapeutaPeloEmail(request);
        paciente.setIdTerapeuta(terapeuta.getId());
        paciente.getResponsaveis().forEach(responsavel -> {
            responsavel.getLogin().setSenhaEncriptada(SENHA_PADRAO_RESPONSAVEL);
            responsavel.getLogin().setPerfil(RESPONSAVEL);
        });
        return pacienteRepository.save(paciente);
    }
}
