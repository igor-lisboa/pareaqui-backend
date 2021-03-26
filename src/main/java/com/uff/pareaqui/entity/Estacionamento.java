package com.uff.pareaqui.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="estacionamentos")
public class Estacionamento {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String nome;
    @ManyToOne
    private Usuario dono;
    @ManyToOne
    private Endereco endereco;
}
