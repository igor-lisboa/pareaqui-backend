package com.uff.pareaqui.service;

import java.util.Collection;

import com.uff.pareaqui.entity.Usuario;
import com.uff.pareaqui.entity.UsuarioRuaFiscal;
import com.uff.pareaqui.repository.UsuarioRuaFiscalRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioRuaFiscalService {
    @Autowired
    private UsuarioRuaFiscalRepository repository;

    @Autowired
    private UsuarioService usuarioService;

    public UsuarioRuaFiscal saveUsuarioRuaFiscal(UsuarioRuaFiscal usuarioRuaFiscal) {
        return repository.save(usuarioRuaFiscal);
    }

    public UsuarioRuaFiscal saveUsuarioRuaFiscalCompleto(Long usuarioId) throws Exception {
        UsuarioRuaFiscal usuarioRuaFiscal = new UsuarioRuaFiscal();

        Usuario usuario = usuarioService.getUsuarioById(usuarioId);
        if (usuario == null) {
            throw new Exception("O usuário escolhido para ser promovido a fiscal de ruas não existe");
        }
        usuarioRuaFiscal.setUsuario(usuario);

        UsuarioRuaFiscal usuarioFiscal = this.findByUsuarioId(usuario.getId());
        if (usuarioFiscal != null) {
            throw new Exception("O usuário escolhido para ser promovido a fiscal já foi promovido");
        }

        return this.saveUsuarioRuaFiscal(usuarioRuaFiscal);
    }

    public UsuarioRuaFiscal findByUsuarioId(Long usuarioId) {
        Collection<UsuarioRuaFiscal> usuarioFiscais = repository.findByUsuarioId(usuarioId);
        if (usuarioFiscais.size() > 0) {
            return (UsuarioRuaFiscal) usuarioFiscais.toArray()[0];
        }
        return null;
    }

    public void deleteUsuarioRuaFiscal(Long id) {
        repository.deleteById(id);
    }
}
