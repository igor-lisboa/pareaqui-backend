package com.uff.pareaqui.entity;

import java.util.Date;

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
@Table(name = "vaga_acidentes")
public class VagaAcidente {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private Vaga vaga;
    private String descricao;
    @Temporal(TemporalType.TIMESTAMP)
    private Date momento;

    public void setCampos(Vaga vaga, String descricao, Date momento) {
        this.setVaga(vaga);
        this.setDescricao(descricao);
        this.setMomento(momento);
    }

    public String getDescricao() {
        return descricao;
    }

    public Long getId() {
        return id;
    }

    public Date getMomento() {
        return momento;
    }

    public Vaga getVaga() {
        return vaga;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setMomento(Date momento) {
        this.momento = momento;
    }

    public void setVaga(Vaga vaga) {
        this.vaga = vaga;
    }
}
