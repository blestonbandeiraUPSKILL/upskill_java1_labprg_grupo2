/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.controller;

import com.grupo2.t4j.model.Data;
import com.grupo2.t4j.model.DesignacaoSeriacao;
import java.time.LocalDate;

/**
 *
 * @author acris
 */
public class PublicarTarefaController {

    

    public boolean publicarTarefa(String referencia, String nifOrganizacao, 
            LocalDate dtInicioPublicitacao, LocalDate dtFimPublicitacao, 
            LocalDate dtInicioCandidaturas, LocalDate dtFimCandidaturas, 
            LocalDate dtInicioSeriacao, LocalDate dtFimSeriacao, 
            String regrasGerais, DesignacaoSeriacao designacaoSeriacao) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * Transforma um objeto LocalDate num Objeto Data
     * @param localDate
     * @return data
     */
    public Data data(LocalDate localDate){
        Data data = new Data(localDate);
        return data;
    }
    
}
