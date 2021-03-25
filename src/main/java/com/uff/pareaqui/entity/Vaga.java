package com.uff.pareaqui.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Vaga {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private Float preco;
    @OneToOne
    private Estacionamento estacionamento;
    @OneToOne
    private VagaTipo tipo;
    @OneToOne
    private VagaTamanho tamanho;
    @Column(nullable = true)
    private String identificacao;
}
