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

    public boolean save(String nifOrganizacao, String refTarefa, int idAnuncio, String emailFreelancer, String dataInicioTarefa, int idCandidatura) throws SQLException;

    boolean save(Atribuicao atribuicao) throws SQLException;

    AtribuicaoDTO findAtribuicaoByTarefa(String refTarefa) throws SQLException;

    List<AtribuicaoDTO> getAllByOrganizacao(String nifOrganizacao) throws SQLException;
}
