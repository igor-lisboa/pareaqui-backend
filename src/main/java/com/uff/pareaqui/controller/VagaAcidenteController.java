package com.uff.pareaqui.controller;

import java.util.HashMap;
import java.util.Map;

import com.uff.pareaqui.service.VagaAcidenteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/vaga/acidente")
public class VagaAcidenteController {
    @Autowired
    private VagaAcidenteService service;

    @PostMapping
    public Map<String, Object> addEstacionamentoVaga(@RequestBody Map<String, ?> input) {
        Map<String, Object> ret = new HashMap<String, Object>();
        try {
            ret.put("data",
                    service.saveVagaAcidenteCompleta(Long.parseLong(String.valueOf((Object) input.get("vaga_id"))),
                            String.valueOf((Object) input.get("descricao")),
                            String.valueOf((Object) input.get("momento"))));
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
