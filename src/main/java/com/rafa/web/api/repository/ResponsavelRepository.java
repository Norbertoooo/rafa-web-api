package com.rafa.web.api.repository;

import com.rafa.web.api.domain.Responsavel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ResponsavelRepository extends JpaRepository<Responsavel, Long> {

    Optional<Responsavel> findByNomeCompleto(String nome);

    Optional<Responsavel> findByLoginEmail(String email);


}
