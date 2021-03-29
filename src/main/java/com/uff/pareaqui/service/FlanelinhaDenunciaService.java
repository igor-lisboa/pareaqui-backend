package com.uff.pareaqui.service;

import com.uff.pareaqui.entity.FlanelinhaDenuncia;
import com.uff.pareaqui.repository.FlanelinhaDenunciaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FlanelinhaDenunciaService {
    @Autowired
    private FlanelinhaDenunciaRepository repository;

    public FlanelinhaDenuncia saveFlanelinhaDenuncia(FlanelinhaDenuncia flanelinhaDenuncia) {
        return repository.save(flanelinhaDenuncia);
    }
}
