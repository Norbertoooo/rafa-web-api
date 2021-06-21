package com.rafa.web.api.shared;

import java.time.ZoneId;

public final class Constantes {

    public static final ZoneId ZONEID_MACEIO = ZoneId.of("America/Maceio");
    public static final String DATA_HORA_FORMATO_BR = "dd/MM/yyyy hh:mm:ss";
    public static final String DATA_FORMATO_BR = "dd/MM/yyyy";
    public static final Integer ACCESS_TOKEN_VALIDITY_SECONDS = 5 * 60 * 60;
    public static final String SIGNING_KEY = "rafaweb";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SENHA_PADRAO_RESPONSAVEL = "responsavel123";


    public static final class Erro {
        public static final String LOGIN_NAO_ENCONTRADO = "Login não encontrado para o email inserido!";
        public static final String TERAPEUTA_NAO_ENCONTRADO = "Terapeuta não encontrado!";
        public static final String RESPONSAVEL_NAO_ENCONTRADO = "Responsável não encontrado!";
        public static final String PACIENTE_NAO_ENCONTRADO = "Paciente não encontrado!";
        public static final String ATENDIMENTO_NAO_ENCONTRADO = "Atendimento não encontrado!";
        public static final String DESAFIO_NAO_ENCONTRADO = "Desafio não encontrado para o id: ";
    }

    public static final class Sucesso {

    }

    public static final class Querys {

    }
}
