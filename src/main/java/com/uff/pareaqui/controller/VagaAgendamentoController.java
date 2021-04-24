package com.uff.pareaqui.controller;

import java.util.HashMap;
import java.util.Map;

import javax.transaction.Transactional;

import com.uff.pareaqui.service.VagaAgendamentoService;

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
@Transactional
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/vaga/agendamento")
public class VagaAgendamentoController {
    @Autowired
    private VagaAgendamentoService service;

    @PostMapping
    public Map<String, Object> addVagaAgendamento(@RequestBody Map<String, ?> input) {
        Map<String, Object> ret = new HashMap<String, Object>();
        try {
            ret.put("data",
                    service.saveVagaAgendamentoCompleta(Long.parseLong(String.valueOf((Object) input.get("vaga_id"))),
                            Long.parseLong(String.valueOf((Object) input.get("usuario_id"))),
                            String.valueOf((Object) input.get("momento"))));
            ret.put("success", true);
            ret.put("message", "O agendamento da Vaga foi cadastrado com sucesso.");
        } catch (Exception exception) {
            ret.put("success", false);
            ret.put("data", null);
            ret.put("message", exception.getMessage());
        }
        return ret;
    }

    @GetMapping("/{id}")
    public Map<String, Object> findVagaAgendamentosByUsuarioId(@PathVariable Long id) {
        Map<String, Object> ret = new HashMap<String, Object>();
        try {
            ret.put("data", service.getVagaAgendamentosFuturosByUsuarioId(id));
            ret.put("success", true);
            ret.put("message", "Agendamentos futuros de vaga recuperados com sucesso.");
        } catch (Exception exception) {
            ret.put("success", false);
            ret.put("data", null);
            ret.put("message", exception.getMessage());
        }
        return ret;
    }

    @DeleteMapping("/{id}")
    public Map<String, Object> deleteVagaAgendamento(@PathVariable Long id) {
        Map<String, Object> ret = new HashMap<String, Object>();
        try {
            service.deleteVagaAgendamento(id);
            ret.put("success", true);
            ret.put("message", "Agendamento de Vaga " + id + " cancelado com sucesso.");
            ret.put("data", null);
        } catch (Exception exception) {
            ret.put("success", false);
            ret.put("data", null);
            ret.put("message", exception.getMessage());
        }
        return ret;
    }
}
