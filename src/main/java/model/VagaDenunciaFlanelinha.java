package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class VagaDenunciaFlanelinha {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private Vaga vaga;
}
