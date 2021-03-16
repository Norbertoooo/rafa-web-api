package com.rafa.web.api.repository;

import com.rafa.web.api.domain.Paciente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    Page<Paciente> findAllByIdTerapeuta(Long id, Pageable pageable);

    Page<Paciente> findAllByIdResponsavel(Long id, Pageable pageable);

}
