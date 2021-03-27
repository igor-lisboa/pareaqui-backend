package com.uff.pareaqui.repository;

import java.util.Collection;

import com.uff.pareaqui.entity.Usuario;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    @Query("SELECT t FROM usuarios t WHERE t.email = ?1 AND t.senha = ?2")
    Collection<Usuario> findByEmailAndSenha(String email, String senha);
}