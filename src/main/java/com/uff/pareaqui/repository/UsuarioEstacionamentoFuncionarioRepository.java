package com.uff.pareaqui.repository;

import java.util.Collection;

import com.uff.pareaqui.entity.UsuarioEstacionamentoFuncionario;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioEstacionamentoFuncionarioRepository
                extends JpaRepository<UsuarioEstacionamentoFuncionario, Long> {
        @Query("SELECT t FROM usuario_estacionamento_funcionarios t WHERE t.estacionamentoId = ?1")
        Collection<UsuarioEstacionamentoFuncionario> findByEstacionamentoId(Long estacionamentoId);

        @Query("SELECT t FROM usuario_estacionamento_funcionarios t WHERE t.estacionamentoId = ?1 AND t.usuario_id = ?2")
        Collection<UsuarioEstacionamentoFuncionario> findByEstacionamentoIdAndUsuarioId(Long estacionamentoId,
                        Long usuarioId);
}