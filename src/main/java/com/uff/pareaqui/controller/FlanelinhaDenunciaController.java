package com.uff.pareaqui.controller;

import java.util.HashMap;
import java.util.Map;

import javax.transaction.Transactional;

import com.uff.pareaqui.entity.FlanelinhaDenuncia;
import com.uff.pareaqui.service.FlanelinhaDenunciaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Transactional
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/flanelinha")
public class FlanelinhaDenunciaController {

    @Autowired
    private FlanelinhaDenunciaService service;

    @PostMapping("/denuncia")
    public Map<String, Object> addFlanelinhaDenuncia(@RequestBody FlanelinhaDenuncia flanelinhaDenuncia) {
        Map<String, Object> ret = new HashMap<String, Object>();
        try {
            ret.put("success", true);
            ret.put("message", "A Denúncia do Flanelinha foi cadastrado com sucesso.");
            ret.put("data", service.saveFlanelinhaDenuncia(flanelinhaDenuncia));
        } catch (Exception exception) {
            ret.put("success", false);
            ret.put("data", null);
            ret.put("message", exception.getMessage());
        }
        return ret;
    }
}
