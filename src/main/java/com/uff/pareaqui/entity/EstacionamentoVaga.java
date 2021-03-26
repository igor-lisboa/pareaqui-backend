package com.uff.pareaqui.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "estacionamento_vagas")
public class EstacionamentoVaga {
    @Id
    @GeneratedValue
    private Long id;
    @OneToOne
    private Vaga vaga;
    @OneToOne
    private Estacionamento estacionamento;
}
