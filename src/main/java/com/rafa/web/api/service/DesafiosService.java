package com.rafa.web.api.service;

import com.rafa.web.api.domain.Desafios;
import com.rafa.web.api.repository.DesafiosRepository;
import com.rafa.web.api.web.exceptionHandler.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.rafa.web.api.shared.Constantes.Erro.DESAFIO_NAO_ENCONTRADO;

@Service
@RequiredArgsConstructor
public class DesafiosService {

    private final DesafiosRepository desafiosRepository;

    public Desafios salvarDesafio(Desafios desafio) {
        return desafiosRepository.save(desafio);
    }

    public Desafios atualizarDesafio(Long id, Desafios desafio) {
        Desafios desafioExistente = buscarPorId(id);
        desafioExistente.setPergunta(desafio.getPergunta());
        desafioExistente.setResposta(desafio.getResposta());
        desafioExistente.setScoreInicial(desafio.getScoreInicial());
        return desafiosRepository.save(desafioExistente);
    }

    public Page<Desafios> listarDesafios(Integer pagina, Integer tamanho) {
        return desafiosRepository.findAll(PageRequest.of(pagina,tamanho));
    }

    public void deletarDesafio(Long id) {
        desafiosRepository.deleteById(id);
    }

    private Desafios buscarPorId(Long id) {
        return desafiosRepository.findById(id).orElseThrow( () -> new NotFoundException(DESAFIO_NAO_ENCONTRADO + id) );
    }

}
