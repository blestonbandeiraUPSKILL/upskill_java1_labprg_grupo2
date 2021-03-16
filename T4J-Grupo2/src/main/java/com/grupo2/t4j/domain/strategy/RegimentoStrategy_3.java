/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.domain.strategy;

import com.grupo2.t4j.domain.RegimentoStrategy;

/**
 *
 * @author CAD
 */
public class RegimentoStrategy_3 implements RegimentoStrategy{
    
    @Override
    public boolean seriar(int idAnuncio){
        return false;
    }
    
    @Override
    public boolean atribuir(int idAnuncio){
        return false;
    }
}
