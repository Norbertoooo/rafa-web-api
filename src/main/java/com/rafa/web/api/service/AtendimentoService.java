package com.rafa.web.api.service;

import com.rafa.web.api.domain.Atendimento;
import com.rafa.web.api.repository.AtendimentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
