package com.rafa.web.api.cucumber.steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@Slf4j
public class LoginSteps extends StepDefs{

    @Dado("Que exista o seguinte Login:")
    public void queExistaOSeguinteLogin(DataTable login) {
        log.debug(login.toString());
    }

    @Quando("for solicitada o login")
    public void forSolicitadaOLogin() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print());
    }

    @Então("usuario deve ser autenticado")
    public void usuarioDeveSerAutenticado() {

    }
}
