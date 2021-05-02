package com.rafa.web.api.web;

import com.rafa.web.api.domain.Terapeuta;
import com.rafa.web.api.service.TerapeutaService;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/terapeutas")
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class TerapeutaResource {

    private final TerapeutaService terapeutaService;

    @GetMapping("/{pagina}/{tamanho}")
    public ResponseEntity<Page<Terapeuta>> listarTerapeutas(@PathVariable Integer pagina, @PathVariable Integer tamanho) {
        return ResponseEntity.ok(terapeutaService.listarTerapeutas(pagina, tamanho));
    }

    @GetMapping
    public ResponseEntity<Terapeuta> buscarTerapeutaPeloEmail(HttpServletRequest request) throws NotFoundException {
        return ResponseEntity.ok(terapeutaService.buscarTerapeutaPeloEmail(request));
    }

    @PostMapping
    public ResponseEntity<Terapeuta> cadastrarTerapeuta(@RequestBody Terapeuta terapeuta) {
        return ResponseEntity.status(HttpStatus.CREATED).body(terapeutaService.cadastrarTerapeuta(terapeuta));
    }

    @PutMapping
    public ResponseEntity<Terapeuta> atualizarTerapeuta(@RequestBody Terapeuta terapeuta) {
        return ResponseEntity.ok().body(terapeutaService.atualizarTerapeuta(terapeuta));
    }

}
