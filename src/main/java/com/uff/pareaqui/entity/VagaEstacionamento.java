package com.uff.pareaqui.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class VagaEstacionamento extends Vaga {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private Vaga vaga;
    @Column(nullable = false)
    private Estacionamento estacionamento;
}
