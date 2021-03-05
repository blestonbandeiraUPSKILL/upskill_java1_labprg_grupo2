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
public class EnderecoPostalTest {
    
    public EnderecoPostalTest() {
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

 /*   @Test
    public void testCriarEnderecoPostal() {
        
        EnderecoPostal e1 = new EnderecoPostal("rua", "s/n", "Portugal", "4000-011");
        
        assertEquals("rua", e1.getArruamento());
        assertEquals("s/n", e1.getPorta());
        assertEquals("Portugal", e1.getLocalidade());
        assertEquals("4000-011", e1.getCodigoPostal());
    }*/
    
  /*  @Test (expected = NomeInvalidoException.class)
    public void testCriarEnderecoPostalArruamentoIncorreto() {
        
         EnderecoPostal e2 = new EnderecoPostal("", "s/n", "Portugal", "1111-222");
    }*/
    
   /* @Test (expected = NomeInvalidoException.class)
    public void testCriarEnderecoPostalPortaIncorreta() {
        
         EnderecoPostal e3 = new EnderecoPostal("rua", "", "Portugal", "1111-222");
    }*/
    
  /*  @Test (expected = NomeInvalidoException.class)
    public void testCriarEnderecoPostalLocalidadeIncorreta() {
        
         EnderecoPostal e4 = new EnderecoPostal("rua", "s/n", "", "1111-222");
    }*/
    
 /*   @Test (expected = NomeInvalidoException.class)
    public void testCriarEnderecoPostalCPIncorreto() {
        
         EnderecoPostal e5 = new EnderecoPostal("rua", "s/n", "Portugal", "1114-abc");
    }*/
    
}
