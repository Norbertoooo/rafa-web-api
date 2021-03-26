package com.rafa.web.api.web;

import com.rafa.web.api.config.jwt.JwtTokenUtil;
import com.rafa.web.api.domain.Terapeuta;
import com.rafa.web.api.service.TerapeutaService;
import javassist.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/terapeutas")
public class TerapeutaResource {

    private final TerapeutaService terapeutaService;

    public TerapeutaResource(TerapeutaService terapeutaService, JwtTokenUtil jwtTokenUtil) {
        this.terapeutaService = terapeutaService;
    }

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

}
