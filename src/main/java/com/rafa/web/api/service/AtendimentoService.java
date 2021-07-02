package com.rafa.web.api.service;

import com.rafa.web.api.config.jwt.JwtTokenUtil;
import com.rafa.web.api.domain.Atendimento;
import com.rafa.web.api.domain.Terapeuta;
import com.rafa.web.api.repository.AtendimentoRepository;
import com.rafa.web.api.web.exceptionHandler.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.rafa.web.api.shared.Constantes.Erro.ATENDIMENTO_NAO_ENCONTRADO;

@Service
@RequiredArgsConstructor
public class AtendimentoService {

    private final AtendimentoRepository atendimentoRepository;
    private final JwtTokenUtil jwtTokenUtil;

    public List<Atendimento> obterAtendimentosPorPaciente(Long pacienteId) {
        return atendimentoRepository.findAllByPaciente_Id(pacienteId);
    }

    public List<Atendimento> obterAtendimentosPorTeraupeuta(HttpServletRequest request) throws javassist.NotFoundException {
        String email = jwtTokenUtil.obterEmailLogado(request);
        return atendimentoRepository.findAllByTerapeuta_Login_Email(email);
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
