package com.rafa.web.api.cucumber.steps;

import com.rafa.web.api.cucumber.util.TruncateTableUtil;
import com.rafa.web.api.domain.Login;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
public class LoginSteps extends StepDefs {

    private final TruncateTableUtil truncateTableUtil;

    @Autowired
    public LoginSteps(TruncateTableUtil truncateTableUtil) {
        this.truncateTableUtil = truncateTableUtil;
    }

    @Dado("Que exista não exista o seguinte Login")
    public void queExistaOSeguinteLogin(List<Login> login) {
        log.debug(login.toString());
        StepDefs.login = login.stream().findFirst().orElseThrow();
        truncateTableUtil.truncateLoginTable();
    }

    @Quando("for solicitada o login com os sequintes dados")
    public void forSolicitadaOLogin(List<Login> logins) throws Exception {
        Login login = logins.stream().findFirst().orElseThrow();
        actions = mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8081/api/autenticar")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(converterParaJson(login)))
                .andDo(print());
    }

    @Então("usuario deve ser autenticado")
    public void usuarioDeveSerAutenticado() throws Exception {
        actions.andExpect(status().isForbidden());
    }
}
