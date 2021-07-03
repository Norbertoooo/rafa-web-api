package com.rafa.web.api.cucumber.config;

import com.rafa.web.api.domain.Login;
import com.rafa.web.api.domain.Perfil;
import io.cucumber.java.DataTableType;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Map;

@CucumberContextConfiguration
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class CucumberSpringConfiguration {

    @DataTableType
    public Login loginDataTable(Map<String, String> entry) {
        return new Login(
                entry.get("email"),
                entry.get("senha"),
                Perfil.valueOf(entry.get("perfil")));
    }
}
