package com.rafa.web.api.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "responsavel")
public class Responsavel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome_completo")
    private String nomeCompleto;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    @Column(name = "telefone")
    private Long telefone;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "parentesco")
    private String parentesco;

    @OneToMany(cascade = ALL)
    @JoinColumn(name = "id_responsavel")
    private List<Paciente> protegidos;

    @OneToOne(cascade = ALL)
    @JoinColumn(name = "email", referencedColumnName = "email")
    private Login login;

    @OneToOne(cascade = ALL)
    @JoinColumn(name = "id_endereco", referencedColumnName = "id")
    private Endereco endereco;

}
