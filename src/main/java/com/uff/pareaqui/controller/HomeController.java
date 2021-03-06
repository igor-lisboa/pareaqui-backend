package com.uff.pareaqui.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping
public class HomeController {
    @GetMapping("")
    public Map<String, Object> welcome() {
        Map<String, Object> ret = new HashMap<String, Object>();
        ret.put("success", true);
        ret.put("message", "Olá a documentação está no POSTMAN");
        Map<String, Object> documentacao = new HashMap<String, Object>();
        documentacao.put("postman", "https://documenter.getpostman.com/view/15046943/TzJvfdJj");
        documentacao.put("github", "https://github.com/igor-lisboa/pareaqui-backend");
        ret.put("data", documentacao);
        return ret;
    }
}
