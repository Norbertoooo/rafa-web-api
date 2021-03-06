package com.rafa.web.api.web;


import com.rafa.web.api.domain.Desafio;
import com.rafa.web.api.service.DesafiosService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/desafios")
@RequiredArgsConstructor
@Slf4j
public class DesafiosResource {

    private final DesafiosService desafiosService;

    @GetMapping("/{pagina}/{tamanho}")
    public ResponseEntity<Page<Desafio>> buscarDesafios(@PathVariable Integer pagina, @PathVariable Integer tamanho) {
        log.info("Requisição para listar todos os desafios");
        return ResponseEntity.ok().body(desafiosService.listarDesafios(pagina, tamanho));
    }

    @PostMapping
    public ResponseEntity<Desafio> cadastrarDesafio(@RequestBody Desafio desafio) {
        log.info("Requisição para cadastrar desafio: {}", desafio);
        return ResponseEntity.status(CREATED).body(desafiosService.salvarDesafio(desafio));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Desafio> atualizarDesafio(@PathVariable Long id, @RequestBody Desafio desafio) {
        log.info("Requisição para atualizar desafio de id: {}", id);
        return ResponseEntity.ok().body(desafiosService.atualizarDesafio(id, desafio));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarDesafio(@PathVariable Long id) {
        log.info("Requisição para deletar desafio de id: {}", id);
        desafiosService.deletarDesafio(id);
        return ResponseEntity.noContent().build();
    }
}
