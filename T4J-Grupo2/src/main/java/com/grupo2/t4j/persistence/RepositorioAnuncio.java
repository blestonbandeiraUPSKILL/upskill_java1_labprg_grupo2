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
import com.grupo2.t4j.exception.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
public interface RepositorioAnuncio {

     void save(String idAnuncio,Data dtInicioPublicitacao, Data dtFimPublicitacao, Data
             dtInicioCandidatura, Data dtFimCandidatura, Data dtInicioSeriacao,
               Data dtFimSeriacao) throws AnuncioDuplicadoException;

     boolean save(Anuncio anuncio);


    Anuncio findById(String idAnuncio);

    ArrayList<Anuncio> getAll();

}
