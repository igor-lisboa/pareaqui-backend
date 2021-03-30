package com.uff.pareaqui.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.uff.pareaqui.entity.Estacionamento;
import com.uff.pareaqui.entity.Usuario;
import com.uff.pareaqui.entity.UsuarioEstacionamentoFuncionario;
import com.uff.pareaqui.service.EstacionamentoService;
import com.uff.pareaqui.service.UsuarioEstacionamentoFuncionarioService;
import com.uff.pareaqui.service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/estacionamento/funcionario")
public class UsuarioEstacionamentoFuncionarioController {

    @Autowired
    private UsuarioEstacionamentoFuncionarioService service;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private EstacionamentoService estacionamentoService;

    @PostMapping
    public Map<String, Object> addUsuarioEstacionamentoFuncionario(@RequestBody Map<String, ?> input) {
        Map<String, Object> ret = new HashMap<String, Object>();
        try {
            UsuarioEstacionamentoFuncionario usuarioEstacionamentoFuncionario = new UsuarioEstacionamentoFuncionario();

            // trata usuario
            Object usuarioId = input.get("usuario_id");
            if (usuarioId == null) {
                throw new Exception("Informe o id do usuário que será promovido a funcionário de um estacionamento");
            }
            Usuario usuario = usuarioService.getUsuarioById(Long.parseLong(usuarioId.toString()));
            if (usuario == null) {
                throw new Exception(
                        "O usuário escolhido para ser promovido a funcionário de um estacionamento não existe");
            }
            usuarioEstacionamentoFuncionario.setUsuario(usuario);

            // trata estacionamento
            Object estacionamentoId = input.get("estacionamento_id");
            if (estacionamentoId == null) {
                throw new Exception("Informe o id de um estacionamento");
            }
            Estacionamento estacionamento = estacionamentoService
                    .getEstacionamentoById(Long.parseLong(estacionamentoId.toString()));
            if (estacionamento == null) {
                throw new Exception("O estacionamento escolhido não existe");
            }
            usuarioEstacionamentoFuncionario.setEstacionamento(estacionamento);

            UsuarioEstacionamentoFuncionario usuarioFuncionario = service
                    .getUsuarioEstacionamentoFuncionarioByEstacionamentoIdAndUsuarioId(estacionamento.getId(),
                            usuario.getId());
            if (usuarioFuncionario != null) {
                throw new Exception(
                        "O usuário escolhido para ser promovido a funcionário do estacionamento já foi promovido");
            }

            usuarioEstacionamentoFuncionario = service
                    .saveUsuarioEstacionamentoFuncionario(usuarioEstacionamentoFuncionario);
            ret.put("success", true);
            ret.put("message", "O Usuário foi cadastrado como funcionário " + usuarioEstacionamentoFuncionario.getId()
                    + " do estacionamento " + estacionamento.getNome() + " com sucesso.");
            ret.put("data", usuarioEstacionamentoFuncionario);
        } catch (Exception exception) {
            ret.put("success", false);
            ret.put("data", null);
            ret.put("message", exception.getMessage());
        }
        return ret;
    }

    @GetMapping("/{id}")
    public Map<String, Object> findUsuarioEstacionamentoFuncionarioById(
            @PathVariable Long id) {
        Map<String, Object> ret = new HashMap<String, Object>();
        try {
            UsuarioEstacionamentoFuncionario usuarioFuncionario = service.getUsuarioEstacionamentoFuncionarioById(id);
            if (usuarioFuncionario == null) {
                throw new Exception("O funcionário de estacionamento desejado não existe");
            }
            ret.put("success", true);
            ret.put("message", "Funcionário de estacionamento recuperado com sucesso.");
            ret.put("data", usuarioFuncionario);
        } catch (Exception exception) {
            ret.put("success", false);
            ret.put("data", null);
            ret.put("message", exception.getMessage());
        }
        return ret;
    }

    @GetMapping("/{estacionamentoId}/todos")
    public Map<String, Object> findUsuarioEstacionamentoFuncionariosByEstacionamentoId(
            @PathVariable Long estacionamentoId) {
        Map<String, Object> ret = new HashMap<String, Object>();
        try {
            Collection<UsuarioEstacionamentoFuncionario> usuarioEstacionamentoFuncionarios = service
                    .getUsuarioEstacionamentoFuncionarioByEstacionamentoId(estacionamentoId);
            ret.put("success", true);
            ret.put("message", "Funcionários do estacionamento recuperado com sucesso.");
            ret.put("data", usuarioEstacionamentoFuncionarios);
        } catch (Exception exception) {
            ret.put("success", false);
            ret.put("data", null);
            ret.put("message", exception.getMessage());
        }
        return ret;
    }

    @GetMapping("/{estacionamentoId}/{usuarioId}")
    public Map<String, Object> findUsuarioEstacionamentoFuncionarioByEstacionamentoIdAndUsuarioId(
            @PathVariable Long estacionamentoId, @PathVariable Long usuarioId) {
        Map<String, Object> ret = new HashMap<String, Object>();
        try {
            UsuarioEstacionamentoFuncionario usuarioFuncionario = service
                    .getUsuarioEstacionamentoFuncionarioByEstacionamentoIdAndUsuarioId(estacionamentoId, usuarioId);
            if (usuarioFuncionario == null) {
                throw new Exception("Não existe funcionário para o usuário e o estacionamento escolhidos");
            }
            ret.put("success", true);
            ret.put("message", "Funcionário de estacionamento recuperado com sucesso.");
            ret.put("data", usuarioFuncionario);
        } catch (Exception exception) {
            ret.put("success", false);
            ret.put("data", null);
            ret.put("message", exception.getMessage());
        }
        return ret;
    }

    @DeleteMapping("/{id}")
    public Map<String, Object> deleteUsuarioEstacionamentoFuncionario(@PathVariable Long id) {
        Map<String, Object> ret = new HashMap<String, Object>();
        try {
            service.deleteUsuarioEstacionamentoFuncionario(id);
            ret.put("success", true);
            ret.put("message", "O usuário deixou de ser funcionário do estacionamento com sucesso.");
            ret.put("data", null);
        } catch (Exception exception) {
            ret.put("success", false);
            ret.put("data", null);
            ret.put("message", exception.getMessage());
        }
        return ret;
    }
}
