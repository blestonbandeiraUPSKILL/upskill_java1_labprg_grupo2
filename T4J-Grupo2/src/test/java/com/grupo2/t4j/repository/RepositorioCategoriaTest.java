/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.repository;

import com.grupo2.t4j.exception.CategoriaDuplicadaException;
import com.grupo2.t4j.exception.DescricaoInvalidaException;
import org.junit.Test;
import com.grupo2.t4j.model.AreaActividade;
import com.grupo2.t4j.model.CaracterizacaoCT;
import com.grupo2.t4j.model.Categoria;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author acris
 */
public class RepositorioCategoriaTest {
    
    public RepositorioCategoriaTest() {
    }
    
    @BeforeEach
    public void setUp() {
        
    }


    @Test(expected = DescricaoInvalidaException.class)
    public void testAddDescricaoInvalida() {
        //Arrange
        //Arrange
        RepositorioCategoria rc1= RepositorioCategoria.getInstance();
        AreaActividade at= new AreaActividade ("123","Cozinha", "Cozinheiro de 2ª");
        List <CaracterizacaoCT> compTec = new ArrayList<>();
        Categoria cat1 = new Categoria ("", at, compTec);
        
        //Act
        rc1.addCategoria(cat1);
    }
    @Test
    public void testgetCategoriaById() {
        //Arrange
        RepositorioCategoria rc1= RepositorioCategoria.getInstance();
        AreaActividade at= new AreaActividade ("12345","Cozinhar", "Cozinheiro");
        List <CaracterizacaoCT> compTec = new ArrayList<>();
        Categoria cat1 = new Categoria ("Chef", at, compTec);
        rc1.addCategoria(cat1);
        //Act
        Categoria c1 = rc1.getCategoriaById("Chef_1");
        //Assert
        assertEquals(cat1,c1);
    }
    
    @Test
    public void testAddCategoria() {
        //Arrange
        RepositorioCategoria rc1= RepositorioCategoria.getInstance();
        AreaActividade at= new AreaActividade ("123","Cozinha", "Cozinheiro de 2ª");
        List <CaracterizacaoCT> compTec = new ArrayList<>();
        Categoria cat1 = new Categoria ("Cozinheiro", at, compTec);
        //Act
        rc1.addCategoria(cat1);
        
        //Assert
        assertTrue(rc1.getCategorias().contains(cat1));
    }
}
