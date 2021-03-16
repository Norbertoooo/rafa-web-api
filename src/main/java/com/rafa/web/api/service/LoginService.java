package com.rafa.web.api.service;

import com.rafa.web.api.domain.Login;
import com.rafa.web.api.repository.LoginRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static com.rafa.web.api.shared.Constantes.Erro.LOGIN_NAO_ENCONTRADO;

@Service
public class LoginService {

    private final LoginRepository loginRepository;

    public LoginService(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    public Page<Login> listarLogins(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return loginRepository.findAll(pageable);
    }

    public Login buscarLoginPeloEmail(String email) throws Exception {
        return loginRepository.findById(email).orElseThrow(() -> new Exception(LOGIN_NAO_ENCONTRADO));
    }


}
