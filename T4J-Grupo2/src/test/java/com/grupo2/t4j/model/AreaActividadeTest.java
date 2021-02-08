/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.model;

import com.grupo2.t4j.exception.AreaActividadeDuplicadaException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *
 * @author acris
 */
public class AreaActividadeTest {
    
    public AreaActividadeTest() {
        
    }
    @Test
    public void testCriarAreaActividade() {
        
        AreaActividade a1= new AreaActividade("123", "AreaActividade1", "AreaActividadeDescDetalhada");
        
        assertEquals("123", a1.getCodigo());
        assertEquals("AreaActividade1", a1.getDescBreve());
        assertEquals("AreaActividadeDescDetalhada", a1.getDescDetalhada());
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testCriarAreaActividadeSemDescBreve() {
        
        AreaActividade a1= new AreaActividade("1234", "", "AreaActividadeDescDetalhada");
             
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testCriarAreaActividadeSemDescDetalhada() {
        
        AreaActividade a1= new AreaActividade("1234", "AreaActividade1", "");
             
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testCriarAreaActividadeDescDetalhadaIncompleta() {
        
        AreaActividade a1= new AreaActividade("1234", "AreaActividade1", "Area1");
             
    }
    
    
    @Test (expected = IllegalArgumentException.class)
    public void testCriarAreaActividadeDescBreveIncompleta() {
        
        AreaActividade a1= new AreaActividade("1234", "Area", "AreaActividadeDescDetalhada");
             
    }
    /*@Test (expected = AreaActividadeDuplicadaException.class)
    public void testCriarAreaActividadeDuplicada() {
        
        AreaActividade a1= new AreaActividade("1234", "AreaActividade1", "AreaActividadeDescDetalhada");
        AreaActividade a2= new AreaActividade("1234", "AreaActividade1", "AreaActividadeDescDetalhada");
             
    }*/
    
           
    
}

