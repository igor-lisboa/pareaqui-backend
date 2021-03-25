package com.uff.pareaqui.entity;

import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class VagaAcidente {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private Vaga vaga;
    @Column(nullable = false)
    private Usuario reportadorDoAcidente;
    @Temporal(TemporalType.TIMESTAMP)
    private DateTimeFormatter momento;

}
