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

import java.sql.SQLException;
import java.util.ArrayList;
public interface RepositorioColaboradorSeriacao {

    boolean update(String emailColaborador, int idSeriacao) throws SQLException;
    
    boolean findByEmailId(String emaiColaborador, int idSeriacao) throws SQLException;
    
    ArrayList<String> getAllColaboresBySeriacao(int idSeriacao) throws SQLException;
    
    ArrayList getAllIdsSeriacaoByColaborador(String emailColaborador) throws SQLException;
    
}
