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

import java.util.ArrayList;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.grupo2.t4j.model.*;
import com.grupo2.t4j.exception.AdministrativoDuplicadoException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;

public class RepositorioAdministrativoTest {

    public RepositorioAdministrativoTest() {
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
    public void testAddAdministrativo() {
        
        RepositorioAdministrativo ra1 = RepositorioAdministrativo.getInstance();
        
        Administrativo a1= new Administrativo("Fulano", "fulano@upskill.pt", "12345678");
        
        ra1.addAdministrativo(a1);
        
        assertTrue(ra1.getListaAdministrativos().contains(a1));
        System.out.println(ra1.getListaAdministrativos().get(0).toString());
    }  
    
    @Test
    public void testAddAdministrativoRepo() {
        
        RepositorioAdministrativo ra1 = RepositorioAdministrativo.getInstance();
                   
        ra1.addAdministrativo("Fulano", "fulano@upskill.pt", "12345678");
                
        System.out.println(ra1.getListaAdministrativos().get(0).toString());
    } 
    
    @Test (expected = AdministrativoDuplicadoException.class)
    public void testAddAdministrativoDuplicado() {
        
        RepositorioAdministrativo ra1 = RepositorioAdministrativo.getInstance();
        
        Administrativo a1 = new Administrativo("Fulano", "fulano@upskill.pt", "12345678");
        Administrativo a2 = new Administrativo("Fulano", "fulano@upskill.pt", "12345678");
        
        ra1.addAdministrativo(a1);
        ra1.addAdministrativo(a2);
    }

    @Test
    public void testGetAdministrativoByEmail() {
        
        RepositorioAdministrativo ra1 = RepositorioAdministrativo.getInstance();
        
        Administrativo a1= new Administrativo("Fulano", "fulano@upskill.pt", "12345678");
        Administrativo a2 = new Administrativo("Beltrano", "beltrano@upskill.pt", "12345678");
        
        ra1.addAdministrativo(a1);
        ra1.addAdministrativo(a2);
           
        Administrativo a3 = ra1.getAdministrativoByEmail("fulano@upskill.pt");
        Administrativo a4 = ra1.getAdministrativoByEmail(a2.getEmail().getEmailText());
        
        assertEquals(a1,a3);
        assertEquals(a2,a4); 
    }
    
    @Test
    public void testGetListaAdministrativos() {
        
        RepositorioAdministrativo ra1 = RepositorioAdministrativo.getInstance();
        
        Administrativo a1= new Administrativo("Fulano", "fulano@upskill.pt", "12345678");
        Administrativo a2 = new Administrativo("Beltrano", "beltrano@upskill.pt", "12345678");
        
        ra1.addAdministrativo(a1);
        ra1.addAdministrativo(a2);
        
        ArrayList<Administrativo> lista = ra1.getListaAdministrativos();
        
        System.out.println(lista.get(0).toStringSemPass());
        System.out.println(lista.get(1).toString());   
   
        Email emailA0 = new Email("fulano@upskill.pt");
        assertEquals(lista.get(0).getEmail().getEmailText(),emailA0.getEmailText());
        
    }
}
