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

import com.grupo2.t4j.model.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public interface RepositorioSeriacao {
    
    boolean save(int idSeriacao, int idAnuncio, String dataSeriacao, List<Classificacao> 
            listaCandidaturasSeriadas) throws SQLException;

    boolean save(Seriacao seriacao) throws SQLException;

    Seriacao findById(int idSeriacao) throws SQLException;
    
    Seriacao findByAnuncio(int idAnuncio) throws SQLException;    
    
    ArrayList<Seriacao> getAll()throws SQLException;
    
    
}
