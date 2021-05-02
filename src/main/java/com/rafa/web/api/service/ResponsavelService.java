package com.rafa.web.api.service;

import com.rafa.web.api.domain.Responsavel;
import com.rafa.web.api.repository.ResponsavelRepository;
import com.rafa.web.api.web.exceptionHandler.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static com.rafa.web.api.domain.Perfil.RESPONSAVEL;
import static com.rafa.web.api.shared.Constantes.Erro.RESPONSAVEL_NAO_ENCONTRADO;

@Service
public class ResponsavelService {

    private final ResponsavelRepository responsavelRepository;
    private final TerapeutaService terapeutaService;
    private final EmailService emailService;

    public ResponsavelService(ResponsavelRepository responsavelRepository, TerapeutaService terapeutaService, EmailService emailService) {
        this.responsavelRepository = responsavelRepository;
        this.terapeutaService = terapeutaService;
        this.emailService = emailService;
    }

    public Responsavel cadastrarResponsavel(Responsavel responsavel, Long idTerapeuta) {
        vereficarSeTerapeutaExiste(idTerapeuta);
        responsavel.getLogin().setPerfil(RESPONSAVEL);
        responsavel.getProtegidos().forEach(protegido -> protegido.setIdTerapeuta(idTerapeuta));
        Responsavel responsavelSalvo = responsavelRepository.save(responsavel);
        emailService.enviarEmail(responsavel.getLogin());
        return responsavelSalvo;
    }

    private void vereficarSeTerapeutaExiste(Long idTerapeuta) {
        terapeutaService.buscarTerapeutaPeloId(idTerapeuta);
    }

    public Page<Responsavel> listarResponsaveis(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return responsavelRepository.findAll(pageable);
    }

    public Responsavel buscarResponsavelPeloId(Long id) {
        return responsavelRepository.findById(id).orElseThrow( () -> new NotFoundException(RESPONSAVEL_NAO_ENCONTRADO));
    }

    public Responsavel buscarResponsavelPeloEmail(String email) {
        return responsavelRepository.findByLoginEmail(email).orElseThrow( () -> new NotFoundException(RESPONSAVEL_NAO_ENCONTRADO));
    }

}
