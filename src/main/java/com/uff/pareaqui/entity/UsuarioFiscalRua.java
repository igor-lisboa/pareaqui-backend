package com.uff.pareaqui.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class UsuarioFiscalRua extends Usuario {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private Usuario usuario;
    @Column(nullable = false)
    private Endereco endereco;
}
