package com.rafa.web.api.shared;

import java.time.ZoneId;

public final class Constantes {

    public interface Variaveis {
        ZoneId ZONEID_MACEIO = ZoneId.of("America/Maceio");
        String DATA_HORA_FORMATO_BR = "dd/MM/yyyy hh:mm:ss";
        Integer ACCESS_TOKEN_VALIDITY_SECONDS = 5 * 60 * 60;
        String SIGNING_KEY = "rafaweb";
        String TOKEN_PREFIX = "Bearer ";
        String HEADER_STRING = "Authorization";
    }

    public interface Erro {
        String LOGIN_NAO_ENCONTRADO = "Login não encontrado para o email inserido!";
        String TERAPEUTA_NAO_ENCONTRADO = "Terapeuta não encontrado!";
        String RESPONSAVEL_NAO_ENCONTRADO = "Responsável não encontrado!";
        String PACIENTE_NAO_ENCONTRADO = "Paciente não encontrado!";
    }

    public interface Sucesso {

    }

    public interface Querys {

    }
}
