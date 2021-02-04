/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.model;

import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.grupo2.t4j.exception.PasswordInvalidaException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;

/**
 *
 * @author CAD
 */
public class PasswordTest {
    
    public PasswordTest() {
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
    public void testaPasswordFormaCorreta() {
        
        String password;
        System.out.println("Digitando uma password válida:");
        password = "12345678";
        Password testePass = new Password(password);
        
        assertEquals("12345678",testePass.getPasswordText());
    }
    
    @Test (expected = PasswordInvalidaException.class)
    public void testPasswordIncorreto() {
        
        String passInv;
        System.out.println("Digitando uma password inválida:");
        passInv = "123";
        Password testePassInv = new Password(passInv);                
    }
    
}
