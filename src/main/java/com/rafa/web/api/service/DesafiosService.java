package com.rafa.web.api.service;

import com.rafa.web.api.domain.Desafio;
import com.rafa.web.api.repository.DesafiosRepository;
import com.rafa.web.api.web.exceptionHandler.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import static com.rafa.web.api.shared.Constantes.Erro.DESAFIO_NAO_ENCONTRADO;

@Service
@RequiredArgsConstructor
public class DesafiosService {

    private final DesafiosRepository desafiosRepository;

    public Desafio salvarDesafio(Desafio desafio) {
        return desafiosRepository.save(desafio);
    }

    public Desafio atualizarDesafio(Long id, Desafio desafio) {
        Desafio desafioExistente = buscarPorId(id);
        desafioExistente.setPergunta(desafio.getPergunta());
        desafioExistente.setResposta(desafio.getResposta());
        desafioExistente.setScoreInicial(desafio.getScoreInicial());
        return desafiosRepository.save(desafioExistente);
    }

    public Page<Desafio> listarDesafios(Integer pagina, Integer tamanho) {
        return desafiosRepository.findAll(PageRequest.of(pagina,tamanho));
    }

    public void deletarDesafio(Long id) {
        desafiosRepository.deleteById(id);
    }

    private Desafio buscarPorId(Long id) {
        return desafiosRepository.findById(id).orElseThrow( () -> new NotFoundException(DESAFIO_NAO_ENCONTRADO + id) );
    }

}
