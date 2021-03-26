package com.uff.pareaqui.repository;

import com.uff.pareaqui.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}