package model;

import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class VagaOcupacao {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private Vaga vaga;
    @Temporal(TemporalType.TIMESTAMP)
    private DateTimeFormatter entrada;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private DateTimeFormatter saida;
}
