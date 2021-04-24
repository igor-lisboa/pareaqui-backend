package com.uff.pareaqui.controller;

import java.util.HashMap;
import java.util.Map;

import com.uff.pareaqui.service.VagaAcidenteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/vaga/acidente")
public class VagaAcidenteController {
    @Autowired
    private VagaAcidenteService service;

    @PostMapping
    public Map<String, Object> addEstacionamentoVaga(@RequestBody Map<String, ?> input) {
        Map<String, Object> ret = new HashMap<String, Object>();
        try {
            String descricao = String.valueOf((Object) input.get("descricao"));
            if (descricao == "null") {
                descricao = null;
            }
            String momento = String.valueOf((Object) input.get("momento"));
            if (momento == "null") {
                momento = null;
            }
            ret.put("data", service.saveVagaAcidenteCompleta(
                    Long.parseLong(String.valueOf((Object) input.get("vaga_id"))), descricao, momento));
            ret.put("success", true);
            ret.put("message", "O acidente foi cadastrado.");
        } catch (Exception exception) {
            ret.put("success", false);
            ret.put("data", null);
            ret.put("message", exception.getMessage());
        }
        return ret;
    }
}
