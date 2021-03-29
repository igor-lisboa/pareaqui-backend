package com.uff.pareaqui.controller;

import java.util.HashMap;
import java.util.Map;

import com.uff.pareaqui.entity.Usuario;
import com.uff.pareaqui.entity.UsuarioRuaFiscal;
import com.uff.pareaqui.service.UsuarioRuaFiscalService;
import com.uff.pareaqui.service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario/fiscal")
public class UsuarioRuaFiscalController {

    @Autowired
    private UsuarioRuaFiscalService service;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public Map<String, Object> addUsuarioRuaFiscal(@RequestBody Map<String, ?> input) {
        Map<String, Object> ret = new HashMap<String, Object>();
        try {
            UsuarioRuaFiscal usuarioRuaFiscal = new UsuarioRuaFiscal();

            Object usuarioId = input.get("usuario_id");
            if (usuarioId == null) {
                throw new Exception("Informe o id do usuário que será promovido a fiscal de ruas");
            }

            Usuario usuario = usuarioService.getUsuarioById(Long.parseLong(usuarioId.toString()));
            if (usuario == null) {
                throw new Exception("O usuário escolhido para ser promovido a fiscal de ruas não existe");
            }
            usuarioRuaFiscal.setUsuario(usuario);

            UsuarioRuaFiscal usuarioFiscal = service.findByUsuarioId(usuario.getId());
            if (usuarioFiscal != null) {
                throw new Exception("O usuário escolhido para ser promovido a fiscal já foi promovido");
            }

            usuarioRuaFiscal = service.saveUsuarioRuaFiscal(usuarioRuaFiscal);
            ret.put("success", true);
            ret.put("message", "O Usuário foi cadastrado como fiscal de ruas com sucesso.");
            ret.put("data", usuarioRuaFiscal);
        } catch (Exception exception) {
            ret.put("success", false);
            ret.put("data", null);
            ret.put("message", exception.getMessage());
        }
        return ret;
    }

    @GetMapping("/{id}")
    public Map<String, Object> findUsuarioFiscalByUsuarioId(@PathVariable Long id) {
        Map<String, Object> ret = new HashMap<String, Object>();
        try {
            UsuarioRuaFiscal usuarioFiscal = service.findByUsuarioId(id);
            if (usuarioFiscal == null) {
                throw new Exception("O id informado não pertence a nenhum fiscal de ruas.");
            }
            ret.put("success", true);
            ret.put("message", "Fiscais de ruas recuperados com sucesso.");
            ret.put("data", usuarioFiscal);
        } catch (Exception exception) {
            ret.put("success", false);
            ret.put("data", null);
            ret.put("message", exception.getMessage());
        }
        return ret;
    }

    @DeleteMapping("/{id}")
    public Map<String, Object> deleteUsuarioRuaFiscal(@PathVariable Long id) {
        Map<String, Object> ret = new HashMap<String, Object>();
        try {
            service.deleteUsuarioRuaFiscal(id);
            ret.put("success", true);
            ret.put("message", "A função de fiscal de ruas foi retirada do usuário com sucesso.");
            ret.put("data", null);
        } catch (Exception exception) {
            ret.put("success", false);
            ret.put("data", null);
            ret.put("message", exception.getMessage());
        }
        return ret;
    }
}
