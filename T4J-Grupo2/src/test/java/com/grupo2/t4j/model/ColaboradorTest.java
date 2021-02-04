/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.model;

import com.grupo2.t4j.exception.*;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;
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

    @Test
    public void testCriarColaborador() {
        
        Colaborador c1 = new Colaborador("Fulano", "fulano@upskill.pt", "12345678", 
        "Gestor", "999888777");
        
        assertEquals("Fulano", c1.getNome());
        assertEquals("fulano@upskill.pt", c1.getEmail().getEmailText());
        assertEquals("12345678", c1.getPassword().getPasswordText());
        assertEquals("Gestor", c1.getFuncao());
        assertEquals("999888777", c1.getTelefone());
    }
    
    @Test (expected = NomeInvalidoException.class)
    public void testCriarColaboradorNomeIncorreto() {
        
        Colaborador c2 = new Colaborador("", "fulano@upskill.pt", "12345678", 
        "Gestor", "999888777");
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testCriarColaboradorEmailFormaIncorreta() {
        
        Colaborador c3 = new Colaborador("Fulano", "fulano", "12345678", 
        "Gestor", "999888777");
    }
    
    @Test (expected = PasswordInvalidaException.class)
    public void testCriarColaboradorPasswordFormaIncorreta() {
       
        Colaborador c4 = new Colaborador("Fulano", "fulano@upskill.pt", "123", 
        "Gestor", "999888777");
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testCriarColaboradorFuncaoInvalida() {
       
        Colaborador c5 = new Colaborador("Fulano", "fulano@upskill.pt", "12345678", 
        "", "999888777");
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testCriarColaboradorTelefoneInvalido() {
       
        Colaborador c6 = new Colaborador("Fulano", "fulano@upskill.pt", "12345678", 
        "Gestor", "999");
    }    
}
