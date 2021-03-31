package com.uff.pareaqui.service;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import com.uff.pareaqui.entity.Usuario;
import com.uff.pareaqui.entity.Vaga;
import com.uff.pareaqui.entity.VagaAgendamento;
import com.uff.pareaqui.repository.VagaAgendamentoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VagaAgendamentoService {
    @Autowired
    private VagaAgendamentoRepository repository;

    @Autowired
    private VagaService vagaService;

    @Autowired
    private UsuarioService usuarioService;

    public VagaAgendamento saveVagaAgendamento(VagaAgendamento vagaAgendamento) {
        return repository.save(vagaAgendamento);
    }

    public VagaAgendamento getVagaAgendamento(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Collection<VagaAgendamento> getVagaAgendamentosFuturosByUsuarioId(Long usuarioId) {
        return repository.findByUsuarioId(usuarioId);
    }

    public VagaAgendamento saveVagaAgendamentoCompleta(Long vagaId, Long usuarioId, String momentoString)
            throws Exception {
        Vaga vaga = vagaService.getVaga(vagaId);
        if (vaga == null) {
            throw new Exception("Informe uma vaga válida");
        }

        Usuario usuario = usuarioService.getUsuarioById(usuarioId);
        if (usuario == null) {
            throw new Exception("Informe um usuário válido");
        }

        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date momento = formater.parse(momentoString);
        Date agora = new Date();
        if (momento.before(agora)) {
            throw new Exception("Informe um momento futuro | [agora] => " + agora.toString()
                    + " | [momento informado] => " + momento.toString());
        }

        VagaAgendamento vagaAgendamento = new VagaAgendamento();
        vagaAgendamento.setCampos(vaga, usuario, momento);
        return this.saveVagaAgendamento(vagaAgendamento);
    }

    public void deleteVagaAgendamento(Long id) {
        repository.deleteById(id);
    }
}
