package com.uff.pareaqui.entity;

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
@Table(name = "usuario_estacionamento_funcionarios")
public class UsuarioEstacionamentoFuncionario {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private Usuario usuario;
    @ManyToOne
    private Estacionamento estacionamento;

    public void setEstacionamento(Estacionamento estacionamento) {
        this.estacionamento = estacionamento;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Estacionamento getEstacionamento() {
        return estacionamento;
    }

    public Long getId() {
        return id;
    }

    public Usuario getUsuario() {
        return usuario;
    }
}
