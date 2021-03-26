package com.uff.pareaqui.entity;

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
public class VagaTipo {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String tipo;
    @Column(nullable = true)
    private String img;
}
