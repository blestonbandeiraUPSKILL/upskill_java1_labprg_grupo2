/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.repository;

/**
 *
 * @author CAD
 */

import com.grupo2.t4j.model.*;
import com.grupo2.t4j.repository.*;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RepositorioUtilizadorTest {
       
    public RepositorioUtilizadorTest() {
    }

    @BeforeEach
    public void setUp() {
        
    }

    @Test
    public void testAddUtilizador() {
        //Arrange
        RepositorioUtilizador ru1 = RepositorioUtilizador.getInstance();
        
        Utilizador u1= new Utilizador("Carol", "carol@upskill.pt", "12345678");
        //Act
        ru1.addUtilizador(u1);
        //Assert
        assertTrue(ru1.getListaUtilizadores().contains(u1));
    }
    
    @Test 
    public void testAddUtilizadorErroNome() {
        //Arrange
        RepositorioUtilizador ru1 = RepositorioUtilizador.getInstance();
        
        Utilizador u1= new Utilizador("Oi", "carol@upskill.pt", "12345678");
        //Act
        ru1.addUtilizador(u1);
        //Assert
        assertTrue(ru1.getListaUtilizadores().contains(u1));
    }

    @Test
    public void testSetListaUtilizadores() {
    }

    @Test
    public void testGetUtilizadores() {
    }

    @Test
    public void testGetUtilizadorByEmail() {
         //Arrange
        RepositorioUtilizador ru1 = RepositorioUtilizador.getInstance();
        Email email = new Email("carol@upskill.pt");
        Utilizador u2 = ru1.getUtilizadorByEmail(email);
         //Act
        u2.toString();
        //u2.toStringSemPass();
        //Assert
        assertEquals("Carol",u2.getNome());
        assertEquals("carol@upskill.pt",u2.getEmail().toString());
    }
}
