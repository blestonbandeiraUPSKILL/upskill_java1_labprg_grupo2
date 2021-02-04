/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.model;

import com.grupo2.t4j.model.*;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;


/**
 *
 * @author blest
 */
public class UtilizadorTest {
    
    public UtilizadorTest() {
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
    public void testCreateUtilizador() {
        
        Utilizador u1= new Utilizador("Fulano", "fulano@upskill.pt", "12345678");
        
        assertEquals("Fulano", u1.getNome());
        assertEquals("fulano@upskill.pt", u1.getEmail().getEmailText());
        assertEquals("12345678", u1.getPassword().getPasswordText());
    }
    
}
