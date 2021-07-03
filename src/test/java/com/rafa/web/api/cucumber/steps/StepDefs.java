package com.rafa.web.api.cucumber.steps;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rafa.web.api.domain.Login;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@Component
public abstract class StepDefs {

    @Autowired
    protected MockMvc mockMvc;

    protected static Login login;

    protected static ResultActions actions;

    protected static String converterParaJson(Object o) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(o);
    }

}
