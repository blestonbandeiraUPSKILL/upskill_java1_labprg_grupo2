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
public class AdministrativoTest {
    
    public AdministrativoTest() {
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

    /*@Test
    public void testCriarAdministrativo() {
        
        Email email = new Email("fulano@upskill.pt");
        Password pass = new Password( "12345678");
        Email email2 = new Email("beltrano@upskill.pt");
        Password pass2 = new Password( "12345678");
        Administrativo a1= new Administrativo("Fulano", email, pass);
        Administrativo a2= new Administrativo("Beltrano", email2, pass2);
        
        System.out.println("Vai");
        System.out.println(a1.toStringSemPass());
        System.out.println(a2.toStringSemPass());
        assertEquals("Fulano", a1.getNome());
        assertEquals("fulano@upskill.pt", a1.getEmail().getEmailText());
        assertEquals("12345678", a1.getPassword().getPasswordText());
    }*/
   /*
    @Test (expected = NomeInvalidoException.class)
    public void testCriarAdministrativoNomeIncorreto() {
        
        Administrativo a1 = new Administrativo("", "fulano@upskill.pt", "12345678");
    }*/
 /*
    @Test (expected = IllegalArgumentException.class)
    public void testCriarAdministrativoEmailFormaIncorreta() {
        
        Administrativo a1 = new Administrativo("Fulano", "fulano", "12345678");
    }*/
    
   /* @Test (expected = PasswordInvalidaException.class)
    public void testCriarAdministrativoPasswordFormaIncorreta() {
       
        Administrativo a4 = new Administrativo("Fulano", "fulano@upskill.pt", "123");
    }*/
}
