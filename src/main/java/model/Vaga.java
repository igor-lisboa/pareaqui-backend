package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Vaga {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private Float preco;
    @Column(nullable = false)
    private Estacionamento estacionamento;
    @Column(nullable = false)
    private VagaTipo tipo;
    @Column(nullable = false)
    private VagaTamanho tamanho;
    @Column(nullable = true)
    private String identificacao;
}
