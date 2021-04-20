package com.uff.pareaqui.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuarios", uniqueConstraints = @UniqueConstraint(columnNames = { "email" }))
public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String senha;
    @Column(nullable = false)
    private Boolean adm = false;

    public Long getId() {
        return id;
    }

    public Boolean getAdm() {
        return adm;
    }

    public void setAdm(Boolean adm) {
        this.adm = adm;
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
