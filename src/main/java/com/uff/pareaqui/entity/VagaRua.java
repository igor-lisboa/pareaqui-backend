package com.uff.pareaqui.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class VagaRua extends Vaga {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private Vaga vaga;
    @Column(nullable = false)
    private Boolean flanelinha;
    @Column(nullable = false)
    private Endereco endereco;
}
