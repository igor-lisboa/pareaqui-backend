package com.uff.pareaqui.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.uff.pareaqui.entity.Vaga;
import com.uff.pareaqui.entity.VagaTamanho;
import com.uff.pareaqui.entity.VagaTipo;
import com.uff.pareaqui.repository.VagaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

@Service
@RestController
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

    public ArrayList<HashMap<String, Object>> filtraVagas(String menorPreco, String maiorPreco, String tiposEscolhidos,
            String tamanhosEscolhidos, boolean estacionamento, boolean semAcidentes, boolean semFlanelinha,
            boolean orderPreco, boolean orderPrecoAsc) throws SQLException {

        ArrayList<String> bind = new ArrayList<String>();

        String baseFrom = "SELECT v.id AS \"vaga_id\",v.identificacao AS \"vaga_identificacao\",v.preco AS \"vaga_preco\",vtipo.id AS \"vaga_tipo_id\" ,vtipo.tipo AS \"vaga_tipo\",vtipo.img AS \"tipo_img\",vtamanho.id AS \"vaga_tamanho_id\",vtamanho.tamanho AS \"vaga_tamanho\", vagas_todas.estacionamento AS \"estacionamento\",vagas_todas.rua AS \"rua\",vagas_todas.numero AS \"numero\",vagas_todas.cidade AS \"cidade\",vagas_todas.complemento AS \"complemento\",vagas_todas.bairro AS \"bairro\",vagas_todas.estado AS \"estado\",vagas_todas.pais AS \"pais\" FROM vagas v INNER JOIN ( SELECT e.id AS \"estacionamento\",e.rua AS \"rua\",e.complemento AS \"complemento\",e.cidade AS \"cidade\",e.numero AS \"numero\",e.bairro AS \"bairro\",e.estado AS \"estado\",e.pais AS \"pais\",ev.vaga_id AS \"vaga_id\" FROM estacionamento_vagas ev INNER JOIN estacionamentos e ON (ev.estacionamento_id=e.id) UNION SELECT 0 AS \"estacionamento\",rv.rua AS \"rua\",rv.complemento AS \"complemento\",rv.numero AS \"numero\",rv.cidade AS \"cidade\",rv.bairro AS \"bairro\",rv.estado AS \"estado\",rv.pais AS \"pais\",rv.vaga_id AS \"vaga_id\" FROM rua_vagas rv ) vagas_todas ON (v.id=vagas_todas.vaga_id) INNER JOIN vaga_tipos vtipo ON (v.tipo_id=vtipo.id) INNER JOIN vaga_tamanhos vtamanho ON (v.tamanho_id=vtamanho.id)";

        String where = "";

        if (estacionamento) {
            where += (where == "" ? " WHERE " : " AND ") + "(consulta_vagas.estacionamento <> 0)";
        }

        if (semAcidentes) {
            where += (where == "" ? " WHERE " : " AND ")
                    + "(consulta_vagas.vaga_id NOT IN (SELECT DISTINCT vaga_id FROM vaga_acidentes) AND consulta_vagas.estacionamento NOT IN (SELECT DISTINCT estacionamento_vagas.estacionamento_id FROM vaga_acidentes INNER JOIN vagas ON (vaga_acidentes.vaga_id=vagas.id) INNER JOIN estacionamento_vagas ON (estacionamento_vagas.vaga_id=vagas.id)) )";
        }

        if (menorPreco != null && menorPreco != "null" && maiorPreco != null && maiorPreco != "null") {
            where += (where == "" ? " WHERE " : " AND ")
                    + "(? <= consulta_vagas.vaga_preco AND consulta_vagas.vaga_preco <= ?)";
            bind.add(menorPreco);
            bind.add(maiorPreco);
        }

        if (tiposEscolhidos != null && tiposEscolhidos != "null") {
            where += (where == "" ? " WHERE " : " AND ") + "(consulta_vagas.vaga_tipo_id IN (?))";
            bind.add(tiposEscolhidos);
        }

        if (tamanhosEscolhidos != null && tamanhosEscolhidos != "null") {
            where += (where == "" ? " WHERE " : " AND ") + "(consulta_vagas.vaga_tamanho_id IN (?))";
            bind.add(tamanhosEscolhidos);
        }

        if (semFlanelinha) {
            where += (where == "" ? " WHERE " : " AND ")
                    + "(CONCAT(consulta_vagas.pais,consulta_vagas.estado,consulta_vagas.cidade,consulta_vagas.bairro,consulta_vagas.rua) NOT LIKE (SELECT CONCAT('%',";
            String dbName = jdbcTemplate.getDataSource().getConnection().getMetaData().getDatabaseProductName();
            if (dbName == "PostgreSQL") {
                where += "string_agg(DISTINCT CONCAT(pais,estado,cidade,bairro,rua),',')";
            } else if (dbName == "MySQL") {
                where += "GROUP_CONCAT(DISTINCT CONCAT(pais,estado,cidade,bairro,rua))";
            }
            where += ",'%') FROM flanelinha_denuncias))";
        }

        String order = "";
        if (orderPreco) {
            order += (order == "" ? " ORDER BY " : " AND ") + " consulta_vagas.vaga_preco ";
            if (!orderPrecoAsc) {
                order += " DESC ";
            }
        }

        String sql = "SELECT * FROM (" + baseFrom + ") consulta_vagas" + where + order;

        // inicia comando
        PreparedStatement comando = jdbcTemplate.getDataSource().getConnection().prepareStatement(sql);
        // executa bind
        Integer contador = 1;
        for (String valor : bind) {
            // insere valor no Statement
            comando.setString(contador, valor);
            // incrementa contador
            contador += 1;
        }
        ResultSet rs = comando.executeQuery();

        ResultSetMetaData md = rs.getMetaData();
        int columns = md.getColumnCount();
        ArrayList<HashMap<String, Object>> list = new ArrayList<>();
        while (rs.next()) {
            HashMap<String, Object> row = new HashMap<>(columns);
            for (int i = 1; i <= columns; ++i) {
                row.put(md.getColumnName(i), rs.getObject(i));
            }
            list.add(row);
        }

        // fechando conexao
        comando.close();

        return list;
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
