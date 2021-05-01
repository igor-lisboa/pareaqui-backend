package com.uff.pareaqui.controller;

import java.util.HashMap;
import java.util.Map;

import com.uff.pareaqui.entity.VagaTamanho;
import com.uff.pareaqui.service.VagaTamanhoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/vaga-tamanho")
public class VagaTamanhoController {

    @Autowired
    private VagaTamanhoService service;

    @PostMapping
    public Map<String, Object> addVagaTamanho(@RequestBody VagaTamanho vagaTamanho) {
        Map<String, Object> ret = new HashMap<String, Object>();
        try {
            ret.put("data", service.saveVagaTamanho(vagaTamanho));
            ret.put("success", true);
            ret.put("message", "O Tamanho de Vaga foi cadastrado com sucesso.");
        } catch (Exception exception) {
            ret.put("success", false);
            ret.put("data", null);
            ret.put("message", exception.getMessage());
        }
        return ret;
    }

    @GetMapping
    public Map<String, Object> findAllVagaTamanhos() {
        Map<String, Object> ret = new HashMap<String, Object>();
        try {
            ret.put("data", service.getVagaTamanhos());
            ret.put("success", true);
            ret.put("message", "Tamanhos de Vaga recuperados com sucesso.");
        } catch (Exception exception) {
            ret.put("success", false);
            ret.put("data", null);
            ret.put("message", exception.getMessage());
        }
        return ret;
    }

    @DeleteMapping("/{id}")
    public Map<String, Object> deleteVagaTamanho(@PathVariable Long id) {
        Map<String, Object> ret = new HashMap<String, Object>();
        try {
            service.deleteVagaTamanho(id);
            ret.put("success", true);
            ret.put("message", "Tamanho de Vaga " + id + " deletado com sucesso.");
            ret.put("data", null);
        } catch (Exception exception) {
            ret.put("success", false);
            ret.put("data", null);
            ret.put("message", exception.getMessage());
        }
        return ret;
    }
}
