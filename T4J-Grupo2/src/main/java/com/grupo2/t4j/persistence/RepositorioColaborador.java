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

import com.grupo2.t4j.domain.Colaborador;
import com.grupo2.t4j.domain.Password;
import com.grupo2.t4j.exception.ColaboradorDuplicadoException;

import java.sql.SQLException;
import java.util.ArrayList;

public interface RepositorioColaborador {


    void save(String email, String nome, String passowrd, String funcao, String telefone) throws ColaboradorDuplicadoException;

    boolean save(Colaborador colaborador) throws SQLException;

    Colaborador findByEmail(String emailCol) throws SQLException;

    ArrayList<Colaborador> getAll(String nifOrganizacacao) throws SQLException;
    
    ArrayList<String> getAllEmailsAlfByOrganizacao(String nifOrganizacao) throws SQLException;
        
    String getNifOrganizacao(String email) throws SQLException;

    Password findPassword(String email) throws SQLException;

}
