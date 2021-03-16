package com.rafa.web.api.service;

import com.rafa.web.api.domain.Responsavel;
import com.rafa.web.api.repository.ResponsavelRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static com.rafa.web.api.domain.Perfil.RESPONSAVEL;

@Service
public class ResponsavelService {

    private final ResponsavelRepository responsavelRepository;
    private final TerapeutaService terapeutaService;

    public ResponsavelService(ResponsavelRepository responsavelRepository, TerapeutaService terapeutaService) {
        this.responsavelRepository = responsavelRepository;
        this.terapeutaService = terapeutaService;
    }

    public Responsavel cadastrarResponsavel(Responsavel responsavel, Long idTerapeuta) throws Exception {
        vereficarSeTerapeutaExiste(idTerapeuta);
        responsavel.getLogin().setPerfil(RESPONSAVEL);
        responsavel.getProtegidos().forEach(protegido -> protegido.setIdTerapeuta(idTerapeuta));
        return responsavelRepository.save(responsavel);
    }

    private void vereficarSeTerapeutaExiste(Long idTerapeuta) throws Exception {
        terapeutaService.buscarTerapeutaPeloId(idTerapeuta);
    }

    public Page<Responsavel> listarResponsaveis(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return responsavelRepository.findAll(pageable);
    }

    public Responsavel buscarResponsavelPeloId(Long id) {
        return responsavelRepository.findById(id).orElseThrow();
    }

}
