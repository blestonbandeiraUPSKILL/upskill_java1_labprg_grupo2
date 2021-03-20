/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.domain;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author CAD
 */
public interface RegimentoStrategy {

    boolean seriar(int idAnuncio) throws SQLException;

    boolean seriar(int idAnuncio, List<Classificacao> classificacoes) throws SQLException;

    boolean seriar(int idAnuncio, List<Classificacao> classificacoes, List<String> colaboradores) throws SQLException;

    boolean atribuir(int idAnuncio);

    boolean atribuir(int idAnuncio, String dataInicioTarefa);

}
