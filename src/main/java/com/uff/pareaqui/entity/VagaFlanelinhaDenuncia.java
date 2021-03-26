package com.uff.pareaqui.entity;

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
@Table(name="vaga_flanelinha_denuncias")
public class VagaFlanelinhaDenuncia {
    @Id
    @GeneratedValue
    private Long id;
    @OneToOne
    private RuaVaga vaga;
}
