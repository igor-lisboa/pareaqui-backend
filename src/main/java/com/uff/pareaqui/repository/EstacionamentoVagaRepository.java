package com.uff.pareaqui.repository;

import java.util.Collection;

import com.uff.pareaqui.entity.EstacionamentoVaga;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstacionamentoVagaRepository extends JpaRepository<EstacionamentoVaga, Long> {
    @Query("SELECT t FROM estacionamento_vagas t WHERE t.estacionamentoId = ?1")
    Collection<EstacionamentoVaga> findByEstacionamentoId(Long estacionamentoId);
}