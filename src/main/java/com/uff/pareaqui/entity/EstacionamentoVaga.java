package com.uff.pareaqui.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
public class EstacionamentoVaga implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    private Long id;
    @OneToOne
    private Vaga vaga;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Estacionamento estacionamento;

    public void setCampos(Vaga vaga, Estacionamento estacionamento) {
        this.setVaga(vaga);
        this.setEstacionamento(estacionamento);
    }

    public Estacionamento getEstacionamento() {
        return estacionamento;
    }

    public Long getId() {
        return id;
    }

    public Vaga getVaga() {
        return vaga;
    }

    public void setEstacionamento(Estacionamento estacionamento) {
        this.estacionamento = estacionamento;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setVaga(Vaga vaga) {
        this.vaga = vaga;
    }
}
