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
@Table(name = "vaga_tipos")
public class VagaTipo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String tipo;
    @Column(nullable = false)
    private String img;

    public void setId(Long id) {
        this.id = id;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Long getId() {
        return id;
    }

    public String getImg() {
        return img;
    }

    public String getTipo() {
        return tipo;
    }
}
