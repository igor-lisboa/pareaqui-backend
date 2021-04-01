package com.uff.pareaqui.service;

import java.util.List;
import java.util.Map;

import com.uff.pareaqui.entity.Vaga;
import com.uff.pareaqui.entity.VagaTamanho;
import com.uff.pareaqui.entity.VagaTipo;
import com.uff.pareaqui.repository.VagaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class VagaService {

    @Autowired
    private VagaRepository repository;

    @Autowired
    private VagaTipoService vagaTipoService;

    @Autowired
    private VagaTamanhoService vagaTamanhoService;

    @Autowired
    JdbcTemplate jdbcTemplate;

    public Vaga saveVaga(Vaga vaga) {
        return repository.save(vaga);
    }

    public List<Map<String, Object>> filtraVagas() {
        return jdbcTemplate.queryForList("SELECT * FROM vagas");
    }

    public Vaga getVaga(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Vaga saveVagaCompleta(Long vagaTipoId, Long vagaTamanhoId, Float preco, String identificacao)
            throws Exception {
        VagaTipo tipo = vagaTipoService.getVagaTipoById(vagaTipoId);
        if (tipo == null) {
            throw new Exception("Informe um tipo válido");
        }
        VagaTamanho tamanho = vagaTamanhoService.getVagaTamanhoById(vagaTamanhoId);
        if (tamanho == null) {
            throw new Exception("Informe um tamanho válido");
        }
        Vaga vaga = new Vaga();
        vaga.setCampos(tipo, tamanho, preco, identificacao);
        return this.saveVaga(vaga);
    }

    public Vaga updateVagaCompleta(Long id, Long vagaTipoId, Long vagaTamanhoId, Float preco, String identificacao)
            throws Exception {
        VagaTipo tipo = vagaTipoService.getVagaTipoById(vagaTipoId);
        if (tipo == null) {
            throw new Exception("Informe um tipo válido");
        }
        VagaTamanho tamanho = vagaTamanhoService.getVagaTamanhoById(vagaTamanhoId);
        if (tamanho == null) {
            throw new Exception("Informe um tamanho válido");
        }
        Vaga vaga = new Vaga();
        vaga.setCampos(tipo, tamanho, preco, identificacao);
        return this.updateVaga(id, vaga);
    }

    public Vaga updateVaga(Long id, Vaga vaga) {
        Vaga vagaAtualiza = repository.getOne(id);
        vagaAtualiza.setCampos(vaga.getTipo(), vaga.getTamanho(), vaga.getPreco(), vaga.getIdentificacao());
        return this.saveVaga(vagaAtualiza);
    }

    public void deleteVaga(Long id) {
        repository.deleteById(id);
    }
}
