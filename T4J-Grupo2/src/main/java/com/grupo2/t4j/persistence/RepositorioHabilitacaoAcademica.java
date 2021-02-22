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

import com.grupo2.t4j.model.HabilitacaoAcademica;

import java.sql.SQLException;
import java.util.List;

public interface RepositorioHabilitacaoAcademica {
    
    void save(String idHabilitacao, String grau, String designacaoCurso,
           String nomeInstituicao, double mediaCurso) throws SQLException;

    boolean save(HabilitacaoAcademica habilitacaoAcademica) throws SQLException;

    List<HabilitacaoAcademica> getAll() throws SQLException;

    HabilitacaoAcademica findById(String idHabilitacao) throws SQLException;
}
