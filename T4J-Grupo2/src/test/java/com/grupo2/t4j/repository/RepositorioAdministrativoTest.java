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
        System.out.println("Teste 1 ok");
    }   
    
    /*@Test (expected = AdministrativoDuplicadoException.class)
    public void testAddAdministrativoDuplicado() {
        
        RepositorioAdministrativo ra1 = RepositorioAdministrativo.getInstance();
        
        Administrativo a1= new Administrativo("Fulano", "fulano@upskill.pt", "12345678");
        Administrativo a2 = new Administrativo("Fulano", "fulano@upskill.pt", "12345678");
        
        ra1.addAdministrativo(a1);
        ra1.addAdministrativo(a2);
    }*/
    
    /*@Test
    public void testGetAdministrativoByEmail() {
        
        RepositorioAdministrativo ra1 = RepositorioAdministrativo.getInstance();
        
        Administrativo a1= new Administrativo("Fulano", "fulano@upskill.pt", "12345678");
        Administrativo a2 = new Administrativo("Beltrano", "beltrano@upskill.pt", "12345678");
        
        ra1.addAdministrativo(a1);
        ra1.addAdministrativo(a2);
        
        Email emailA1 = new Email(a1.getEmail());
        Email emailA2 = new Email(a2.getEmail());
        
        //Administrativo a3 = ra1.
        
        //System.out.println(a3.getNome());
                
        assertEquals("Fulano", ra1.getAdministrativoByEmail(emailA1).getNome());
        System.out.println("Teste 2 ok");
    }*/
    
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
        System.out.println("Teste 3 ok");
        Email emailA0 = new Email("fulano@upskill.pt");
        assertEquals(lista.get(0).getEmail().getEmailText(),emailA0.getEmailText());
        
    }
}
