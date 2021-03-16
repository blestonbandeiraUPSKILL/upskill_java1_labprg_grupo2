/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.domain;

import java.sql.SQLException;

/**
 *
 * @author CAD
 */
public interface RegimentoStrategy {

    boolean seriar(int idAnuncio) throws SQLException;

    boolean atribuir(int idAnuncio);

}
