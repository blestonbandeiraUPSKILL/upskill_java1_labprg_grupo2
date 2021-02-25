/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.model;

/**
 *
 * @author CAD
 */

import java.util.ArrayList;
import java.util.List;
public class SeriacaoManual {

    private List<ColocacaoSeriacao> listaSeriadaFreelancers;
    
    
    
    private String idAnuncio;
    
    private List<Candidatura> listaCandidaturas;
    
    private String ordemColocacao;
    
    private Data dataSeriacao;
    
    public SeriacaoManual(){
        listaSeriadaFreelancers = new ArrayList<>();
    }
    
    public SeriacaoManual(String idAnuncio, List<Candidatura> 
            listaCandidaturas, String ordemColocacao, Data dataSeriacao){
        for(int i = 0; i < listaCandidaturas.size();i++){
            ColocacaoSeriacao colocacao = new ColocacaoSeriacao(idAnuncio, listaCandidaturas.get(i).getIdCandidatura(),
            listaCandidaturas.get(i).getEmailFreelancer(), Integer.parseInt(ordemColocacao), dataSeriacao);
            listaSeriadaFreelancers.add(colocacao);
        }
    }
    
    //public 
    
}
