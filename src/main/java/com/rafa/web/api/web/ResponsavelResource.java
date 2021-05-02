package com.rafa.web.api.web;

import com.rafa.web.api.config.jwt.JwtTokenUtil;
import com.rafa.web.api.domain.Responsavel;
import com.rafa.web.api.service.ResponsavelService;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/responsaveis")
@Slf4j
public class ResponsavelResource {

    private final ResponsavelService responsavelService;
    private final JwtTokenUtil jwtTokenUtil;

    public ResponsavelResource(ResponsavelService responsavelService, JwtTokenUtil jwtTokenUtil) {
        this.responsavelService = responsavelService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @GetMapping("/{pagina}/{tamanho}")
    public ResponseEntity<Page<Responsavel>> listarResponsaveis(@PathVariable Integer pagina, @PathVariable Integer tamanho) {
        return ResponseEntity.ok(responsavelService.listarResponsaveis(pagina, tamanho));
    }

    @GetMapping
    public ResponseEntity<Responsavel> buscarResponsavelPeloEmail(HttpServletRequest request) throws NotFoundException {
        String email = jwtTokenUtil.obterEmailLogado(request);
        return ResponseEntity.ok(responsavelService.buscarResponsavelPeloEmail(email));
    }

    @PostMapping()
    public ResponseEntity<Responsavel> cadastrarResponsavel(@RequestBody Responsavel responsavel, @RequestParam Long id) {
        return ResponseEntity.status(HttpStatus.CREATED).body(responsavelService.cadastrarResponsavel(responsavel, id));
    }
}
