package com.uff.pareaqui.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.uff.pareaqui.entity.Estacionamento;
import com.uff.pareaqui.entity.Usuario;
import com.uff.pareaqui.service.EstacionamentoService;
import com.uff.pareaqui.service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/estacionamento")
public class EstacionamentoController {

    @Autowired
    private EstacionamentoService service;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public Map<String, Object> addEstacionamento(@RequestBody Map<String, ?> input) {
        Map<String, Object> ret = new HashMap<String, Object>();
        try {
            Estacionamento estacionamento = new Estacionamento();

            Object donoId = input.get("dono_id");
            Usuario dono = new Usuario();
            if (donoId != null) {
                dono = usuarioService.getUsuarioById(Long.parseLong(donoId.toString()));
                if (dono == null) {
                    throw new Exception("O id informado para o dono não pertence a nenhum usuário válido");
                }
            }

            String nome = null;
            Object getNome = input.get("nome");
            if (getNome == null) {
                throw new Exception("Informe um nome para o Estacionamento");
            } else {
                nome = getNome.toString();
            }

            String rua = null;
            Object getRua = input.get("rua");
            if (getRua != null) {
                rua = getRua.toString();
            }

            Long numero = null;
            Object getNumero = input.get("numero");
            if (getNumero != null) {
                numero = Long.parseLong(getNumero.toString());
            }

            String bairro = null;
            Object getBairro = input.get("bairro");
            if (getBairro != null) {
                bairro = getBairro.toString();
            }

            String complemento = null;
            Object getComplemento = input.get("complemento");
            if (getComplemento != null) {
                complemento = getComplemento.toString();
            }

            String cidade = null;
            Object getCidade = input.get("cidade");
            if (getCidade != null) {
                cidade = getCidade.toString();
            }

            String estado = null;
            Object getEstado = input.get("estado");
            if (getEstado != null) {
                estado = getEstado.toString();
            }

            String pais = null;
            Object getPais = input.get("pais");
            if (getPais != null) {
                pais = getBairro.toString();
            }

            estacionamento.setCampos(nome, dono, rua, numero, complemento, bairro, cidade, estado, pais);

            estacionamento = service.saveEstacionamento(estacionamento);

            ret.put("success", true);
            ret.put("message", "O estacionamento " + estacionamento.getNome() + " foi cadastrado com sucesso.");
            ret.put("data", estacionamento);
        } catch (Exception exception) {
            ret.put("success", false);
            ret.put("data", null);
            ret.put("message", exception.getMessage());
        }
        return ret;
    }

    @GetMapping
    public Map<String, Object> findAllEstacionamentos() {
        Map<String, Object> ret = new HashMap<String, Object>();
        try {
            List<Estacionamento> estacionamentosEncontrados = service.getEstacionamentos();
            ret.put("success", true);
            ret.put("message", "Estacionamentos recuperados com sucesso.");
            ret.put("data", estacionamentosEncontrados);
        } catch (Exception exception) {
            ret.put("success", false);
            ret.put("data", null);
            ret.put("message", exception.getMessage());
        }
        return ret;
    }

    @GetMapping("/{id}")
    public Map<String, Object> findEstacionamentoById(@PathVariable Long id) {
        Map<String, Object> ret = new HashMap<String, Object>();
        try {
            Estacionamento estacionamento = service.getEstacionamentoById(id);
            if (estacionamento == null) {
                throw new Exception("O id informado não pertence a nenhum usuário.");
            }
            ret.put("success", true);
            ret.put("message", "Estacionamento recuperado com sucesso.");
            ret.put("data", estacionamento);
        } catch (Exception exception) {
            ret.put("success", false);
            ret.put("data", null);
            ret.put("message", exception.getMessage());
        }
        return ret;
    }

    @PutMapping("/{id}")
    public Map<String, Object> updateEstacionamento(@PathVariable Long id, @RequestBody Map<String, ?> input) {
        Map<String, Object> ret = new HashMap<String, Object>();
        try {
            Estacionamento estacionamento = new Estacionamento();

            Object donoId = input.get("dono_id");
            Usuario dono = new Usuario();
            if (donoId != null) {
                dono = usuarioService.getUsuarioById(Long.parseLong(donoId.toString()));
                if (dono == null) {
                    throw new Exception("O id informado para o dono não pertence a nenhum usuário válido");
                }
            }

            String nome = null;
            Object getNome = input.get("nome");
            if (getNome == null) {
                throw new Exception("Informe um nome para o Estacionamento");
            } else {
                nome = getNome.toString();
            }

            String rua = null;
            Object getRua = input.get("rua");
            if (getRua != null) {
                rua = getRua.toString();
            }

            Long numero = null;
            Object getNumero = input.get("numero");
            if (getNumero != null) {
                numero = Long.parseLong(getNumero.toString());
            }

            String bairro = null;
            Object getBairro = input.get("bairro");
            if (getBairro != null) {
                bairro = getBairro.toString();
            }

            String complemento = null;
            Object getComplemento = input.get("complemento");
            if (getComplemento != null) {
                complemento = getComplemento.toString();
            }

            String cidade = null;
            Object getCidade = input.get("cidade");
            if (getCidade != null) {
                cidade = getCidade.toString();
            }

            String estado = null;
            Object getEstado = input.get("estado");
            if (getEstado != null) {
                estado = getEstado.toString();
            }

            String pais = null;
            Object getPais = input.get("pais");
            if (getPais != null) {
                pais = getBairro.toString();
            }

            estacionamento.setCampos(nome, dono, rua, numero, complemento, bairro, cidade, estado, pais);

            estacionamento = service.updateEstacionamento(id, estacionamento);
            ret.put("success", true);
            ret.put("message", estacionamento.getNome() + " atualizado com sucesso.");
            ret.put("data", estacionamento);
        } catch (Exception exception) {
            ret.put("success", false);
            ret.put("data", null);
            ret.put("message", exception.getMessage());
        }
        return ret;
    }

    @DeleteMapping("/{id}")
    public Map<String, Object> deleteEstacionamento(@PathVariable Long id) {
        Map<String, Object> ret = new HashMap<String, Object>();
        try {
            service.deleteEstacionamento(id);
            ret.put("success", true);
            ret.put("message", "Estacionamento " + id + " deletado com sucesso.");
            ret.put("data", null);
        } catch (Exception exception) {
            ret.put("success", false);
            ret.put("data", null);
            ret.put("message", exception.getMessage());
        }
        return ret;
    }
}
