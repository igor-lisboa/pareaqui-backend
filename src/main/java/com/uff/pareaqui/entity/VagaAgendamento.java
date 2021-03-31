package com.uff.pareaqui.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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
@Table(name = "vaga_agendamentos")
public class VagaAgendamento {
    @Id
    @GeneratedValue
    private Long id;
    @OneToOne
    private Vaga vaga;
    @ManyToOne
    private Usuario usuario;
    @Temporal(TemporalType.TIMESTAMP)
    private Date momento;

    public void setCampos(Vaga vaga, Usuario usuario, Date momento) {
        this.setVaga(vaga);
        this.setUsuario(usuario);
        this.setMomento(momento);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setMomento(Date momento) {
        this.momento = momento;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setVaga(Vaga vaga) {
        this.vaga = vaga;
    }

    public Long getId() {
        return id;
    }

    public Date getMomento() {
        return momento;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Vaga getVaga() {
        return vaga;
    }
}
