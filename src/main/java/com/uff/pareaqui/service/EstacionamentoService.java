package com.uff.pareaqui.service;

import java.util.List;

import com.uff.pareaqui.entity.Estacionamento;
import com.uff.pareaqui.entity.Usuario;
import com.uff.pareaqui.repository.EstacionamentoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstacionamentoService {
    @Autowired
    private EstacionamentoRepository repository;

    @Autowired
    private UsuarioService usuarioService;

    public Estacionamento saveEstacionamento(Estacionamento estacionamento) {
        return repository.save(estacionamento);
    }

    public Estacionamento saveEstacionamentoCompleto(Long donoId, String nome, String rua, String numero, String bairro,
            String complemento, String cidade, String estado, String pais) throws Exception {
        Estacionamento estacionamento = new Estacionamento();

        Usuario dono = usuarioService.getUsuarioById(donoId);
        if (dono == null) {
            throw new Exception("O id informado para o dono não pertence a nenhum usuário válido");
        }

        estacionamento.setCampos(nome, dono, rua, numero, complemento, bairro, cidade, estado, pais);

        return this.saveEstacionamento(estacionamento);
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

    public Estacionamento updateEstacionamentoCompleto(Long id, Long donoId, String nome, String rua, String numero,
            String bairro, String complemento, String cidade, String estado, String pais) throws Exception {
        Usuario dono = usuarioService.getUsuarioById(donoId);
        if (dono == null) {
            throw new Exception("O id informado para o dono não pertence a nenhum usuário válido");
        }
        Estacionamento estacionamento = new Estacionamento();
        estacionamento.setCampos(nome, dono, rua, numero, complemento, bairro, cidade, estado, pais);
        return this.updateEstacionamento(id, estacionamento);
    }
}
