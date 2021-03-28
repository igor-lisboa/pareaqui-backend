package com.uff.pareaqui.service;

import java.util.List;

import com.uff.pareaqui.entity.VagaTipo;
import com.uff.pareaqui.repository.VagaTipoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VagaTipoService {
    @Autowired
    private VagaTipoRepository repository;

    public VagaTipo saveVagaTipo(VagaTipo vagaTipo) {
        return repository.save(vagaTipo);
    }

    public List<VagaTipo> getVagaTipos() {
        return repository.findAll();
    }

    public void deleteVagaTipo(Long id) {
        repository.deleteById(id);
    }
}
