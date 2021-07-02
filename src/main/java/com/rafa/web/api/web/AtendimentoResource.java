package com.rafa.web.api.web;

import com.rafa.web.api.domain.Atendimento;
import com.rafa.web.api.service.AtendimentoService;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/atendimentos")
@RequiredArgsConstructor
@Log4j2
public class AtendimentoResource {

    private final AtendimentoService atendimentoService;

    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity<List<Atendimento>> obterAtendimentosDeUmPaciente(@PathVariable Long pacienteId) {
        log.info("Requisição para obter atendimentos do paciente de id: {}", pacienteId);
        return ResponseEntity.ok(atendimentoService.obterAtendimentosPorPaciente(pacienteId));
    }

    @GetMapping("/terapeuta")
    public ResponseEntity<List<Atendimento>> obterAtendimentosDeUmTerapeuta(HttpServletRequest request) throws NotFoundException {
        log.info("Requisição para obter atendimentos do terapeuta");
        return ResponseEntity.ok(atendimentoService.obterAtendimentosPorTeraupeuta(request));
    }

    @PatchMapping("/{atendimentoId}")
    public ResponseEntity<Atendimento> editarObservacao(@PathVariable Long atendimentoId, @RequestBody Atendimento atendimento) {
        log.info("Requisição para editar observação do atendimento de id: {}", atendimentoId);
        atendimentoService.editarObservacao(atendimentoId, atendimento);
        return ResponseEntity.noContent().build();
    }

}
