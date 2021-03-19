/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.persistence;

/**
 *
 * @author CAD
 */
import com.grupo2.t4j.domain.Atribuicao;
import com.grupo2.t4j.dto.AtribuicaoDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public interface RepositorioAtribuicao {

    boolean save(String nifOrganizacao, String refTarefa, int idAnuncio, int idCandidatura, String emailFreelancer,
                 double valorAceite, int numDiasAceite, String codigoAtribuicao, String dataInicioTarefa) throws SQLException;

    boolean save(AtribuicaoDTO atribuicao) throws SQLException;

    Atribuicao findAtribuicaoByAnuncio(int idAnuncio) throws SQLException;

    List<Atribuicao> getAllByOrganizacao(String nifOrganizacao) throws SQLException;
}
