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

import com.grupo2.t4j.exception.*;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;

public class AnuncioTest {
    
    public AnuncioTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testCriarAnuncio() {
        
        Anuncio a1 = new Anuncio("Anuncio_001", "2021/03/01", "2021/05/31",
        "2021/03/05", "2021/04/20", "2021/05/21", "2021/05/30");
        
        assertEquals("Anuncio_001", a1.getIdAnuncio());
        assertEquals("2021/03/01", a1.getDtInicioPub().toAnoMesDiaString());
        assertEquals("2021/05/31", a1.getDtFimPub().toAnoMesDiaString());
        assertEquals("2021/03/05", a1.getDtInicioPub().toAnoMesDiaString());
        assertEquals("2021/04/20", a1.getDtFimCand().toAnoMesDiaString());
        assertEquals("2021/05/21", a1.getDtInicioSeriacao().toAnoMesDiaString());
        assertEquals("2021/05/30", a1.getDtFimSeriacao().toAnoMesDiaString());
    }
     
    @Test (expected = NomeInvalidoException.class)
    public void testCriarColaboradorNomeIncorreto() {
        
        Colaborador c1 = new Colaborador("", "fulano@upskill.pt", "Assistente", "999888777");
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testCriarColaboradorEmailFormaIncorreta() {
        
        Colaborador c1 = new Colaborador("Fulano", "fulano", "Assistente", "999888777");
    }
      
    @Test (expected = IllegalArgumentException.class)
    public void testCriarColaboradorFuncaoInvalida() {
      
        Colaborador c1 = new Colaborador("Fulano", "fulano@upskill.pt","", "999888777");
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testCriarColaboradorTelefoneInvalido() {
        
        Colaborador c1 = new Colaborador("Fulano", "fulano@upskill.pt", "Assistente", "999");
    }   
}
