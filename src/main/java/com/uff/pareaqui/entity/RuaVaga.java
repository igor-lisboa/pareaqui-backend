package com.uff.pareaqui.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
@Table(name = "rua_vagas")
public class RuaVaga {
    @Id
    @GeneratedValue
    private Long id;
    @OneToOne
    private Vaga vaga;
    @ManyToOne
    private UsuarioRuaFiscal fiscal;
    @Column(nullable = false)
    private Boolean flanelinha;
    @Column(nullable = true)
    private String rua;
    @Column(nullable = false)
    private Long numero;
    @Column(nullable = true)
    private String complemento;
    @Column(nullable = false)
    private String bairro;
    @Column(nullable = false)
    private String cidade;
    @Column(nullable = false)
    private String estado;
    @Column(nullable = false)
    private String pais;
}
