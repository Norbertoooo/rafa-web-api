package com.rafa.web.api.repository;

import com.rafa.web.api.domain.Paciente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {


    List<Paciente> findAllByIdTerapeuta(Long id);

    Page<Paciente> findAllByIdTerapeuta(Long id, Pageable pageable);

    List<Paciente> findAllByIdResponsavel(Long id);

    Page<Paciente> findAllByIdResponsavel(Long id, Pageable pageable);

}
