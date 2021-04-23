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

    public Usuario saveUsuario(Usuario usuario) throws Exception {
        Collection<Usuario> usuarios = repository.findByEmail(usuario.getEmail());
        if (usuarios.size() > 0) {
            throw new Exception("Esse e-mail já está em uso por outro usuário, tente a opção 'Esqueci minha senha'");
        }
        return repository.save(usuario);
    }

    public Usuario login(String email, String senha) {
        Collection<Usuario> usuarios = repository.findByEmailAndSenha(email, senha);
        if (usuarios.size() == 1) {
            return (Usuario) usuarios.toArray()[0];
        }
        return null;
    }

    public Usuario esqueciSenha(String email) {
        Collection<Usuario> usuarios = repository.findByEmail(email);
        if (usuarios.size() == 1) {
            return (Usuario) usuarios.toArray()[0];
        }
        return null;
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

    public Usuario updateUsuario(Long id, Usuario usuario) throws Exception {
        Usuario usuarioAtualiza = repository.getOne(id);
        usuarioAtualiza.setNome(usuario.getNome());
        usuarioAtualiza.setSenha(usuario.getSenha());
        usuarioAtualiza.setEmail(usuario.getEmail());
        usuarioAtualiza.setAdm(usuario.getAdm());
        return this.saveUsuario(usuarioAtualiza);
    }
}