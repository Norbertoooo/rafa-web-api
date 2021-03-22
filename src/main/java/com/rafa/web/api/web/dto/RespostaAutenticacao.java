package com.rafa.web.api.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RespostaAutenticacao {
    private String email;
    private String perfil;
    private String token;
}
