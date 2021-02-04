/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.model;

import com.grupo2.t4j.exception.*;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;

/**
 *
 * @author CAD
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
    public void testCriarOrganizacao() {
                
        Colaborador colabGestor = new Colaborador("Fulano", "fulano@upskill.pt", "12345678", 
        "Gestor", "999888777");
        
        EnderecoPostal endereco = new EnderecoPostal("rua", "s/n", "Portugal", "1111-222");
        
        Website website = new Website("http://www.t4j.pt");
        
        Email email = new Email("t4j@t4j.pt");
        
        Organizacao o1 = new Organizacao("T4J", "111222333", endereco, website,
                "999888777", email , colabGestor);
        
        assertEquals("T4J", o1.getNome());
        assertEquals("111222333", o1.getNif());
        assertEquals("rua", o1.getEnderecoPostal().getArruamento());
        assertEquals("s/n", o1.getEnderecoPostal().getPorta());
        assertEquals("Portugal", o1.getEnderecoPostal().getLocalidade());
        assertEquals("1111-222", o1.getEnderecoPostal().getCodigoPostal());
        assertEquals("http://www.t4j.pt", o1.getWebsite().getWebsiteText());
        assertEquals("999888777", o1.getTelefone());
        assertEquals("t4j@t4j.pt", o1.getEmail().getEmailText());
        
        /* Colaborador Gestor já testado em ColaboradorTest 
           Endereço Postal já testado em EnderecoPostalTest
           Website já testado em WebsiteTest
           Email já testado em EmailTest
        */
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testCriarOrganizacaoNomeIncorreto() {
        
        Colaborador colabGestor = new Colaborador("Fulano", "fulano@upskill.pt", "12345678", 
        "Gestor", "999888777");
        
        EnderecoPostal endereco = new EnderecoPostal("rua", "s/n", "Portugal", "1111-222");
        
        Website website = new Website("http://www.t4j.pt");
        
        Email email = new Email("t4j@t4j.pt");
        
        Organizacao o2 = new Organizacao("", "111222333", endereco, website,
                "999888777", email , colabGestor);
              
    }
    
    @Test (expected = NifInvalidoException.class)
    public void testCriarOrganizacaoNifInvalido() {
        
        Colaborador colabGestor = new Colaborador("Fulano", "fulano@upskill.pt", "12345678", 
        "Gestor", "999888777");
        
        EnderecoPostal endereco = new EnderecoPostal("rua", "s/n", "Portugal", "1111-222");
        
        Website website = new Website("http://www.t4j.pt");
        
        Email email = new Email("t4j@t4j.pt");
        
        Organizacao o3 = new Organizacao("T4J", "111", endereco, website,
                "999888777", email , colabGestor);
              
    }
        
    @Test (expected = IllegalArgumentException.class)
    public void testCriarOrganizacaoTelefoneInvalido() {
       
        Colaborador colabGestor = new Colaborador("Fulano", "fulano@upskill.pt", "12345678", 
        "Gestor", "999888777");
        
        EnderecoPostal endereco = new EnderecoPostal("rua", "s/n", "Portugal", "1111-222");
        
        Website website = new Website("http://www.t4j.pt");
        
        Email email = new Email("t4j@t4j.pt");
        
        Organizacao o4 = new Organizacao("T4J", "111222333", endereco, website,
                    "999", email , colabGestor);
    }
}