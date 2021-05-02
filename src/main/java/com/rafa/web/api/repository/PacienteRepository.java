package com.rafa.web.api.repository;

import com.rafa.web.api.domain.Paciente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Optional;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {


    List<Paciente> findAllByIdTerapeuta(Long id);

    Optional<Paciente> findByNomeCompletoLike(@NotBlank String nomeCompleto);

    Page<Paciente> findAllByIdTerapeuta(Long id, Pageable pageable);

    List<Paciente> findAllByResponsaveis_Id(Long id);

    Page<Paciente> findAllByResponsaveis_Id(Long id, Pageable pageable);

}
