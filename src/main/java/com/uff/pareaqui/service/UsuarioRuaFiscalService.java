package com.uff.pareaqui.service;

import java.util.Collection;

import com.uff.pareaqui.entity.UsuarioRuaFiscal;
import com.uff.pareaqui.repository.UsuarioRuaFiscalRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioRuaFiscalService {
    @Autowired
    private UsuarioRuaFiscalRepository repository;

    public UsuarioRuaFiscal saveUsuarioRuaFiscal(UsuarioRuaFiscal usuarioRuaFiscal) {
        return repository.save(usuarioRuaFiscal);
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
