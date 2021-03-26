package com.uff.pareaqui.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="rua_vagas")
public class RuaVaga {
    @Id
    @GeneratedValue
    private Long id;
    @OneToOne
    private Vaga vaga;
    @Column(nullable = false)
    private Boolean flanelinha;
    @OneToOne
    private Endereco endereco;
}
