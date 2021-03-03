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
public interface RepositorioClassificacao {

    boolean save(int idClassificacao, int idAnuncio, int idCandidatura, int posicao) throws SQLException;

    boolean save(Classificacao classificacao) throws SQLException;

    Classificacao findById(int idClassificacao) throws SQLException;
    
    Classificacao findByAnuncio(int idAnuncio) throws SQLException;
    
    Classificacao findByCandidatura(int idCandidatura) throws SQLException;
    
    ArrayList<Classificacao> getAllByAnuncio(int idAnuncio)throws SQLException;
}
