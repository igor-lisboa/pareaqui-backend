package com.uff.pareaqui.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class HomeController {
    @GetMapping
    public Map<String, Object> welcome() {
        Map<String, Object> ret = new HashMap<String, Object>();
        ret.put("success", true);
        ret.put("message", "Olá a documentação está no POSTMAN");
        ret.put("data", null);
        return ret;
    }
}
