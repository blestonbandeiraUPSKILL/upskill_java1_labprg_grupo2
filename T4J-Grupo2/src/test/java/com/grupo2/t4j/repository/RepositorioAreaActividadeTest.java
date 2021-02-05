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
import com.grupo2.t4j.exception.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;

public class RepositorioAreaActividadeTest {
    
    public RepositorioAreaActividadeTest() {
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
    public void testAddAreaActividade() {
        
        RepositorioAreaActividade raa1 = RepositorioAreaActividade.getInstance();
        
        AreaActividade aa1= new AreaActividade("AA_001", "Design", "Design web");
        
        raa1.addAreaActividade(aa1);
        
        assertTrue(raa1.getListaAreasActividade().contains(aa1));
    }   
    
    @Test (expected = AreaActividadeDuplicadaException.class)
    public void testAddAreaActividadeDuplicada() {
        
        RepositorioAreaActividade raa1 = RepositorioAreaActividade.getInstance();
        
        AreaActividade aa1= new AreaActividade("AA_001", "Design", "Design web");
        AreaActividade aa2= new AreaActividade("AA_001", "Design", "Design web");
        
        raa1.addAreaActividade(aa1);
        raa1.addAreaActividade(aa2);
    }
    
    @Test
    public void testGetAreaActividadeByCodigo() {
        
        RepositorioAreaActividade raa1 = RepositorioAreaActividade.getInstance();
        
        AreaActividade aa1= new AreaActividade("AA_001", "Design", "Design web");
        AreaActividade aa2= new AreaActividade("AA_002", "Modelagem", "Modelagem protótipo");
        
        raa1.addAreaActividade(aa1);
        raa1.addAreaActividade(aa2);
             
        AreaActividade aa3 = raa1.getAreaActividadeByCodigo("AA_002");
        
        AreaActividade aa4 = raa1.getAreaActividadeByCodigo(aa1.getCodigo());
        
        assertEquals(aa2,aa3);
        
        assertEquals(aa1,aa4);
        
        System.out.println("Teste 2 ok");
    }
    
    @Test
    public void testGetListaAreasActividade() {
        
        RepositorioAreaActividade raa1 = RepositorioAreaActividade.getInstance();
        
        AreaActividade aa1= new AreaActividade("AA_001", "Design", "Design web");
        AreaActividade aa2= new AreaActividade("AA_002", "Modelagem", "Modelagem protótipo");
        
        raa1.addAreaActividade(aa1);
        raa1.addAreaActividade(aa2);
        
        ArrayList<AreaActividade> lista = raa1.getListaAreasActividade();
        
        assertEquals(lista.get(0),aa1);        
    }
}
