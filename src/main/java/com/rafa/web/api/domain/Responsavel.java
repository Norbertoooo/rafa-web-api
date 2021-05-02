package com.rafa.web.api.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
@Table(name = "responsavel")
public class Responsavel {

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

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "parentesco")
    private String parentesco;

    @JsonIgnore
    @ManyToMany(cascade = ALL, fetch = EAGER, mappedBy = "responsaveis")
    private List<Paciente> protegidos = new ArrayList<>();

    @OneToOne(cascade = ALL)
    @JoinColumn(name = "email", referencedColumnName = "email")
    private Login login;

    @OneToOne(cascade = ALL)
    @JoinColumn(name = "id_endereco", referencedColumnName = "id")
    private Endereco endereco;

}
