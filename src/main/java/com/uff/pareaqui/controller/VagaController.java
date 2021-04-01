package com.uff.pareaqui.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.uff.pareaqui.service.VagaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vaga")
public class VagaController {
    @Autowired
    VagaService service;

    @GetMapping
    public Map<String, Object> getVagasFiltradas() {
        Map<String, Object> ret = new HashMap<String, Object>();
        try {
            ret.put("data", service.filtraVagas());
            ret.put("success", true);
            ret.put("message", "Vagas recuperadas com sucesso.");
        } catch (Exception exception) {
            ret.put("success", false);
            ret.put("data", null);
            ret.put("message", exception.getMessage());
        }
        return ret;
    }
}
