package com.rafa.web.api.repository;

import com.rafa.web.api.domain.Desafios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DesafiosRepository extends JpaRepository<Desafios, Long> {
}
