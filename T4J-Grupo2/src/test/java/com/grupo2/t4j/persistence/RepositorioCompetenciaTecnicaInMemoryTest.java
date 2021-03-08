/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.persistence;

import org.junit.Test;
import com.grupo2.t4j.exception.CompetenciaTecnicaDuplicadaException;
import com.grupo2.t4j.exception.DescricaoInvalidaException;
import com.grupo2.t4j.domain.CompetenciaTecnica;
import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;


/**
 *
 * @author acris
 */
public class RepositorioCompetenciaTecnicaInMemoryTest {
    
    public RepositorioCompetenciaTecnicaInMemoryTest() {
    }
    
    @BeforeEach
    public void setUp() {
    }

    

   /* @Test(expected = CompetenciaTecnicaDuplicadaException.class)
    public void testAddCompetenciaTecnicaDuplicada() {
        //Arrange
        RepositorioCompetenciaTecnicaInMemory rc1= RepositorioCompetenciaTecnicaInMemory.getInstance();
        CompetenciaTecnica ct1= new CompetenciaTecnica("1234", "Jogo do Galo", "Jogo para duas pessoas");
        CompetenciaTecnica ct2= new CompetenciaTecnica("1234", "Jogo do Galo", "Jogo para duas pessoas");
        
        //Act
        rc1.addCompetenciaTecnica(ct1);
        rc1.addCompetenciaTecnica(ct2);


    }*/

    /*@Test(expected = DescricaoInvalidaException.class)
    public void testAddCompetenciaDescricaoInvalida() {
        //Arrange
        RepositorioCompetenciaTecnicaInMemory rc1= RepositorioCompetenciaTecnicaInMemory.getInstance();;
        CompetenciaTecnica ct1= new CompetenciaTecnica("1235", "", "Jogo para duas pessoas");
        //Act
        rc1.addCompetenciaTecnica(ct1);    
    }*/

/*    @Test
    public void testAddCompetenciaTecnica() {
        //Arrange
        RepositorioCompetenciaTecnicaInMemory rc1=  RepositorioCompetenciaTecnicaInMemory.getInstance();
        CompetenciaTecnica ct1= new CompetenciaTecnica("123456", "Jogo do Galo", "Jogo para duas pessoas");
        
        //Act
        rc1.addCompetenciaTecnica(ct1);
        
        
        //Assert
        assertTrue(rc1.getCompetenciasTecnicas().contains(ct1));
    }*/
    
    
/*    @Test
    public void testGetCompetenciaTecnicaByCodigo() {
        
        //Arrange
        RepositorioCompetenciaTecnicaInMemory rc1=  RepositorioCompetenciaTecnicaInMemory.getInstance();
        CompetenciaTecnica ct1= new CompetenciaTecnica("123456", "Jogo do Galo", "Jogo para duas pessoas");
        rc1.addCompetenciaTecnica(ct1);
        
        //Act
        CompetenciaTecnica ct2 = rc1.getCompetenciaTecnicaByCodigo("123456");
        
        //
        assertEquals(ct1, ct2);
        
    }*/

 /*   @Test
    public void testGetCompetenciasTecnicasByAreaActividade() {
        //Arrange
        AreaActividade at= new AreaActividade ("123","Cozinha", "Cozinheiro de 2ª");
        RepositorioCompetenciaTecnicaInMemory rc1=  RepositorioCompetenciaTecnicaInMemory.getInstance();
        CompetenciaTecnica ct1= new CompetenciaTecnica("123456", "Jogo do Galo", "Jogo para duas pessoas",at);
        rc1.addCompetenciaTecnica(ct1);
        
        //Act
        ArrayList<CompetenciaTecnica> ct2 = rc1.getCompetenciasTecnicasByAreaActividade(at);
        
        //
        assertTrue(ct2.contains(ct1));
        
    }*/
    
}
