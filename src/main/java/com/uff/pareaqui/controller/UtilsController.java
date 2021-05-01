package com.uff.pareaqui.controller;

import java.util.HashMap;
import java.util.Map;

import com.uff.pareaqui.service.VagaService;
import com.uff.pareaqui.service.VagaTamanhoService;
import com.uff.pareaqui.service.VagaTipoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/utils")
public class UtilsController {

    @Autowired
    private VagaTipoService vagaTipoService;

    @Autowired
    private VagaTamanhoService vagaTamanhoService;

    @Autowired
    private VagaService vagaService;

    @GetMapping("/filtro-vagas")
    public Map<String, Object> findAllVagaTipos() {
        Map<String, Object> ret = new HashMap<String, Object>();
        Map<String, Object> filtros = new HashMap<String, Object>();
        try {
            filtros.put("vagaTipos", vagaTipoService.getVagaTipos());
            filtros.put("vagaTamanhos", vagaTamanhoService.getVagaTamanhos());
            filtros.put("precoMaximo", vagaService.getMaiorPrecoVagas());
            ret.put("data", filtros);
            ret.put("success", true);
            ret.put("message", "Filtros de Vaga recuperados com sucesso.");
        } catch (Exception exception) {
            ret.put("success", false);
            ret.put("data", null);
            ret.put("message", exception.getMessage());
        }
        return ret;
    }
}
