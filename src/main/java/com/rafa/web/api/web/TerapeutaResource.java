package com.rafa.web.api.web;

import com.rafa.web.api.domain.Terapeuta;
import com.rafa.web.api.service.TerapeutaService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/terapeutas")
@CrossOrigin(origins = "*")
public class TerapeutaResource {

    private final TerapeutaService terapeutaService;

    public TerapeutaResource(TerapeutaService terapeutaService) {
        this.terapeutaService = terapeutaService;
    }

    @GetMapping
    public ResponseEntity<Page<Terapeuta>> listarTerapeutas() {
        return ResponseEntity.ok(terapeutaService.listarTerapeutas(0, 10));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Terapeuta> buscarTerapeutaPeloId(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(terapeutaService.buscarTerapeutaPeloId(id));
    }

    @PostMapping
    public ResponseEntity<Terapeuta> cadastrarTerapeuta(@RequestBody Terapeuta terapeuta) {
        return ResponseEntity.status(HttpStatus.CREATED).body(terapeutaService.cadastrarTerapeuta(terapeuta));
    }

}
