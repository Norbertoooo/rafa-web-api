package com.rafa.web.api.config.jwt;

import com.rafa.web.api.domain.Login;
import com.rafa.web.api.domain.Perfil;
import com.rafa.web.api.service.LoginService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class JwtUserDetailsService implements UserDetailsService {

    private final LoginService loginService;

    public JwtUserDetailsService(LoginService loginService) {
        this.loginService = loginService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Login> usuario = null;
        try {
            usuario = Optional.ofNullable(loginService.buscarLoginPeloEmail(email));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (usuario.isEmpty()) {
            throw new UsernameNotFoundException("Email não encontrado: " + email);
        }
        List<GrantedAuthority> perfilAdmin = AuthorityUtils.createAuthorityList(Perfil.ADMINISTRADOR.name());
        List<GrantedAuthority> perfilResponsavel = AuthorityUtils.createAuthorityList(Perfil.RESPONSAVEL.name());
        List<GrantedAuthority> perfilTerapeuta = AuthorityUtils.createAuthorityList(Perfil.TERAPEUTA.name());

        if (usuario.get().getPerfil().equals(Perfil.ADMINISTRADOR)) {
            return new User(usuario.get().getEmail(), usuario.get().getSenha(), perfilAdmin);
        }
        if (usuario.get().getPerfil().equals(Perfil.RESPONSAVEL)) {
            return new User(usuario.get().getEmail(), usuario.get().getSenha(), perfilResponsavel);
        }
        return new User(usuario.get().getEmail(), usuario.get().getSenha(), perfilTerapeuta);
    }
}
