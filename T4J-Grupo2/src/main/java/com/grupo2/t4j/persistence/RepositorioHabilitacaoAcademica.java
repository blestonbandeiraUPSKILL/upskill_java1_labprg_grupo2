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

import com.grupo2.t4j.exception.HabilitacaoAcademicaDuplicadaException;
import com.grupo2.t4j.domain.HabilitacaoAcademica;

import java.sql.SQLException;
import java.util.ArrayList;

public interface RepositorioHabilitacaoAcademica {
    
    boolean save(String grau, String designacaoCurso,
           String nomeInstituicao, double mediaCurso, String emailFreelancer) throws HabilitacaoAcademicaDuplicadaException, SQLException;

    boolean save(HabilitacaoAcademica habilitacaoAcademica, String emailFreelancer) throws HabilitacaoAcademicaDuplicadaException, SQLException;

    HabilitacaoAcademica findByGrauDesigInst(String grau, String designacaoCurso,
                                             String nomeInstituicao, String emailFreelancer) throws SQLException;

    HabilitacaoAcademica findById(int idHabilitacao) throws SQLException;
    
    ArrayList<HabilitacaoAcademica> getAll(String emailFreelancer) throws SQLException;

}
