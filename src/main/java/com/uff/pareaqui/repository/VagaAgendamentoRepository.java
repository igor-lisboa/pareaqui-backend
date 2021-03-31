package com.uff.pareaqui.repository;

import java.util.Collection;

import com.uff.pareaqui.entity.VagaAgendamento;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VagaAgendamentoRepository extends JpaRepository<VagaAgendamento, Long> {
    @Query("SELECT t FROM vaga_agendamentos t WHERE t.usuario_id = ?1 AND momento > NOW()")
    Collection<VagaAgendamento> findByUsuarioId(Long usuarioId);
}