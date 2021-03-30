package com.uff.pareaqui.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.uff.pareaqui.entity.EstacionamentoVaga;
import com.uff.pareaqui.service.EstacionamentoVagaService;

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
@RequestMapping("/estacionamento/vaga")
public class EstacionamentoVagaController {
    @Autowired
    private EstacionamentoVagaService service;

    @PostMapping
    public Map<String, Object> addEstacionamentoVaga(@RequestBody Map<String, ?> input) {
        Map<String, Object> ret = new HashMap<String, Object>();
        try {
            Long estacionamentoId = Long.parseLong(String.valueOf((Object) input.get("estacionamento_id")));
            Long vagaTipoId = Long.parseLong(String.valueOf((Object) input.get("vaga_tipo_id")));
            Long vagaTamanhoId = Long.parseLong(String.valueOf((Object) input.get("vaga_tamanho_id")));
            Float preco = Float.parseFloat(String.valueOf((Object) input.get("preco")));
            String identificacao = String.valueOf((Object) input.get("identificacao"));

            EstacionamentoVaga estacionamentoVaga = service.saveEstacionamentoVagaCompleta(estacionamentoId, vagaTipoId,
                    vagaTamanhoId, preco, identificacao);

            ret.put("success", true);
            ret.put("message", "A vaga do estacionamento foi adicionada.");
            ret.put("data", estacionamentoVaga);
        } catch (Exception exception) {
            ret.put("success", false);
            ret.put("data", null);
            ret.put("message", exception.getMessage());
        }
        return ret;
    }

    @PutMapping("/{id}")
    public Map<String, Object> updateEstacionamentoVaga(@PathVariable Long id, @RequestBody Map<String, ?> input) {
        Map<String, Object> ret = new HashMap<String, Object>();
        try {
            Long estacionamentoId = Long.parseLong(String.valueOf((Object) input.get("estacionamento_id")));
            Long vagaTipoId = Long.parseLong(String.valueOf((Object) input.get("vaga_tipo_id")));
            Long vagaTamanhoId = Long.parseLong(String.valueOf((Object) input.get("vaga_tamanho_id")));
            Float preco = Float.parseFloat(String.valueOf((Object) input.get("preco")));
            String identificacao = String.valueOf((Object) input.get("identificacao"));

            EstacionamentoVaga estacionamentoVaga = service.updateEstacionamentoVagaCompleta(id, estacionamentoId,
                    vagaTipoId, vagaTamanhoId, preco, identificacao);
            ret.put("success", true);
            ret.put("message", "Vaga do estacionamento " + estacionamentoVaga.getEstacionamento().getNome()
                    + " recuperada com sucesso.");
            ret.put("data", estacionamentoVaga);
        } catch (Exception exception) {
            ret.put("success", false);
            ret.put("data", null);
            ret.put("message", exception.getMessage());
        }
        return ret;
    }

    @GetMapping("/{id}")
    public Map<String, Object> findEstacionamentoVaga(@PathVariable Long id) {
        Map<String, Object> ret = new HashMap<String, Object>();
        try {
            EstacionamentoVaga estacionamentoVaga = service.getEstacionamentoVaga(id);
            if (estacionamentoVaga == null) {
                throw new Exception("O id informado não pertence a nenhuma vaga de estacionamento.");
            }
            ret.put("success", true);
            ret.put("message", "Vaga do estacionamento " + estacionamentoVaga.getEstacionamento().getNome()
                    + " recuperada com sucesso.");
            ret.put("data", estacionamentoVaga);
        } catch (Exception exception) {
            ret.put("success", false);
            ret.put("data", null);
            ret.put("message", exception.getMessage());
        }
        return ret;
    }

    @GetMapping("/{id}/todas")
    public Map<String, Object> findEstacionamentoVagas(@PathVariable Long id) {
        Map<String, Object> ret = new HashMap<String, Object>();
        try {
            Collection<EstacionamentoVaga> estacionamentoVagas = service.getEstacionamentoVagasByEstacionamentoId(id);
            ret.put("success", true);
            ret.put("message", "Vagas do estacionamento " + id + " recuperadas com sucesso.");
            ret.put("data", estacionamentoVagas);
        } catch (Exception exception) {
            ret.put("success", false);
            ret.put("data", null);
            ret.put("message", exception.getMessage());
        }
        return ret;
    }

    @DeleteMapping("/{id}")
    public Map<String, Object> deleteEstacionamentoVaga(@PathVariable Long id) {
        Map<String, Object> ret = new HashMap<String, Object>();
        try {
            service.deleteEstacionamentoVaga(id);
            ret.put("success", true);
            ret.put("message", "A vaga do estacionamento foi deletada com sucesso.");
            ret.put("data", null);
        } catch (Exception exception) {
            ret.put("success", false);
            ret.put("data", null);
            ret.put("message", exception.getMessage());
        }
        return ret;
    }
}
