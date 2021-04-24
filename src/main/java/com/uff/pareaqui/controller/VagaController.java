package com.uff.pareaqui.controller;

import java.util.HashMap;
import java.util.Map;

import javax.transaction.Transactional;

import com.uff.pareaqui.service.VagaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Transactional
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/vaga")
public class VagaController {
    @Autowired
    VagaService service;

    @GetMapping
    public Map<String, Object> getVagasFiltradas(@RequestParam Map<String, ?> input) {
        Map<String, Object> ret = new HashMap<String, Object>();
        try {
            String menorPreco = String.valueOf((Object) input.get("menor_preco"));
            if (menorPreco == "null") {
                menorPreco = null;
            }
            String maiorPreco = String.valueOf((Object) input.get("maior_preco"));
            if (maiorPreco == "null") {
                maiorPreco = null;
            }
            String tiposEscolhidos = String.valueOf((Object) input.get("tipos_escolhidos"));
            if (tiposEscolhidos == "null") {
                tiposEscolhidos = null;
            }
            String tamanhosEscolhidos = String.valueOf((Object) input.get("tamanhos_escolhidos"));
            if (tamanhosEscolhidos == "null") {
                tamanhosEscolhidos = null;
            }
            Boolean estacionamento = String.valueOf((Object) input.get("estacionamento")).equals("1");
            Boolean semAcidentes = String.valueOf((Object) input.get("sem_acidentes")).equals("1");
            Boolean semFlanelinha = String.valueOf((Object) input.get("sem_flanelinha")).equals("1");
            Boolean orderPreco = String.valueOf((Object) input.get("order_preco")).equals("1");
            Boolean orderPrecoAsc = String.valueOf((Object) input.get("order_preco_asc")).equals("1");
            ret.put("data", service.filtraVagas(menorPreco, maiorPreco, tiposEscolhidos, tamanhosEscolhidos,
                    estacionamento, semAcidentes, semFlanelinha, orderPreco, orderPrecoAsc));
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
