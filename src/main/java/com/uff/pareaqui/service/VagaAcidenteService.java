package com.uff.pareaqui.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.uff.pareaqui.entity.Vaga;
import com.uff.pareaqui.entity.VagaAcidente;
import com.uff.pareaqui.repository.VagaAcidenteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VagaAcidenteService {
    @Autowired
    private VagaAcidenteRepository repository;

    @Autowired
    private VagaService vagaService;

    public VagaAcidente saveVagaAcidente(VagaAcidente vagaAcidente) {
        return repository.save(vagaAcidente);
    }

    public VagaAcidente getVagaAcidente(Long id) {
        return repository.findById(id).orElse(null);
    }

    public VagaAcidente saveVagaAcidenteCompleta(Long vagaId, String descricao, String momentoString) throws Exception {
        Vaga vaga = vagaService.getVaga(vagaId);
        if (vaga == null) {
            throw new Exception("Informe uma vaga válida");
        }

        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date momento = formater.parse(momentoString);

        VagaAcidente vagaAcidente = new VagaAcidente();
        vagaAcidente.setCampos(vaga, descricao, momento);
        return this.saveVagaAcidente(vagaAcidente);
    }
}
