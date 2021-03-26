package com.uff.pareaqui.controller;

import com.uff.pareaqui.entity.Usuario;
import com.uff.pareaqui.service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @PostMapping
    public Usuario addUsuario(@RequestBody Usuario usuario) {
        return service.saveUsuario(usuario);
    }

    @PostMapping("/multiplo")
    public List<Usuario> addUsuarios(@RequestBody List<Usuario> usuarios) {
        return service.saveUsuarios(usuarios);
    }

    @GetMapping
    public List<Usuario> findAllUsuarios() {
        return service.getUsuarios();
    }

    @GetMapping("/{id}")
    public Usuario findUsuarioById(@PathVariable Long id) {
        return service.getUsuarioById(id);
    }

    @PutMapping
    public Usuario updateUsuario(@RequestBody Usuario usuario) {
        return service.updateUsuario(usuario);
    }

    @DeleteMapping("/{id}")
    public Long deleteUsuario(@PathVariable Long id) {
        return service.deleteUsuario(id);
    }
}
