package com.rafa.web.api.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.rafa.web.api.shared.Constantes.DATA_FORMATO_BR;
import static javax.persistence.CascadeType.*;
import static javax.persistence.FetchType.EAGER;
import static javax.persistence.FetchType.LAZY;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "paciente")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "nome_completo")
    private String nomeCompleto;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATA_FORMATO_BR)
    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    @Column(name = "id_terapeuta")
    private Long idTerapeuta;

    @ManyToMany(cascade = ALL, fetch = EAGER)
    @JoinTable(name = "pacientes_responsaveis", joinColumns =
            {@JoinColumn(name = "paciente_id")}, inverseJoinColumns =
            {@JoinColumn(name = "responsavel_id")})
    private List<Responsavel> responsaveis = new ArrayList<>();

}
