package com.rafa.web.api.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class JsonUtil {

    public static String converterParaJson(Object objeto) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(objeto);
    }
}
