/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.domain;


import org.junit.Before;

/**
 *
 * @author blest
 */
public class CategoriaTest {
    
    public CategoriaTest() {
    }

    @Before
    public void setUp() throws Exception {
    }
    
    /*@Test
    public void novaCategoriaTest() {
        //Arrange
        AreaActividade at= new AreaActividade ("123","Cozinha", "Cozinheiro de 2ª");
        CompetenciaTecnica ct1= new CompetenciaTecnica("123", "CompetenciaTecnica1", "CompetenciaTecnicaDescDetalhada");
        CaracterizacaoCT cct = new CaracterizacaoCT(ct1,, true);
        List <CaracterizacaoCT> compTec = new ArrayList<>(); 
        Categoria ct1 = new Categoria ("Cozinheiro", at, compTec);
        String expectedResult = "Cozinheiro; Cozinha; ";
        
        //Act
        
        String result = ct1.toString();
        
        //Assert
        assertEquals(expectedResult, result);
        
    }*/
    
  /*  @Test (expected = DescricaoInvalidaException.class)
    public void novaCategoriaInvalidaTest() {
       
        AreaActividade at= new AreaActividade ("223","Cozinha", "Cozinheiro de 2ª");
        List <CaracterizacaoCT> compTec = new ArrayList<>(); 
        Categoria ct1 = new Categoria ("", at, compTec);
       
    }*/

   /* @Test
    public void toStringCompTecTest() {
        //Arrange
        int id =1;
        AreaActividade at= new AreaActividade ("123","Cozinha", "Cozinheiro de 2ª");
        CompetenciaTecnica ct1= new CompetenciaTecnica("1234", "Galo", "Jogo para duas pessoas");
        GrauProficiencia gp = new GrauProficiencia(3, "Bom");
        CaracterizacaoCT cct = new CaracterizacaoCT(ct1, gp, false); 
        List <CaracterizacaoCT> compTec = new ArrayList<>();
        Categoria cat1 = new Categoria ("Cozinheiro", at, compTec);
        compTec.add(cct);
        
        String expectedResult="->Competência Técnica: Galo;\nGrau de Proficiência Mínimo: Bom;\nCarácter: Opcional.\n";
        
        
        //Act
        
        String result = cat1.toStringCompTec();
        
        //Assert
        assertEquals(expectedResult, result);
        
    }*/
}
