package com.uff.pareaqui.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.uff.pareaqui.entity.Estacionamento;
import com.uff.pareaqui.service.EstacionamentoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/estacionamento")
public class EstacionamentoController {

    @Autowired
    private EstacionamentoService service;

    @PostMapping
    public Map<String, Object> addEstacionamento(@RequestBody Estacionamento estacionamento) {
        Map<String, Object> ret = new HashMap<String, Object>();
        try {
            estacionamento = service.saveEstacionamento(estacionamento);

            ret.put("success", true);
            ret.put("message", "O estacionamento " + estacionamento.getNome() + " foi cadastrado com sucesso.");
            ret.put("data", estacionamento);
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
            List<Estacionamento> estacionamentosEncontrados = service.getEstacionamentos();
            ret.put("success", true);
            ret.put("message", "Estacionamentos recuperados com sucesso.");
            ret.put("data", estacionamentosEncontrados);
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
                throw new Exception("O id informado não pertence a nenhum usuário.");
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
    public Map<String, Object> updateEstacionamento(@PathVariable Long id, @RequestBody Estacionamento estacionamento) {
        Map<String, Object> ret = new HashMap<String, Object>();
        try {
            estacionamento = service.updateEstacionamento(id, estacionamento);
            ret.put("success", true);
            ret.put("message", estacionamento.getNome() + " atualizado com sucesso.");
            ret.put("data", estacionamento);
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
