package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Endereco {
    @Id
    @GeneratedValue
    private Long id;
    @Column(length = 8)
    private Integer cep;
    @Column(nullable = false)
    private String endereco;
    @Column(nullable = true)
    private String complemento;
    @Column(nullable = true)
    private Integer numero;
    @Column(nullable = false)
    private String cidade;
    @Column(nullable = false)
    private String bairro;
    @Column(nullable = false)
    private String estado;
    @Column(nullable = false)
    private String pais;
}
