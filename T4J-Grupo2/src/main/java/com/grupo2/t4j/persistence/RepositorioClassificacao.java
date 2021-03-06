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

import com.grupo2.t4j.domain.Classificacao;

import java.sql.SQLException;
import java.util.List;
public interface RepositorioClassificacao {

    boolean save(int posicao, int idSeriacao, int idCandidatura) throws SQLException;

    boolean save(Classificacao classificacao) throws SQLException;

    Classificacao findById(int idClassificacao) throws SQLException;
            
    Classificacao findByCandidatura(int idCandidatura) throws SQLException;
    
    Classificacao findBySeriacao(int idSeriacao) throws SQLException;
    
    List<Classificacao> getAllBySeriacao(int idSeriacao)throws SQLException;
    
    List<Classificacao> ordenarByIdCandidatura(List<Classificacao> classificacoes) throws SQLException;
}
