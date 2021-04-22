package com.uff.pareaqui.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.uff.pareaqui.entity.VagaTipo;
import com.uff.pareaqui.service.VagaTipoService;

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
@RequestMapping("/vaga-tipo")
public class VagaTipoController {

    @Autowired
    private VagaTipoService service;

    @PostMapping
    public Map<String, Object> addVagaTipo(@RequestBody Map<String, ?> input) {
        Map<String, Object> ret = new HashMap<String, Object>();
        try {
            ret.put("data", service.saveVagaTipo(String.valueOf((Object) input.get("img")),
                    String.valueOf((Object) input.get("tipo"))));
            ret.put("success", true);
            ret.put("message", "O Tipo de Vaga foi cadastrado com sucesso.");
        } catch (Exception exception) {
            ret.put("success", false);
            ret.put("data", null);
            ret.put("message", exception.getMessage());
        }
        return ret;
    }

    @GetMapping
    public Map<String, Object> findAllVagaTipos() {
        Map<String, Object> ret = new HashMap<String, Object>();
        try {
            ret.put("data", service.getVagaTipos());
            ret.put("success", true);
            ret.put("message", "Tipos de Vaga recuperados com sucesso.");
        } catch (Exception exception) {
            ret.put("success", false);
            ret.put("data", null);
            ret.put("message", exception.getMessage());
        }
        return ret;
    }

    @DeleteMapping("/{id}")
    public Map<String, Object> deleteVagaTipo(@PathVariable Long id) {
        Map<String, Object> ret = new HashMap<String, Object>();
        try {
            service.deleteVagaTipo(id);
            ret.put("success", true);
            ret.put("message", "Tipo de Vaga " + id + " deletado com sucesso.");
            ret.put("data", null);
        } catch (Exception exception) {
            ret.put("success", false);
            ret.put("data", null);
            ret.put("message", exception.getMessage());
        }
        return ret;
    }
}
