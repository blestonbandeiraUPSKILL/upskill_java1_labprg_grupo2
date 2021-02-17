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
import java.util.ArrayList;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class RepositorioColaboradorTest {
    
    public RepositorioColaboradorTest() {
    }

    @BeforeEach
    public void setUp() {
        
    }

  /*  @Test
    public void testAddColaborador() {
       
        RepositorioColaborador rc1 = RepositorioColaborador.getInstance();
        
        Colaborador c1= new Colaborador("Fulano", "fulano@upskill.pt", "Assistente", "999888777");
      
        rc1.addColaborador(c1);
        
        assertTrue(rc1.getListaColaboradores().contains(c1));
        System.out.println(rc1.getListaColaboradores().get(0).toString());
    }*/
    
    @Test
    public void testAddColaboradorRepo() {
       
        RepositorioColaborador rc1 = RepositorioColaborador.getInstance();
                
        rc1.addColaborador(new Email("fulano@upskill.pt"), "Fulano", "Assistente", "999888777", Rolename.COLABORADOR);
               
        System.out.println(rc1.getListaColaboradores().get(0).toString());
    }
    /*
    @Test (expected = ColaboradorDuplicadoException.class)
    public void testAddColaboradorDuplicado() {
       
        RepositorioColaborador rc1 = RepositorioColaborador.getInstance();
        
        Colaborador c1= new Colaborador(new Email("fulano@upskill.pt"), "Fulano", "Assistente", "999888777", Rolename.COLABORADOR);
        Colaborador c2= new Colaborador(new Email("fulano@upskill.pt"), "Fulano", "Assistente", "999888777", Rolename.COLABORADOR);
      
        rc1.addColaborador(c1);
        rc1.addColaborador(c2);
    }*/
    
   /* @Test
    public void testGetColaboradorByEmail() {
        
        RepositorioColaborador rc1 = RepositorioColaborador.getInstance();
        
        Colaborador c1= new Colaborador(new Email("Fulano"), "fulano@upskill.pt", "Assistente", "999888777");
        Colaborador c2= new Colaborador(new Email("Beltrano"), "beltrano@upskill.pt", "Assistente Jr", "999888555");
      
        rc1.addColaborador(c1);
        rc1.addColaborador(c2);
                         
        Colaborador c3 = rc1.getColaboradorByEmail("fulano@upskill.pt");
        Colaborador c4 = rc1.getColaboradorByEmail(c2.getEmail().getEmailText());
        
        assertEquals(c1,c3);
        assertEquals(c2,c4); 
    }*/
    
   /* @Test
    public void testGetListaColaboradores() {
        
        RepositorioColaborador rc1 = RepositorioColaborador.getInstance();
        
        Colaborador c1= new Colaborador(new Email("fulano@upskill.pt"), "Fulano", "Assistente", "999888777");
        Colaborador c2= new Colaborador(new Email("beltrano@upskill.pt"), "Beltrano", "Assistente Jr", "999888555");
      
        rc1.addColaborador(c1);
        rc1.addColaborador(c2);
        
        ArrayList<Colaborador> lista = rc1.getListaColaboradores();
        
        System.out.println(lista.get(0).toStringSemPass());
        System.out.println(lista.get(1).toString());   
   
        Email emailA0 = new Email("fulano@upskill.pt");
        assertEquals(lista.get(0).getEmail().getEmailText(),emailA0.getEmailText());
        
    }*/
}
