package com.uff.pareaqui.controller;

import java.util.HashMap;
import java.util.Map;

import com.uff.pareaqui.entity.RuaVaga;
import com.uff.pareaqui.service.RuaVagaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/rua/vaga")
public class RuaVagaController {
    @Autowired
    private RuaVagaService service;

    @PostMapping
    public Map<String, Object> addRuaVaga(@RequestBody Map<String, ?> input) {
        Map<String, Object> ret = new HashMap<String, Object>();
        try {
            String rua = String.valueOf((Object) input.get("rua"));
            if (rua == "null") {
                rua = null;
            }
            String numero = String.valueOf((Object) input.get("numero"));
            if (numero == "null") {
                numero = null;
            }
            String complemento = String.valueOf((Object) input.get("complemento"));
            if (complemento == "null") {
                complemento = null;
            }
            String bairro = String.valueOf((Object) input.get("bairro"));
            if (bairro == "null") {
                bairro = null;
            }
            String cidade = String.valueOf((Object) input.get("cidade"));
            if (cidade == "null") {
                cidade = null;
            }
            String estado = String.valueOf((Object) input.get("estado"));
            if (estado == "null") {
                estado = null;
            }
            String pais = String.valueOf((Object) input.get("pais"));
            if (pais == "null") {
                pais = null;
            }
            Long vagaTipoId = Long.parseLong(String.valueOf((Object) input.get("vaga_tipo_id")));
            Long vagaTamanhoId = Long.parseLong(String.valueOf((Object) input.get("vaga_tamanho_id")));
            Float preco = Float.parseFloat(String.valueOf((Object) input.get("preco")));
            String identificacao = String.valueOf((Object) input.get("identificacao"));
            if (identificacao == "null") {
                identificacao = null;
            }
            ret.put("data", service.saveRuaVagaCompleta(rua, numero, complemento, bairro, cidade, estado, pais,
                    vagaTipoId, vagaTamanhoId, preco, identificacao));
            ret.put("message", "A vaga da rua " + rua + " foi adicionada.");
            ret.put("success", true);
        } catch (Exception exception) {
            ret.put("success", false);
            ret.put("data", null);
            ret.put("message", exception.getMessage());
        }
        return ret;
    }

    @PutMapping("/{id}")
    public Map<String, Object> updateRuaVaga(@PathVariable Long id, @RequestBody Map<String, ?> input) {
        Map<String, Object> ret = new HashMap<String, Object>();
        try {
            String rua = String.valueOf((Object) input.get("rua"));
            if (rua == "null") {
                rua = null;
            }
            String numero = String.valueOf((Object) input.get("numero"));
            if (numero == "null") {
                numero = null;
            }
            String complemento = String.valueOf((Object) input.get("complemento"));
            if (complemento == "null") {
                complemento = null;
            }
            String bairro = String.valueOf((Object) input.get("bairro"));
            if (bairro == "null") {
                bairro = null;
            }
            String cidade = String.valueOf((Object) input.get("cidade"));
            if (cidade == "null") {
                cidade = null;
            }
            String estado = String.valueOf((Object) input.get("estado"));
            if (estado == "null") {
                estado = null;
            }
            String pais = String.valueOf((Object) input.get("pais"));
            if (pais == "null") {
                pais = null;
            }
            Long vagaTipoId = Long.parseLong(String.valueOf((Object) input.get("vaga_tipo_id")));
            Long vagaTamanhoId = Long.parseLong(String.valueOf((Object) input.get("vaga_tamanho_id")));
            Float preco = Float.parseFloat(String.valueOf((Object) input.get("preco")));
            String identificacao = String.valueOf((Object) input.get("identificacao"));
            if (identificacao == "null") {
                identificacao = null;
            }
            ret.put("data", service.updateRuaVagaCompleta(id, rua, numero, complemento, bairro, cidade, estado, pais,
                    vagaTipoId, vagaTamanhoId, preco, identificacao));
            ret.put("message", "Vaga da rua " + rua + " recuperada com sucesso.");
            ret.put("success", true);
        } catch (Exception exception) {
            ret.put("success", false);
            ret.put("data", null);
            ret.put("message", exception.getMessage());
        }
        return ret;
    }

    @GetMapping("/{id}")
    public Map<String, Object> findRuaVaga(@PathVariable Long id) {
        Map<String, Object> ret = new HashMap<String, Object>();
        try {
            RuaVaga ruaVaga = service.getRuaVaga(id);
            if (ruaVaga == null) {
                throw new Exception("O id informado n??o pertence a nenhuma vaga de rua.");
            }
            ret.put("success", true);
            ret.put("message", "Vaga da rua " + ruaVaga.getRua() + " recuperada com sucesso.");
            ret.put("data", ruaVaga);
        } catch (Exception exception) {
            ret.put("success", false);
            ret.put("data", null);
            ret.put("message", exception.getMessage());
        }
        return ret;
    }

    @DeleteMapping("/{id}")
    public Map<String, Object> deleteRuaVaga(@PathVariable Long id) {
        Map<String, Object> ret = new HashMap<String, Object>();
        try {
            service.deleteRuaVaga(id);
            ret.put("success", true);
            ret.put("message", "A vaga " + id + " de rua foi deletada com sucesso.");
            ret.put("data", null);
        } catch (Exception exception) {
            ret.put("success", false);
            ret.put("data", null);
            ret.put("message", exception.getMessage());
        }
        return ret;
    }
}
