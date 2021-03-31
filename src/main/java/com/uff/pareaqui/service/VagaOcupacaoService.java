package com.uff.pareaqui.service;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import com.uff.pareaqui.entity.VagaOcupacao;
import com.uff.pareaqui.repository.VagaOcupacaoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VagaOcupacaoService {
    @Autowired
    private VagaOcupacaoRepository repository;

    @Autowired
    private VagaService vagaService;

    @Autowired
    private UsuarioService usuarioService;

    public VagaOcupacao saveVagaOcupacao(VagaOcupacao vagaOcupacao) {
        return repository.save(vagaOcupacao);
    }

    public VagaOcupacao getVagaOcupada(Long vagaId) {
        Collection<VagaOcupacao> ocupacoes = repository.findByVagaIdAndSaidaIsNull(vagaId);
        if (ocupacoes.size() == 1) {
            return (VagaOcupacao) ocupacoes.toArray()[0];
        } else {
            return null;
        }
    }

    public Collection<VagaOcupacao> historicoOcupacoesUsuario(Long usuarioId) {
        return repository.findByUsuarioId(usuarioId);
    }

    public VagaOcupacao getVagaOcupacao(Long id) {
        return repository.findById(id).orElse(null);
    }

    public VagaOcupacao registraEntrada(Long vagaId, Long usuarioId, String entradaString) throws Exception {
        VagaOcupacao vagaOcupacao = new VagaOcupacao();
        vagaOcupacao.setVaga(vagaService.getVaga(vagaId));
        vagaOcupacao.setUsuario(usuarioService.getUsuarioById(usuarioId));
        vagaOcupacao.setEntrada((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).parse(entradaString));
        if (this.getVagaOcupada(vagaId) != null) {
            throw new Exception("Essa vaga já está ocupada");
        }
        return this.saveVagaOcupacao(vagaOcupacao);
    }

    public VagaOcupacao registraSaida(Long vagaId, String saidaString) throws Exception {
        VagaOcupacao vagaOcupacao = this.getVagaOcupada(vagaId);
        if (vagaOcupacao == null) {
            throw new Exception(
                    "Você não pode registrar a saida dessa ocupação pois a vaga não está ocupada ou não existe");
        }
        Date saida = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).parse(saidaString);
        if (saida.before(vagaOcupacao.getEntrada()) || saida.equals(vagaOcupacao.getEntrada())) {
            throw new Exception("A saída tem que ser depois da entrada | [entrada] => " + vagaOcupacao.getEntrada()
                    + " | [saida desejada] => " + saida);
        }
        vagaOcupacao.setSaida(saida);
        return this.saveVagaOcupacao(vagaOcupacao);
    }
}
