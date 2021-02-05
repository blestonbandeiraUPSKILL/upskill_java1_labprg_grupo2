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
import com.grupo2.t4j.exception.*;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class RepositorioUtilizadorTest {
       
    public RepositorioUtilizadorTest() {
    }

    @BeforeEach
    public void setUp() {
        
    }

    @Test
    public void testAddUtilizador() {
       
        RepositorioUtilizador ru1 = RepositorioUtilizador.getInstance();
        
        Utilizador u1= new Utilizador("Fulano", "fulano@upskill.pt", "12345678");
      
        ru1.addUtilizador(u1);
        
        assertTrue(ru1.getListaUtilizadores().contains(u1));
    }
    
    // Não deu certo este
    @Test (expected = UtilizadorDuplicadoException.class)
    public void testAddUtilizadorDuplicado() {
       
        RepositorioUtilizador ru1 = RepositorioUtilizador.getInstance();
        
        Utilizador u1= new Utilizador("Fulano", "fulano@upskill.pt", "12345678");
        Utilizador u2= new Utilizador("Fulano", "fulano@upskill.pt", "12345678");
        
        ru1.addUtilizador(u1);
        ru1.addUtilizador(u2);              
    }
    
    @Test (expected = NomeInvalidoException.class)
    public void testAddUtilizadorErroNome() {
        
        RepositorioUtilizador ru1 = RepositorioUtilizador.getInstance();
        
        Utilizador u1= new Utilizador("", "fulano@upskill.pt", "12345678");
        
        ru1.addUtilizador(u1);
    }
    
    // Não deu certo este
    @Test
    public void testGetUtilizadorByEmail() {
        
        RepositorioUtilizador ru1 = RepositorioUtilizador.getInstance();
        
        Utilizador u1 = new Utilizador("Fulano", "fulano@upskill.pt", "12345678");
        Utilizador u2 = new Utilizador("Beltrano", "beltrano@upskill.pt", "12345678");
        
        ru1.addUtilizador(u1);
        ru1.addUtilizador(u2); 
        
        Email email1 = new Email("fulano@upskill.pt");
        Email email2 = new Email("beltrano@upskill.pt");
        
        Utilizador u3 = ru1.getUtilizadorByEmail(email1);
        Utilizador u4 = ru1.getUtilizadorByEmail(u2.getEmail());
        
        assertEquals(u1,u3);
        assertEquals(u2,u4);
        
    }
}
