package com.rafa.web.api.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.CascadeType.ALL;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder(setterPrefix = "com", buildMethodName = "construir")
@Table(name = "atendimento")
public class Atendimento {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = ALL)
    @JoinColumn(name = "id_paciente", referencedColumnName = "id")
    private Paciente paciente;

    @OneToOne(cascade = ALL)
    @JoinColumn(name = "id_terapeuta", referencedColumnName = "id")
    private Terapeuta terapeuta;

    @Column(name = "data_hora")
    private LocalDateTime dataHora;

    @Column(name = "score")
    private Integer score;

    @Column(name = "observacao")
    private String observacao;
}
