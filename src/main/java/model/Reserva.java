package model;

import java.text.DateFormat;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Reserva {
    @Id
    @GeneratedValue
    private Long id;
    @Temporal(TemporalType.TIMESTAMP)
    private DateFormat momento;
    @OneToOne(mappedBy = "id", cascade = CascadeType.ALL)
    private Vaga vaga;
    private Boolean cancelada;
}
