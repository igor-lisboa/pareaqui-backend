package com.uff.pareaqui.service;

import java.util.List;

import javax.transaction.Transactional;

import com.uff.pareaqui.entity.RuaVaga;
import com.uff.pareaqui.entity.Vaga;
import com.uff.pareaqui.repository.RuaVagaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RuaVagaService {
    @Autowired
    private RuaVagaRepository repository;

    @Autowired
    private VagaService vagaService;

    public RuaVaga saveRuaVaga(RuaVaga ruaVaga) {
        return repository.save(ruaVaga);
    }

    public RuaVaga getRuaVaga(Long id) {
        return repository.findById(id).orElse(null);
    }

    public List<RuaVaga> getRuaVagas() {
        return repository.findAll();
    }

    public RuaVaga saveRuaVagaCompleta(String rua, Long numero, String complemento, String bairro, String cidade,
            String estado, String pais, Long vagaTipoId, Long vagaTamanhoId, Float preco, String identificacao)
            throws Exception {
        Vaga vaga = vagaService.saveVagaCompleta(vagaTipoId, vagaTamanhoId, preco, identificacao);

        RuaVaga ruaVaga = new RuaVaga();
        ruaVaga.setCampos(vaga, rua, numero, complemento, bairro, cidade, estado, pais);
        return this.saveRuaVaga(ruaVaga);
    }

    @Transactional
    public RuaVaga updateRuaVagaCompleta(Long id, String rua, Long numero, String complemento, String bairro,
            String cidade, String estado, String pais, Long vagaTipoId, Long vagaTamanhoId, Float preco,
            String identificacao) throws Exception {
        RuaVaga ruaVaga = this.getRuaVaga(id);
        Vaga vaga = vagaService.updateVagaCompleta(ruaVaga.getVaga().getId(), vagaTipoId, vagaTamanhoId, preco,
                identificacao);
        ruaVaga.setCampos(vaga, rua, numero, complemento, bairro, cidade, estado, pais);
        return this.updateRuaVaga(id, ruaVaga);
    }

    @Transactional
    public RuaVaga updateRuaVaga(Long id, RuaVaga ruaVaga) {
        RuaVaga ruaVagaAtualiza = repository.getOne(id);
        ruaVagaAtualiza.setCampos(ruaVaga.getVaga(), ruaVaga.getRua(), ruaVaga.getNumero(), ruaVaga.getComplemento(),
                ruaVaga.getBairro(), ruaVaga.getCidade(), ruaVaga.getEstado(), ruaVaga.getPais());
        return this.saveRuaVaga(ruaVagaAtualiza);
    }

    @Transactional
    public void deleteRuaVaga(Long id) {
        RuaVaga ruaVaga = this.getRuaVaga(id);
        vagaService.deleteVaga(ruaVaga.getVaga().getId());
        repository.deleteById(id);
    }
}
