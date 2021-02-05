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

public class RepositorioColaboradorTest {
    
    public RepositorioColaboradorTest() {
    }

    @BeforeEach
    public void setUp() {
        
    }

    @Test
    public void testAddColaborador() {
       
        RepositorioColaborador rc1 = RepositorioColaborador.getInstance();
        
        Colaborador c1= new Colaborador("Fulano", "fulano@upskill.pt", "12345678",
        "Gestor", "999888777");
      
        rc1.addColaborador(c1);
        
        assertTrue(rc1.getListaColaboradores().contains(c1));
    }
    
    @Test (expected = ColaboradorDuplicadoException.class)
    public void testAddColaboradorDuplicado() {
       
        RepositorioColaborador rc1 = RepositorioColaborador.getInstance();
        
        Colaborador c1= new Colaborador("Fulano", "fulano@upskill.pt", "12345678",
        "Gestor", "999888777");
        Colaborador c2= new Colaborador("Fulano", "fulano@upskill.pt", "12345678",
        "Gestor", "999888777");
      
        rc1.addColaborador(c1);
        rc1.addColaborador(c2);
    }
    
    @Test (expected = NomeInvalidoException.class)
    public void testAddColaboradorErroNome() {
        
        RepositorioColaborador rc1 = RepositorioColaborador.getInstance();
        
        Colaborador c1= new Colaborador("", "fulano@upskill.pt", "12345678",
        "Gestor", "999888777");
        
         rc1.addColaborador(c1);
    }
    
    @Test
    public void testGetColaboradorByEmail() {
        
        RepositorioColaborador rc1 = RepositorioColaborador.getInstance();
        
        Colaborador c1= new Colaborador("Fulano", "fulano@upskill.pt", "12345678",
        "Gestor", "999888777");
        Colaborador c2= new Colaborador("Beltrano", "beltrano@upskill.pt", "12345678",
        "Gestor", "999888777");
        
        rc1.addColaborador(c1);
        rc1.addColaborador(c2);
                
        Colaborador c3 = rc1.getColaboradorByEmail("fulano@upskill.pt");
        Colaborador c4 = rc1.getColaboradorByEmail(c2.getEmail().getEmailText());
        
        assertEquals(c1,c3);
        assertEquals(c2,c4);
        
    }
}
