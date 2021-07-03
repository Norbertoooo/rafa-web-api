package com.rafa.web.api.cucumber.util;

import com.rafa.web.api.repository.LoginRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Log4j2
public class TruncateTableUtil {

    private final LoginRepository loginRepository;

    public void truncateLoginTable() {
        log.info("Efetuando truncate na tabela de login");
        loginRepository.deleteAll();
    }
}
