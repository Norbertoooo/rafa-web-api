package com.rafa.web.api.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

import static com.rafa.web.api.shared.Constantes.DATA_FORMATO_BR;
import static javax.persistence.CascadeType.ALL;

@Entity
@Data
@Builder(setterPrefix = "com", buildMethodName = "construir")
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "terapeuta")
public class Terapeuta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome_completo")
    private String nomeCompleto;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATA_FORMATO_BR)
    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    @Column(name = "telefone")
    private Long telefone;

    @Column(name = "crfa")
    private Long crfa;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "especialidade")
    private String especialidade;

    @Column(name = "formacao")
    private String formacao;

    @OneToOne(cascade = ALL)
    @JoinColumn(name = "email", referencedColumnName = "email")
    private Login login;

    @OneToOne(cascade = ALL)
    @JoinColumn(name = "id_endereco", referencedColumnName = "id")
    private Endereco endereco;

}
