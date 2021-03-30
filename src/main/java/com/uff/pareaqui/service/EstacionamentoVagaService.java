package com.uff.pareaqui.service;

import java.util.Collection;

import javax.transaction.Transactional;

import com.uff.pareaqui.entity.Estacionamento;
import com.uff.pareaqui.entity.EstacionamentoVaga;
import com.uff.pareaqui.entity.Vaga;
import com.uff.pareaqui.repository.EstacionamentoVagaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstacionamentoVagaService {
    @Autowired
    private EstacionamentoVagaRepository repository;

    @Autowired
    private VagaService vagaService;

    @Autowired
    private EstacionamentoService estacionamentoService;

    public EstacionamentoVaga saveEstacionamentoVaga(EstacionamentoVaga estacionamentoVaga) {
        return repository.save(estacionamentoVaga);
    }

    public EstacionamentoVaga getEstacionamentoVaga(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Collection<EstacionamentoVaga> getEstacionamentoVagasByEstacionamentoId(Long estacionamentoId) {
        return repository.findByEstacionamentoId(estacionamentoId);
    }

    public EstacionamentoVaga saveEstacionamentoVagaCompleta(Long estacionamentoId, Long vagaTipoId, Long vagaTamanhoId,
            Float preco, String identificacao) throws Exception {
        Estacionamento estacionamento = estacionamentoService.getEstacionamentoById(estacionamentoId);
        if (estacionamento == null) {
            throw new Exception("Informe um estacionamento válido");
        }
        Vaga vaga = vagaService.saveVagaCompleta(vagaTipoId, vagaTamanhoId, preco, identificacao);

        EstacionamentoVaga estacionamentoVaga = new EstacionamentoVaga();
        estacionamentoVaga.setCampos(vaga, estacionamento);
        return this.saveEstacionamentoVaga(estacionamentoVaga);
    }

    @Transactional
    public EstacionamentoVaga updateEstacionamentoVagaCompleta(Long id, Long estacionamentoId, Long vagaTipoId,
            Long vagaTamanhoId, Float preco, String identificacao) throws Exception {
        Estacionamento estacionamento = estacionamentoService.getEstacionamentoById(estacionamentoId);
        if (estacionamento == null) {
            throw new Exception("Informe um estacionamento válido");
        }
        EstacionamentoVaga estacionamentoVaga = this.getEstacionamentoVaga(id);
        Vaga vaga = vagaService.updateVagaCompleta(estacionamentoVaga.getVaga().getId(), vagaTipoId, vagaTamanhoId,
                preco, identificacao);

        estacionamentoVaga.setCampos(vaga, estacionamento);
        return this.updateEstacionamentoVaga(id, estacionamentoVaga);
    }

    @Transactional
    public EstacionamentoVaga updateEstacionamentoVaga(Long id, EstacionamentoVaga estacionamentoVaga) {
        EstacionamentoVaga estacionamentoVagaAtualiza = repository.getOne(id);
        estacionamentoVagaAtualiza.setCampos(estacionamentoVaga.getVaga(), estacionamentoVaga.getEstacionamento());
        return this.saveEstacionamentoVaga(estacionamentoVagaAtualiza);
    }

    @Transactional
    public void deleteEstacionamentoVaga(Long id) {
        EstacionamentoVaga estacionamentoVaga = this.getEstacionamentoVaga(id);
        vagaService.deleteVaga(estacionamentoVaga.getVaga().getId());
        repository.deleteById(id);
    }
}
