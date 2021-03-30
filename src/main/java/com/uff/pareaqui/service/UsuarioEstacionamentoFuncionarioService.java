package com.uff.pareaqui.service;

import java.util.Collection;
import java.util.List;

import com.uff.pareaqui.entity.UsuarioEstacionamentoFuncionario;
import com.uff.pareaqui.repository.UsuarioEstacionamentoFuncionarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioEstacionamentoFuncionarioService {
    @Autowired
    private UsuarioEstacionamentoFuncionarioRepository repository;

    public UsuarioEstacionamentoFuncionario saveUsuarioEstacionamentoFuncionario(
            UsuarioEstacionamentoFuncionario estacionamento) {
        return repository.save(estacionamento);
    }

    public List<UsuarioEstacionamentoFuncionario> getUsuarioEstacionamentoFuncionarios() {
        return repository.findAll();
    }

    public UsuarioEstacionamentoFuncionario getUsuarioEstacionamentoFuncionarioById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Collection<UsuarioEstacionamentoFuncionario> getUsuarioEstacionamentoFuncionarioByEstacionamentoId(
            Long estacionamentoId) {
        return repository.findByEstacionamentoId(estacionamentoId);
    }

    public UsuarioEstacionamentoFuncionario getUsuarioEstacionamentoFuncionarioByEstacionamentoIdAndUsuarioId(
            Long estacionamentoId, Long usuarioId) {
        Collection<UsuarioEstacionamentoFuncionario> funcionarios = repository
                .findByEstacionamentoIdAndUsuarioId(estacionamentoId, usuarioId);
        if (funcionarios.size() > 0) {
            return (UsuarioEstacionamentoFuncionario) funcionarios.toArray()[0];
        }
        return null;
    }

    public void deleteUsuarioEstacionamentoFuncionario(Long id) {
        repository.deleteById(id);
    }
}
