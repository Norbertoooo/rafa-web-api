package com.rafa.web.api.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder(setterPrefix = "com", buildMethodName = "construir")
@Table(name = "desafios")
public class Desafios {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "pergunta")
    private String pergunta;

    @Column(name = "resposta")
    private String resposta;

    @Column(name = "score_inicial")
    private Integer scoreInicial;

    @Column(name = "score_global")
    private Integer scoreGlobal;
}
