package com.uff.pareaqui.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class VagaTipo {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String tipo;
    @Column(nullable = true)
    private String img;
}
