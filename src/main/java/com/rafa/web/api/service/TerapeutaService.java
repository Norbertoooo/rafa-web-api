package com.rafa.web.api.service;

import com.rafa.web.api.domain.Terapeuta;
import com.rafa.web.api.repository.TerapeutaRepository;
import com.rafa.web.api.web.exceptionHandler.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import static com.rafa.web.api.domain.Perfil.TERAPEUTA;
import static com.rafa.web.api.shared.Constantes.Erro.TERAPEUTA_NAO_ENCONTRADO;

@Service
public class TerapeutaService {

    private final TerapeutaRepository terapeutaRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public TerapeutaService(TerapeutaRepository terapeutaRepository, BCryptPasswordEncoder passwordEncoder) {
        this.terapeutaRepository = terapeutaRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Page<Terapeuta> listarTerapeutas(Integer pagina, Integer tamanho) {
        Pageable pageable = PageRequest.of(pagina, tamanho);
        return terapeutaRepository.findAll(pageable);
    }

    public Terapeuta buscarTerapeutaPeloId(Long id) throws NotFoundException {
        return terapeutaRepository.findById(id).orElseThrow(() -> new NotFoundException(TERAPEUTA_NAO_ENCONTRADO));
    }

    public Terapeuta cadastrarTerapeuta(Terapeuta terapeuta) {
        terapeuta.getLogin().setPerfil(TERAPEUTA);
        terapeuta.getLogin().setSenha(passwordEncoder.encode(terapeuta.getLogin().getSenha()));
        return terapeutaRepository.save(terapeuta);
    }
}
