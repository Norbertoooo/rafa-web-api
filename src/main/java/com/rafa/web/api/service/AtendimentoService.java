package com.rafa.web.api.service;

import com.rafa.web.api.domain.Atendimento;
import com.rafa.web.api.repository.AtendimentoRepository;
import com.rafa.web.api.web.exceptionHandler.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.rafa.web.api.shared.Constantes.Erro.ATENDIMENTO_NAO_ENCONTRADO;

@Service
@RequiredArgsConstructor
public class AtendimentoService {

    private final AtendimentoRepository atendimentoRepository;

    public List<Atendimento> obterAtendimentosPorPaciente(Long pacienteId) {
        return atendimentoRepository.findAllByPaciente_Id(pacienteId);
    }

    public List<Atendimento> obterAtendimentosPorTeraupeuta(Long terapeutaId) {
        return atendimentoRepository.findAllByTerapeuta_Id(terapeutaId);
    }

    public void editarObservacao(Long atendimentoId, Atendimento atendimento) {
        Atendimento atendimentoExistente = obterAtendimentoPeloId(atendimentoId);
        atendimentoExistente.setObservacao(atendimento.getObservacao());
        atendimentoRepository.save(atendimentoExistente);
    }

    private Atendimento obterAtendimentoPeloId(Long atendimentoId) {
        return atendimentoRepository.findById(atendimentoId)
                .orElseThrow(() -> new NotFoundException(ATENDIMENTO_NAO_ENCONTRADO));
    }
}
