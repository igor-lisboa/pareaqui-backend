package com.uff.pareaqui.controller;

import java.util.HashMap;
import java.util.Map;

import com.uff.pareaqui.service.VagaOcupacaoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/vaga/ocupacao")
public class VagaOcupacaoController {

    @Autowired
    private VagaOcupacaoService service;

    @PostMapping
    public Map<String, Object> incluiOcupacao(@RequestBody Map<String, ?> input) {
        Map<String, Object> ret = new HashMap<String, Object>();
        try {
            ret.put("data",
                    service.registraEntrada(Long.parseLong(String.valueOf((Object) input.get("vaga_id"))),
                            Long.parseLong(String.valueOf((Object) input.get("usuario_id"))),
                            String.valueOf((Object) input.get("entrada"))));
            ret.put("success", true);
            ret.put("message", "A vaga foi ocupada com sucesso.");
        } catch (Exception exception) {
            ret.put("success", false);
            ret.put("data", null);
            ret.put("message", exception.getMessage());
        }
        return ret;
    }

    @GetMapping("/usuario/{id}/historico")
    public Map<String, Object> getHitoricoUsuario(@PathVariable Long id) {
        Map<String, Object> ret = new HashMap<String, Object>();
        try {
            ret.put("data", service.historicoOcupacoesUsuario(id));
            ret.put("success", true);
            ret.put("message", "O histórico de vagas do usuário foi recuperado com sucesso.");
        } catch (Exception exception) {
            ret.put("success", false);
            ret.put("data", null);
            ret.put("message", exception.getMessage());
        }
        return ret;
    }

    @GetMapping("/{id}")
    public Map<String, Object> getVagaOcupacaoByVagaId(@PathVariable Long id) {
        Map<String, Object> ret = new HashMap<String, Object>();
        try {
            ret.put("data", service.getVagaOcupada(id));
            ret.put("success", true);
            ret.put("message", "A ocupação da vaga recuperada com sucesso.");
        } catch (Exception exception) {
            ret.put("success", false);
            ret.put("data", null);
            ret.put("message", exception.getMessage());
        }
        return ret;
    }

    @PutMapping("/{id}")
    public Map<String, Object> atualizaSaida(@PathVariable Long id, @RequestBody Map<String, ?> input) {
        Map<String, Object> ret = new HashMap<String, Object>();
        try {
            ret.put("data", service.registraSaida(id, String.valueOf((Object) input.get("saida"))));
            ret.put("success", true);
            ret.put("message", "A vaga foi desocupada com sucesso.");
        } catch (Exception exception) {
            ret.put("success", false);
            ret.put("data", null);
            ret.put("message", exception.getMessage());
        }
        return ret;
    }
}
