/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author blest
 */
public class OrganizacaoTest {
    
    public OrganizacaoTest() {
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
    public void testSomeMethod() {
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
    public void addColaborador(){
        //Arrange
        Organizacao o1= new Organizacao("Casa Rocha", "123456789", "casarocha.pt", "912345678","casa@rocha.com");
        Colaborador c1= new Colaborador("Diogo Ventura", "diogo@rocha.com", "PaSsWoRd", "RH", "923456781");
        //Act
        o1.addColaborador(c1);
        
        //Assert
        assertTrue(o1.getColaboradores().contains(c1));
    }
    
}
