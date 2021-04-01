package com.uff.pareaqui.controller;

import java.util.HashMap;
import java.util.Map;

import com.uff.pareaqui.service.VagaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vaga")
public class VagaController {
    @Autowired
    VagaService service;

    @GetMapping
    public Map<String, Object> getVagasFiltradas(@RequestParam Map<String, ?> input) {
        Map<String, Object> ret = new HashMap<String, Object>();
        try {
            ret.put("data",
                    service.filtraVagas(String.valueOf((Object) input.get("menor_preco")),
                            String.valueOf((Object) input.get("maior_preco")),
                            String.valueOf((Object) input.get("tipos_escolhidos")),
                            String.valueOf((Object) input.get("tamanhos_escolhidos")),
                            String.valueOf((Object) input.get("estacionamento")).equals("1"),
                            String.valueOf((Object) input.get("sem_acidentes")).equals("1"),
                            String.valueOf((Object) input.get("sem_flanelinha")).equals("1"),
                            String.valueOf((Object) input.get("order_preco")).equals("1"),
                            String.valueOf((Object) input.get("order_preco_asc")).equals("1")));
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
