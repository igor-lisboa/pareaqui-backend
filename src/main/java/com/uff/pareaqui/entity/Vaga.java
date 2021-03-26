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
@Table(name="vagas")
public class Vaga {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private Float preco;
    @ManyToOne
    private Estacionamento estacionamento;
    @ManyToOne
    private VagaTipo tipo;
    @ManyToOne
    private VagaTamanho tamanho;
    @Column(nullable = true)
    private String identificacao;
}
