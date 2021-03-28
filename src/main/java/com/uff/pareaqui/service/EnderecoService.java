package com.uff.pareaqui.service;

import java.util.List;

import com.uff.pareaqui.entity.Endereco;
import com.uff.pareaqui.repository.EnderecoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnderecoService {
    @Autowired
    private EnderecoRepository repository;

    public Endereco saveEndereco(Endereco endereco) {
        return repository.save(endereco);
    }

    public List<Endereco> getEnderecos() {
        return repository.findAll();
    }

    public Endereco getEnderecoById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void deleteEndereco(Long id) {
        repository.deleteById(id);
    }

    public Endereco updateEndereco(Long id, Endereco endereco) {
        Endereco enderecoAtualiza = repository.getOne(id);
        enderecoAtualiza.setRua(endereco.getRua());
        enderecoAtualiza.setComplemento(endereco.getComplemento());
        enderecoAtualiza.setNumero(endereco.getNumero());
        enderecoAtualiza.setBairro(endereco.getBairro());
        enderecoAtualiza.setCidade(endereco.getCidade());
        enderecoAtualiza.setEstado(endereco.getEstado());
        enderecoAtualiza.setPais(endereco.getPais());
        return this.saveEndereco(enderecoAtualiza);
    }
}
