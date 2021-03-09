package model;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class UsuarioCliente extends Usuario {
    @Id
    @GeneratedValue
    private Long id;
    @ElementCollection
    private Estacionamento[] estacionamentosFavoritos;

}
