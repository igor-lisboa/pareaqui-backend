package com.uff.pareaqui.entity;

import javax.persistence.Column;
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
@Table(name = "vagas")
public class Vaga {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private Float preco;
    @ManyToOne
    private VagaTipo tipo;
    @ManyToOne
    private VagaTamanho tamanho;
    @Column(nullable = true)
    private String identificacao;

    public void setCampos(VagaTipo tipo, VagaTamanho tamanho, Float preco, String identificacao) {
        this.setIdentificacao(identificacao);
        this.setPreco(preco);
        this.setTamanho(tamanho);
        this.setTipo(tipo);
    }

    public Long getId() {
        return id;
    }

    public String getIdentificacao() {
        return identificacao;
    }

    public Float getPreco() {
        return preco;
    }

    public VagaTamanho getTamanho() {
        return tamanho;
    }

    public VagaTipo getTipo() {
        return tipo;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setIdentificacao(String identificacao) {
        this.identificacao = identificacao;
    }

    public void setPreco(Float preco) {
        this.preco = preco;
    }

    public void setTamanho(VagaTamanho tamanho) {
        this.tamanho = tamanho;
    }

    public void setTipo(VagaTipo tipo) {
        this.tipo = tipo;
    }
}
