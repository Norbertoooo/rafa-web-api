package com.rafa.web.api.web;

import com.rafa.web.api.domain.Atendimento;
import com.rafa.web.api.service.AtendimentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/atendimentos")
@RequiredArgsConstructor
public class AtendimentoResource {

    private final AtendimentoService atendimentoService;

    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity<List<Atendimento>> obterAtendimentosDeUmPaciente(@PathVariable Long pacienteId) {
        return ResponseEntity.ok(atendimentoService.obterAtendimentosPorPaciente(pacienteId));
    }

    @GetMapping("/terapeuta/{terapeutaId}")
    public ResponseEntity<List<Atendimento>> obterAtendimentosDeUmTerapeuta(@PathVariable Long terapeutaId) {
        return ResponseEntity.ok(atendimentoService.obterAtendimentosPorTeraupeuta(terapeutaId));
    }

    @PatchMapping("/{atendimentoId}")
    public ResponseEntity<Atendimento> editarObservacao(@PathVariable Long atendimentoId, @RequestBody Atendimento atendimento) {
        atendimentoService.editarObservacao(atendimentoId, atendimento);
        return ResponseEntity.noContent().build();
    }

}
