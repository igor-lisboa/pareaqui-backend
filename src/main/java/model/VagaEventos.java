package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class VagaEventos {
    @Id
    @GeneratedValue
    private Long id;
    private UsuarioCliente cliente;
    private String descricao;
}
