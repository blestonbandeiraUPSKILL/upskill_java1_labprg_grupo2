/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.grupo2.t4j.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;


/**
 *
 * @author CAD
 */

public class ColaboradorTest {
    
    public ColaboradorTest() {
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

  /*  @Test
    public void testCriarColaborador() {
        
        Colaborador c1 = new Colaborador("fulano@upskill.pt", "Fulano","Assistente",
                "999888777");

        assertEquals("Fulano", c1.getNome());
        assertEquals("fulano@upskill.pt", c1.getEmail().getEmailText());
        assertEquals("Assistente", c1.getFuncao());
        assertEquals("999888777", c1.getTelefone());
    }
     
    @Test (expected = NomeInvalidoException.class)
    public void testCriarColaboradorNomeIncorreto() {
        
        Colaborador c1 = new Colaborador("fulano@upskill.pt", "", "Assistente", "999888777");
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testCriarColaboradorEmailFormaIncorreta() {
        
        Colaborador c1 = new Colaborador("fulano", "Fulano", "Assistente", "999888777");
    }
      
    @Test (expected = IllegalArgumentException.class)
    public void testCriarColaboradorFuncaoInvalida() {
      
        Colaborador c1 = new Colaborador("fulano@upskill.pt", "Fulano","", "999888777");
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testCriarColaboradorTelefoneInvalido() {
        
        Colaborador c1 = new Colaborador("Fulano", "fulano@upskill.pt", "Assistente", "999");
    }    */
}
