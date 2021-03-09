package model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Estacionamento {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String nome;
    @OneToOne(mappedBy = "id", cascade = CascadeType.ALL)
    private Estacionamento estacionamento;
    @OneToOne(mappedBy = "id", cascade = CascadeType.ALL)
    private UsuarioDono dono;
}
