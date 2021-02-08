/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *
 * @author acris
 */
public class CompetenciaTecnicaTest {
    
    public CompetenciaTecnicaTest() {
    }
    
   @Test
    public void testCriarCompetenciaTecnica() {
        
        CompetenciaTecnica ct1= new CompetenciaTecnica("123", "CompetenciaTecnica1", "CompetenciaTecnicaDescDetalhada");
        
        assertEquals("123", ct1.getCodigo());
        assertEquals("CompetenciaTecnica1", ct1.getDescricaoBreve());
        assertEquals("CompetenciaTecnicaDescDetalhada", ct1.getDescricaoDetalhada());
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testCriarCompetenciaTecnicaSemDescBreve() {
        
        CompetenciaTecnica ct1= new CompetenciaTecnica("1234", "", "CompetenciaTecnicaDescDetalhada");
             
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testCriarCompetenciaTecnicaSemDescDetalhada() {
        
        CompetenciaTecnica ct1= new CompetenciaTecnica("1234", "CompetenciaTecnica1", "");
             
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testCriarCompetenciaTecnicaDescDetalhadaIncompleta() {
        
        CompetenciaTecnica ct1= new CompetenciaTecnica("1234", "CompetenciaTecnica1", "Comp1");
             
    }
    
    
    @Test (expected = IllegalArgumentException.class)
    public void testCriarAreaActividadeDescBreveIncompleta() {
        
        CompetenciaTecnica ct1= new CompetenciaTecnica("1234", "Comp", "CompetenciaTecnicaDescDetalhada");
             
    }    
}
