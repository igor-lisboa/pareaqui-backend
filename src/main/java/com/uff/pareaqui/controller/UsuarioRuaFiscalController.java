package com.uff.pareaqui.controller;

import java.util.HashMap;
import java.util.Map;

import com.uff.pareaqui.entity.UsuarioRuaFiscal;
import com.uff.pareaqui.service.UsuarioRuaFiscalService;

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

    @PostMapping
    public Map<String, Object> addUsuarioRuaFiscal(@RequestBody Map<String, ?> input) {
        Map<String, Object> ret = new HashMap<String, Object>();
        try {
            ret.put("data", service
                    .saveUsuarioRuaFiscalCompleto(Long.parseLong(String.valueOf((Object) input.get("usuario_id")))));
            ret.put("success", true);
            ret.put("message", "O Usuário foi cadastrado como fiscal de ruas com sucesso.");
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
            ret.put("message", "Fiscal de rua recuperado com sucesso.");
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
