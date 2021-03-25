package com.uff.pareaqui.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UsuarioEstacionamentoDono {
    @Id
    @GeneratedValue
    private Long id;
    @OneToOne
    private Usuario usuario;
    @OneToOne
    private Estacionamento estacionamento;
}
