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
import java.util.ArrayList;
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
        
       Utilizador u1= new Utilizador("Fulano", "fulano@upskill.pt");
      
        ru1.addUtilizador(u1);
        
        assertTrue(ru1.getListaUtilizadores().contains(u1));
        System.out.println("testinho1");
    }
    
    @Test
    public void testAddUtilizadorRepo() {
       
        RepositorioUtilizador ru1 = RepositorioUtilizador.getInstance();
            
        ru1.addUtilizador("Fulano", "fulano@upskill.pt");
        
        System.out.println(ru1.getListaUtilizadores().get(0).toString());
    }
    
    @Test (expected = UtilizadorDuplicadoException.class)
    public void testAddUtilizadorDuplicado() {
       
        RepositorioUtilizador ru1 = RepositorioUtilizador.getInstance();
        
        Utilizador u1= new Utilizador("Fulano", "fulano@upskill.pt");
        Utilizador u2= new Utilizador("Fulano", "fulano@upskill.pt");
        
        ru1.addUtilizador(u1);
        ru1.addUtilizador(u2);              
    }
     
    @Test
    public void testGetUtilizadorByEmail() {
        
        RepositorioUtilizador ru1 = RepositorioUtilizador.getInstance();
        
        Utilizador u1 = new Utilizador("Fulano", "fulano@upskill.pt");
        Utilizador u2 = new Utilizador("Beltrano", "beltrano@upskill.pt");
        
        ru1.addUtilizador(u1);
        ru1.addUtilizador(u2); 
                
        Utilizador u3 = ru1.getUtilizadorByEmail("fulano@upskill.pt");
        Utilizador u4 = ru1.getUtilizadorByEmail(u2.getEmail().getEmailText());
        
        assertEquals(u1,u3);
        assertEquals(u2,u4);
        System.out.println(u4.toStringSemPass());        
    }
    
    @Test
    public void testGetListaUtilizadores() {
        
        RepositorioUtilizador ru1 = RepositorioUtilizador.getInstance();
        
        Utilizador u1 = new Utilizador("Fulano", "fulano@upskill.pt");
        Utilizador u2 = new Utilizador("Beltrano", "beltrano@upskill.pt");
        
        ru1.addUtilizador(u1);
        ru1.addUtilizador(u2);
        
        ArrayList<Utilizador> lista = ru1.getListaUtilizadores();
        
        System.out.println(lista.get(0).toStringSemPass());
        System.out.println(lista.get(1).toString());   
   
        Email emailA0 = new Email("fulano@upskill.pt");
        assertEquals(lista.get(0).getEmail().getEmailText(),emailA0.getEmailText());        
    }
}
