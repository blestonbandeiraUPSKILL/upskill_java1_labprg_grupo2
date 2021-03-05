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
public class WebsiteTest {

    public WebsiteTest() {
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
    public void testWebsiteCorreto() {
        
        String website;
        System.out.println("Digitando um website válido:");
        website = "https://www.upskill.pt";
        Website testeWebsite = new Website(website);
        
        assertEquals("https://www.upskill.pt",testeWebsite.getWebsiteText());
    }
    
    @Test (expected = WebsiteInvalidoException.class)
    public void testWebsiteIncorreto() {
        
        String websiteInv;
        System.out.println("Digitando um website válido:");
        websiteInv = "123";
        Website testeWebsite = new Website(websiteInv);                
    }*/
}