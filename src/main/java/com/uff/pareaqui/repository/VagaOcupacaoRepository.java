package com.uff.pareaqui.repository;

import java.util.Collection;

import com.uff.pareaqui.entity.VagaOcupacao;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VagaOcupacaoRepository extends JpaRepository<VagaOcupacao, Long> {
    @Query("SELECT t FROM vaga_ocupacoes t WHERE t.saida IS NULL AND t.vaga_id = ?1")
    Collection<VagaOcupacao> findByVagaIdAndSaidaIsNull(Long vagaId);

    @Query("SELECT t FROM vaga_ocupacoes t WHERE t.usuario_id = ?1 ORDER BY t.entrada DESC")
    Collection<VagaOcupacao> findByUsuarioId(Long usuarioId);
}