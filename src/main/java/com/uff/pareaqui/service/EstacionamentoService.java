package com.uff.pareaqui.service;

import java.util.List;

import com.uff.pareaqui.entity.Estacionamento;
import com.uff.pareaqui.repository.EstacionamentoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstacionamentoService {
    @Autowired
    private EstacionamentoRepository repository;

    public Estacionamento saveEstacionamento(Estacionamento estacionamento) {
        return repository.save(estacionamento);
    }

    public List<Estacionamento> getEstacionamentos() {
        return repository.findAll();
    }

    public Estacionamento getEstacionamentoById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void deleteEstacionamento(Long id) {
        repository.deleteById(id);
    }

    public Estacionamento updateEstacionamento(Long id, Estacionamento estacionamento) {
        Estacionamento estacionamentoAtualiza = repository.getOne(id);
        estacionamentoAtualiza.setCampos(estacionamento.getNome(), estacionamento.getDono(), estacionamento.getRua(),
                estacionamento.getNumero(), estacionamento.getComplemento(), estacionamento.getBairro(),
                estacionamento.getCidade(), estacionamento.getEstado(), estacionamento.getPais());
        return this.saveEstacionamento(estacionamentoAtualiza);
    }
}
