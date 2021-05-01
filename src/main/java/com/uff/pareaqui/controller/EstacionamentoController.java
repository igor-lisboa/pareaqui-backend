package com.uff.pareaqui.controller;

import java.util.HashMap;
import java.util.Map;

import com.uff.pareaqui.entity.Estacionamento;
import com.uff.pareaqui.service.EstacionamentoService;

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
@RequestMapping("/estacionamento")
public class EstacionamentoController {

    @Autowired
    private EstacionamentoService service;

    @PostMapping
    public Map<String, Object> addEstacionamento(@RequestBody Map<String, ?> input) {
        Map<String, Object> ret = new HashMap<String, Object>();
        try {
            Long donoId = Long.parseLong(String.valueOf((Object) input.get("dono_id")));
            String nome = String.valueOf((Object) input.get("nome"));
            if (nome == "null") {
                nome = null;
            }
            String rua = String.valueOf((Object) input.get("rua"));
            if (rua == "null") {
                rua = null;
            }
            String numero = String.valueOf((Object) input.get("numero"));
            if (numero == "null") {
                numero = null;
            }
            String bairro = String.valueOf((Object) input.get("bairro"));
            if (bairro == "null") {
                bairro = null;
            }
            String complemento = String.valueOf((Object) input.get("complemento"));
            if (complemento == "null") {
                complemento = null;
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
            ret.put("data", service.saveEstacionamentoCompleto(donoId, nome, rua, numero, bairro, complemento, cidade,
                    estado, pais));
            ret.put("success", true);
            ret.put("message", "O estacionamento foi cadastrado com sucesso.");
        } catch (Exception exception) {
            ret.put("success", false);
            ret.put("data", null);
            ret.put("message", exception.getMessage());
        }
        return ret;
    }

    @GetMapping
    public Map<String, Object> findAllEstacionamentos() {
        Map<String, Object> ret = new HashMap<String, Object>();
        try {
            ret.put("data", service.getEstacionamentos());
            ret.put("success", true);
            ret.put("message", "Estacionamentos recuperados com sucesso.");
        } catch (Exception exception) {
            ret.put("success", false);
            ret.put("data", null);
            ret.put("message", exception.getMessage());
        }
        return ret;
    }

    @GetMapping("/{id}")
    public Map<String, Object> findEstacionamentoById(@PathVariable Long id) {
        Map<String, Object> ret = new HashMap<String, Object>();
        try {
            Estacionamento estacionamento = service.getEstacionamentoById(id);
            if (estacionamento == null) {
                throw new Exception("O id informado não pertence a nenhum estacionamento.");
            }
            ret.put("success", true);
            ret.put("message", "Estacionamento recuperado com sucesso.");
            ret.put("data", estacionamento);
        } catch (Exception exception) {
            ret.put("success", false);
            ret.put("data", null);
            ret.put("message", exception.getMessage());
        }
        return ret;
    }

    @PutMapping("/{id}")
    public Map<String, Object> updateEstacionamento(@PathVariable Long id, @RequestBody Map<String, ?> input) {
        Map<String, Object> ret = new HashMap<String, Object>();
        try {
            Long donoId = Long.parseLong(String.valueOf((Object) input.get("dono_id")));
            String nome = String.valueOf((Object) input.get("nome"));
            if (nome == "null") {
                nome = null;
            }
            String rua = String.valueOf((Object) input.get("rua"));
            if (rua == "null") {
                rua = null;
            }
            String numero = String.valueOf((Object) input.get("numero"));
            if (numero == "null") {
                numero = null;
            }
            String bairro = String.valueOf((Object) input.get("bairro"));
            if (bairro == "null") {
                bairro = null;
            }
            String complemento = String.valueOf((Object) input.get("complemento"));
            if (complemento == "null") {
                complemento = null;
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
            ret.put("data", service.updateEstacionamentoCompleto(id, donoId, nome, rua, numero, bairro, complemento,
                    cidade, estado, pais));
            ret.put("success", true);
            ret.put("message", "Estacionamento " + id + " atualizado com sucesso.");
        } catch (Exception exception) {
            ret.put("success", false);
            ret.put("data", null);
            ret.put("message", exception.getMessage());
        }
        return ret;
    }

    @DeleteMapping("/{id}")
    public Map<String, Object> deleteEstacionamento(@PathVariable Long id) {
        Map<String, Object> ret = new HashMap<String, Object>();
        try {
            service.deleteEstacionamento(id);
            ret.put("success", true);
            ret.put("message", "Estacionamento " + id + " deletado com sucesso.");
            ret.put("data", null);
        } catch (Exception exception) {
            ret.put("success", false);
            ret.put("data", null);
            ret.put("message", exception.getMessage());
        }
        return ret;
    }
}
