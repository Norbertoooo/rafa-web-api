package com.rafa.web.api.repository;

import com.rafa.web.api.domain.Atendimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AtendimentoRepository extends JpaRepository<Atendimento, Long> {

    List<Atendimento> findAllByPaciente_Id(Long pacienteId);

    List<Atendimento> findAllByTerapeuta_Login_Email(String email);

}
