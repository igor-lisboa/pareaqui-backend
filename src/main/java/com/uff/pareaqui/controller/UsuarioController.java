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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @PostMapping("/login")
    public Map<String, Object> loginUsuario(@RequestBody Map<String, ?> input) {
        Map<String, Object> ret = new HashMap<String, Object>();
        try {
            Usuario usuario = service.login(input.get("email").toString(), input.get("senha").toString());

            if (usuario == null) {
                throw new Exception("Credenciais inválidas");
            }
            ret.put("success", true);
            ret.put("message", "Seja bem vindo " + usuario.getNome() + ".");
            ret.put("data", usuario);
        } catch (Exception exception) {
            ret.put("success", false);
            ret.put("data", null);
            ret.put("message", exception.getMessage());
        }
        return ret;
    }

    @GetMapping("/esqueci")
    public Map<String, Object> esqueci(@RequestParam(name = "email") String email) {
        Map<String, Object> ret = new HashMap<String, Object>();
        try {
            Usuario usuario = service.esqueciSenha(email);

            if (usuario == null) {
                throw new Exception("Esse email não pertence a nenhum usuário no cadastro");
            }
            ret.put("success", true);
            ret.put("message", "Sua senha é: [" + usuario.getSenha() + "].");
            ret.put("data", usuario);
        } catch (Exception exception) {
            ret.put("success", false);
            ret.put("data", null);
            ret.put("message", exception.getMessage());
        }
        return ret;
    }

    @PostMapping
    public Map<String, Object> addUsuario(@RequestBody Map<String, ?> input) {
        Usuario usuario = new Usuario(input.get("email").toString(), input.get("nome").toString(),
                input.get("senha").toString());
        Map<String, Object> ret = new HashMap<String, Object>();
        try {
            usuario = service.saveUsuario(usuario);

            ret.put("success", true);
            ret.put("message", "Usuário " + usuario.getNome() + " cadastrado com sucesso.");
            ret.put("data", usuario);
        } catch (Exception exception) {
            ret.put("success", false);
            ret.put("data", null);
            ret.put("message", exception.getMessage());
        }
        return ret;
    }

    @PostMapping("/multiplo")
    public Map<String, Object> addUsuarios(@RequestBody List<Usuario> usuarios) {
        Map<String, Object> ret = new HashMap<String, Object>();
        try {
            List<Usuario> usuariosGerados = service.saveUsuarios(usuarios);

            ret.put("success", true);
            ret.put("message", "Usuários gravados com sucesso.");
            ret.put("data", usuariosGerados);
        } catch (Exception exception) {
            ret.put("success", false);
            ret.put("data", null);
            ret.put("message", exception.getMessage());
        }
        return ret;
    }

    @GetMapping
    public Map<String, Object> findAllUsuarios() {
        Map<String, Object> ret = new HashMap<String, Object>();
        try {
            List<Usuario> usuariosEncontrados = service.getUsuarios();
            ret.put("success", true);
            ret.put("message", "Usuários recuperados com sucesso.");
            ret.put("data", usuariosEncontrados);
        } catch (Exception exception) {
            ret.put("success", false);
            ret.put("data", null);
            ret.put("message", exception.getMessage());
        }
        return ret;
    }

    @GetMapping("/{id}")
    public Map<String, Object> findUsuarioById(@PathVariable Long id) {
        Map<String, Object> ret = new HashMap<String, Object>();
        try {
            Usuario usuario = service.getUsuarioById(id);
            if (usuario == null) {
                throw new Exception("O id informado não pertence a nenhum usuário.");
            }
            ret.put("success", true);
            ret.put("message", "Usuário recuperado com sucesso.");
            ret.put("data", usuario);
        } catch (Exception exception) {
            ret.put("success", false);
            ret.put("data", null);
            ret.put("message", exception.getMessage());
        }
        return ret;
    }

    @PutMapping("/{id}")
    public Map<String, Object> updateUsuario(@PathVariable Long id, @RequestBody Map<String, ?> input) {
        Map<String, Object> ret = new HashMap<String, Object>();
        try {
            Usuario usuario = new Usuario(input.get("email").toString(), input.get("nome").toString(),
                    input.get("senha").toString());
            service.updateUsuario(usuario);
            ret.put("success", true);
            ret.put("message", usuario.getNome() + " recuperado com sucesso.");
            ret.put("data", usuario);
        } catch (Exception exception) {
            ret.put("success", false);
            ret.put("data", null);
            ret.put("message", exception.getMessage());
        }
        return ret;
    }

    @DeleteMapping("/{id}")
    public Map<String, Object> deleteUsuario(@PathVariable Long id) {
        Map<String, Object> ret = new HashMap<String, Object>();
        try {
            service.deleteUsuario(id);
            ret.put("success", true);
            ret.put("message", "Usuário " + id + " deletado com sucesso.");
            ret.put("data", null);
        } catch (Exception exception) {
            ret.put("success", false);
            ret.put("data", null);
            ret.put("message", exception.getMessage());
        }
        return ret;
    }
}
