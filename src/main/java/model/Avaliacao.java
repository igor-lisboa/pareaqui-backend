package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Avaliacao {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = true)
    private String descricao;
    @Column(nullable = false)
    private Integer nota;
}
