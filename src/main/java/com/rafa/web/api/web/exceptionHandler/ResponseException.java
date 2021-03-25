package com.rafa.web.api.web.exceptionHandler;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

import static com.rafa.web.api.shared.Constantes.DATA_HORA_FORMATO_BR;
import static com.rafa.web.api.shared.Constantes.ZONEID_MACEIO;


@Data
public class ResponseException {

    private String mensagem;
    private Integer status;
    private String url;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATA_HORA_FORMATO_BR)
    private LocalDateTime dataHora = LocalDateTime.now(ZONEID_MACEIO);

    public ResponseException(String mensagem, Integer status, String url) {
        this.mensagem = mensagem;
        this.status = status;
        this.url = url;
    }
}
