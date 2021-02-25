/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.persistence;

/**
 *
 * @author CAD
 */

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
public class RepositorioTarefaInMemoryTest {

    public RepositorioTarefaInMemoryTest() {
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

   /* @Test
    public void testAddTarefa() {
        
        RepositorioTarefaInMemory rt1 = RepositorioTarefaInMemory.getInstance();
        
        Tarefa t1= new Tarefa("T_001", "Design", "Design web", "Design Web em Android", 
        14, 5000);
        
        rt1.addTarefa(t1);
        
        assertTrue(rt1.getAll().contains(t1));
    }  */
    
   /* @Test (expected = TarefaDuplicadaException.class)
    public void testAddTarefaDuplicada() {
        
        RepositorioTarefaInMemory rt1 = RepositorioTarefaInMemory.getInstance();
        
        Tarefa t1= new Tarefa("T_001", "Design", "Design web", "Design Web em Android", 
        14, 5000);
        Tarefa t2= new Tarefa("T_001", "Design", "Design web", "Design Web em Android", 
        14, 5000);
        
        rt1.addTarefa(t1);
        rt1.addTarefa(t2);
    }*/
    
  /*  @Test
    public void testGetTarefaByReferencia() {
        
        RepositorioTarefaInMemory rt1 = RepositorioTarefaInMemory.getInstance();
        
        Tarefa t1= new Tarefa("T_001", "Design", "Design web", "Design Web em Android", 
        14, 5000);
        Tarefa t2= new Tarefa("T_002", "Modelagem", "Modelo protótipo", "Protótipo em escala real", 
        20, 10000);
        
        rt1.addTarefa(t1);
        rt1.addTarefa(t2);
             
        Tarefa t3 = rt1.getTarefaByReferencia("T_002");
        
        Tarefa t4 = rt1.getTarefaByReferencia(t1.getReferencia());
        
        assertEquals(t2,t3);
        
        assertEquals(t1,t4);
        
        System.out.println("Teste 2 ok");
    }*/
    
/*    @Test
    public void testGetListaTarefas() {
        
         RepositorioTarefaInMemory rt1 = RepositorioTarefaInMemory.getInstance();
        
        Tarefa t1= new Tarefa("T_001", "Design", "Design web", "Design Web em Android", 
        14, 5000);
        Tarefa t2= new Tarefa("T_002", "Modelagem", "Modelo protótipo", "Protótipo em escala real", 
        20, 10000);
        
        rt1.addTarefa(t1);
        rt1.addTarefa(t2);
        
        ArrayList<Tarefa> lista = rt1.getAll();
        
        assertEquals(lista.get(0),t1);        
        assertEquals(lista.get(1),t2);        
    }*/
}
