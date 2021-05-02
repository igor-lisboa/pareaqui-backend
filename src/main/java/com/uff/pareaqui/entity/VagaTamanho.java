package com.uff.pareaqui.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "vaga_tamanhos")
public class VagaTamanho implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String tamanho;

    public Long getId() {
        return id;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }
}
