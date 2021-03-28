package com.uff.pareaqui.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "vaga_ocupacoes")
public class VagaOcupacao {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private Vaga vaga;
    @ManyToOne
    private Usuario usuario;
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date entrada;
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date saida;
}
