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
    private Date entrada;
    @Temporal(TemporalType.TIMESTAMP)
    private Date saida;

    public Date getEntrada() {
        return entrada;
    }

    public Long getId() {
        return id;
    }

    public Date getSaida() {
        return saida;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Vaga getVaga() {
        return vaga;
    }

    public void setEntrada(Date entrada) {
        this.entrada = entrada;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setSaida(Date saida) {
        this.saida = saida;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setVaga(Vaga vaga) {
        this.vaga = vaga;
    }
}
