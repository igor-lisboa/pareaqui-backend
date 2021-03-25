package com.uff.pareaqui.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class UsuarioEstacionamentoFuncionario extends Usuario {
    @Id
    @GeneratedValue
    private Long id;
    @OneToOne
    private Usuario usuario;
    @OneToOne
    private Estacionamento estacionamento;
}
