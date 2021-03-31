package com.uff.pareaqui.service;

import java.util.Collection;
import java.util.List;

import com.uff.pareaqui.entity.Estacionamento;
import com.uff.pareaqui.entity.Usuario;
import com.uff.pareaqui.entity.UsuarioEstacionamentoFuncionario;
import com.uff.pareaqui.repository.UsuarioEstacionamentoFuncionarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioEstacionamentoFuncionarioService {
    @Autowired
    private UsuarioEstacionamentoFuncionarioRepository repository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private EstacionamentoService estacionamentoService;

    public UsuarioEstacionamentoFuncionario saveUsuarioEstacionamentoFuncionario(
            UsuarioEstacionamentoFuncionario estacionamento) {
        return repository.save(estacionamento);
    }

    public UsuarioEstacionamentoFuncionario saveUsuarioEstacionamentoFuncionarioCompleto(Long usuarioId,
            Long estacionamentoId) throws Exception {

        UsuarioEstacionamentoFuncionario usuarioEstacionamentoFuncionario = new UsuarioEstacionamentoFuncionario();

        Usuario usuario = usuarioService.getUsuarioById(usuarioId);
        if (usuario == null) {
            throw new Exception("O usuário escolhido para ser promovido a funcionário de um estacionamento não existe");
        }
        usuarioEstacionamentoFuncionario.setUsuario(usuario);

        Estacionamento estacionamento = estacionamentoService.getEstacionamentoById(estacionamentoId);
        if (estacionamento == null) {
            throw new Exception("O estacionamento escolhido não existe");
        }
        usuarioEstacionamentoFuncionario.setEstacionamento(estacionamento);

        UsuarioEstacionamentoFuncionario usuarioFuncionario = this
                .getUsuarioEstacionamentoFuncionarioByEstacionamentoIdAndUsuarioId(estacionamento.getId(),
                        usuario.getId());
        if (usuarioFuncionario != null) {
            throw new Exception(
                    "O usuário escolhido para ser promovido a funcionário do estacionamento já foi promovido");
        }

        return this.saveUsuarioEstacionamentoFuncionario(usuarioEstacionamentoFuncionario);
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
