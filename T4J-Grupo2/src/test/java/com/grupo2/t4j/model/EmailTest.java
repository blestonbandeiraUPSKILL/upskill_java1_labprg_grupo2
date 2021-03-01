/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.model;

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
public class EmailTest {
    
    public EmailTest() {
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
    public void testEmailCorreto() {
        
        String email;
        System.out.println("Digitando um email válido:");
        email = "fulano@gmail.com";
        Email testeEmail = new Email(email);
        
        assertEquals("fulano@gmail.com",testeEmail.getEmailText());
    }
    
    /*@Test (expected = IllegalArgumentException.class)
    public void testEmailIncorreto() {
        
        String emailErrado;
        System.out.println("Digitando um email inválido:");
        emailErrado = "fulano";
        Email testeEmailErrado = new Email(emailErrado);
                
    }*/
}
