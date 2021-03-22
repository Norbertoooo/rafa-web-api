package com.rafa.web.api.repository;

import com.rafa.web.api.domain.Terapeuta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TerapeutaRepository extends JpaRepository<Terapeuta, Long> {

    Optional<Terapeuta> findByNomeCompleto(String nome);

    Optional<Terapeuta> findByCrfa(Long crfa);

    Optional<Terapeuta> findByLoginEmail(String email);

}
