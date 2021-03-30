package com.uff.pareaqui.service;

import java.util.List;

import com.uff.pareaqui.entity.VagaTamanho;
import com.uff.pareaqui.repository.VagaTamanhoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VagaTamanhoService {
    @Autowired
    private VagaTamanhoRepository repository;

    public VagaTamanho saveVagaTamanho(VagaTamanho vagaTamanho) {
        return repository.save(vagaTamanho);
    }

    public VagaTamanho getVagaTamanhoById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public List<VagaTamanho> getVagaTamanhos() {
        return repository.findAll();
    }

    public void deleteVagaTamanho(Long id) {
        repository.deleteById(id);
    }
}
