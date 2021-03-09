package model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class DenunciaVaga {
    @Id
    @GeneratedValue
    private Long id;
    @OneToOne(mappedBy = "id", cascade = CascadeType.ALL)
    private Denuncia denuncia;
    @OneToOne(mappedBy = "id", cascade = CascadeType.ALL)
    private Vaga vaga;
}
