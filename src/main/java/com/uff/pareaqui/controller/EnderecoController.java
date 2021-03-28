package com.uff.pareaqui.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.uff.pareaqui.entity.Endereco;
import com.uff.pareaqui.service.EnderecoService;

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
@RequestMapping("/endereco")
public class EnderecoController {
    @Autowired
    private EnderecoService service;

    @PostMapping
    public Map<String, Object> addEndereco(@RequestBody Endereco endereco) {
        Map<String, Object> ret = new HashMap<String, Object>();
        try {
            endereco = service.saveEndereco(endereco);

            ret.put("success", true);
            ret.put("message", "Endereço cadastrado com sucesso.");
            ret.put("data", endereco);
        } catch (Exception exception) {
            ret.put("success", false);
            ret.put("data", null);
            ret.put("message", exception.getMessage());
        }
        return ret;
    }

    @GetMapping
    public Map<String, Object> findAllEnderecos() {
        Map<String, Object> ret = new HashMap<String, Object>();
        try {
            List<Endereco> enderecosEncontrados = service.getEnderecos();
            ret.put("success", true);
            ret.put("message", "Endereços recuperados com sucesso.");
            ret.put("data", enderecosEncontrados);
        } catch (Exception exception) {
            ret.put("success", false);
            ret.put("data", null);
            ret.put("message", exception.getMessage());
        }
        return ret;
    }

    @GetMapping("/{id}")
    public Map<String, Object> findEnderecoById(@PathVariable Long id) {
        Map<String, Object> ret = new HashMap<String, Object>();
        try {
            Endereco endereco = service.getEnderecoById(id);
            if (endereco == null) {
                throw new Exception("O id informado não pertence a nenhum usuário.");
            }
            ret.put("success", true);
            ret.put("message", "Endereço recuperado com sucesso.");
            ret.put("data", endereco);
        } catch (Exception exception) {
            ret.put("success", false);
            ret.put("data", null);
            ret.put("message", exception.getMessage());
        }
        return ret;
    }

    @PutMapping("/{id}")
    public Map<String, Object> updateEndereco(@PathVariable Long id, @RequestBody Endereco endereco) {
        Map<String, Object> ret = new HashMap<String, Object>();
        try {
            endereco = service.updateEndereco(id, endereco);
            ret.put("success", true);
            ret.put("message", "Endereço atualizado com sucesso.");
            ret.put("data", endereco);
        } catch (Exception exception) {
            ret.put("success", false);
            ret.put("data", null);
            ret.put("message", exception.getMessage());
        }
        return ret;
    }

    @DeleteMapping("/{id}")
    public Map<String, Object> deleteEndereco(@PathVariable Long id) {
        Map<String, Object> ret = new HashMap<String, Object>();
        try {
            service.deleteEndereco(id);
            ret.put("success", true);
            ret.put("message", "Endereço " + id + " deletado com sucesso.");
            ret.put("data", null);
        } catch (Exception exception) {
            ret.put("success", false);
            ret.put("data", null);
            ret.put("message", exception.getMessage());
        }
        return ret;
    }
}
