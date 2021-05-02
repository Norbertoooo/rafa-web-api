package com.rafa.web.api.web;

import com.rafa.web.api.config.jwt.JwtTokenUtil;
import com.rafa.web.api.config.jwt.JwtUserDetailsService;
import com.rafa.web.api.domain.Login;
import com.rafa.web.api.web.dto.RespostaAutenticacao;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Slf4j
public class LoginResource {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping("/autenticar")
    public ResponseEntity<?> autenticar(@RequestBody Login login) {

        log.info("Requisição para efetuar login: {}", login);

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.getEmail(), login.getSenha()));

        UserDetails userDetails = userDetailsService.loadUserByUsername(login.getEmail());

        String token = jwtTokenUtil.generateToken(userDetails);

        String perfil = userDetails.getAuthorities().stream().findFirst().get().toString();

        return ResponseEntity.ok(new RespostaAutenticacao(userDetails.getUsername(), perfil, token));
    }
}
