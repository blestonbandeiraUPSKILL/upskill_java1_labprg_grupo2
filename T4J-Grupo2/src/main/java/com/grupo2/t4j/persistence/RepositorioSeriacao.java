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

import com.grupo2.t4j.domain.*;
import java.sql.SQLException;
import java.util.List;
public interface RepositorioSeriacao {
    
    boolean save(int idAnuncio) throws SQLException;

    boolean save(ProcessoSeriacao seriacao) throws SQLException;

    ProcessoSeriacao findById(int idSeriacao) throws SQLException;
    
    ProcessoSeriacao findByAnuncio(int idAnuncio) throws SQLException;    
    
    List<ProcessoSeriacao> getAll()throws SQLException;
    
    
}
