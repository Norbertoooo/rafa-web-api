package com.rafa.web.api.web;

import com.rafa.web.api.domain.Responsavel;
import com.rafa.web.api.service.ResponsavelService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/responsaveis")
@CrossOrigin(origins = "*")
public class ResponsavelResource {

    private final ResponsavelService responsavelService;

    public ResponsavelResource(ResponsavelService responsavelService) {
        this.responsavelService = responsavelService;
    }

    @GetMapping
    public ResponseEntity<Page<Responsavel>> listarResponsaveis() {
        return ResponseEntity.ok(responsavelService.listarResponsaveis(0, 10));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Responsavel> buscarResponsavelPeloId(@PathVariable Long id) {
        return ResponseEntity.ok(responsavelService.buscarResponsavelPeloId(id));
    }

    @PostMapping()
    public ResponseEntity<Responsavel> cadastrarResponsavel(@RequestBody Responsavel responsavel, @RequestParam Long id) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(responsavelService.cadastrarResponsavel(responsavel, id));
    }
}
