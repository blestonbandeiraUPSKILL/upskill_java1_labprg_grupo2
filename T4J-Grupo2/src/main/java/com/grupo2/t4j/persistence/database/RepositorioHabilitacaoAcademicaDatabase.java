/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.persistence.database;

/**
 *
 * @author CAD
 */

import com.grupo2.t4j.exception.HabilitacaoAcademicaDuplicadaException;
import com.grupo2.t4j.model.*;
import com.grupo2.t4j.persistence.RepositorioHabilitacaoAcademica;
import com.grupo2.t4j.utils.DBConnectionHandler;
import java.sql.SQLException;
import java.util.ArrayList;

public class RepositorioHabilitacaoAcademicaDatabase implements RepositorioHabilitacaoAcademica{
    
    /**
     * Define uma instância estática do Repositório em que estão
     * registados todos os Freelancers
     */
    private static RepositorioHabilitacaoAcademicaDatabase repositorioHabilitacaoAcademicaDatabase;
    
    String jdbcUrl = "jdbc:oracle:thin:@vsrvbd1.dei.isep.ipp.pt:1521/pdborcl";
    String username = "UPSKILL_BD_TURMA1_01";
    String password = "qwerty";
    
    /**
     * Inicializa o Repositório de Freelancers
     */
    RepositorioHabilitacaoAcademicaDatabase(){    }

    /**
     * Devolve uma instância estática do Repositório de Utilizadores
     *
     * @return RepositorioUtilizadorDatabase
     */
    public static RepositorioHabilitacaoAcademicaDatabase getInstance(){
        if(repositorioHabilitacaoAcademicaDatabase == null) {
            repositorioHabilitacaoAcademicaDatabase = new RepositorioHabilitacaoAcademicaDatabase();
        }
        return repositorioHabilitacaoAcademicaDatabase;
    }


    @Override
    public void save(String idHabilitacao, String grau, String designacaoCurso,
           String nomeInstituicao, double mediaCurso) throws HabilitacaoAcademicaDuplicadaException,
            SQLException{

    }

    @Override
    public boolean save(HabilitacaoAcademica habilitacaoAcademica) throws HabilitacaoAcademicaDuplicadaException,
            SQLException {
        return false;
    }

    @Override
    public HabilitacaoAcademica findById(String idHabilitacao) throws SQLException{
        return null;
    }

    @Override
    public ArrayList<HabilitacaoAcademica> getAll() throws SQLException {
        return null;
    }
}
