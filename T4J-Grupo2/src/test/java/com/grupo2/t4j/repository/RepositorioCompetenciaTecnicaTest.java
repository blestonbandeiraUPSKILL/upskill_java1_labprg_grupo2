/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.repository;

import com.grupo2.t4j.model.CompetenciaTecnica;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author acris
 */
public class RepositorioCompetenciaTecnicaTest {
    
    public RepositorioCompetenciaTecnicaTest() {
    }
    
    @BeforeEach
    public void setUp() {
    }

    @Test
    public void testAddCompetenciaTecnica() {
        //Arrange
        RepositorioCompetenciaTecnica rc1= new RepositorioCompetenciaTecnica();
        CompetenciaTecnica ct1= new CompetenciaTecnica("1234", "Jogo do Galo", "Jogo para duas pessoas");
        //Act
        rc1.addCompetenciaTecnica(ct1);
        
        //Assert
        assertTrue(rc1.getCompetenciasTecnicas().contains(ct1));
    }

    @Test
    public void testSetListaCompTecnicas() {
    }

    @Test
    public void testGetCompetenciasTecnicas() {
    }

    @Test
    public void testGetCompetenciaTecnicaByCodigo() {
    }
    
}
