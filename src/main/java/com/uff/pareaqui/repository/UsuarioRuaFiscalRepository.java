package com.uff.pareaqui.repository;

import java.util.Collection;

import com.uff.pareaqui.entity.UsuarioRuaFiscal;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRuaFiscalRepository extends JpaRepository<UsuarioRuaFiscal, Long> {
    @Query("SELECT t FROM usuario_rua_fiscais t WHERE t.usuario_id = ?1")
    Collection<UsuarioRuaFiscal> findByUsuarioId(Long usuarioId);
}