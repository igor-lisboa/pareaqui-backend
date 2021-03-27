package com.uff.pareaqui.service;

import com.uff.pareaqui.entity.Usuario;
import com.uff.pareaqui.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository repository;

    public Usuario saveUsuario(Usuario usuario) {
        return repository.save(usuario);
    }

    public Usuario login(String email, String senha) {
        Collection<Usuario> usuarios = repository.findByEmailAndSenha(email, senha);
        if (usuarios.size() == 1) {
            return (Usuario) usuarios.toArray()[0];
        }
        return null;
    }

    public List<Usuario> saveUsuarios(List<Usuario> usuarios) {
        return repository.saveAll(usuarios);
    }

    public List<Usuario> getUsuarios() {
        return repository.findAll();
    }

    public Usuario getUsuarioById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void deleteUsuario(Long id) {
        repository.deleteById(id);
    }

    public Usuario updateUsuario(Usuario usuario) {
        this.deleteUsuario(usuario.getId());
        return this.saveUsuario(usuario);
    }

}