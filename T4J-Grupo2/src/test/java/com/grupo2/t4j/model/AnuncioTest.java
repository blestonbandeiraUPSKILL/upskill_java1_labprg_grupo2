/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.model;

/**
 *
 * @author CAD
 */

import com.grupo2.t4j.exception.*;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;

public class AnuncioTest {
    
    public AnuncioTest() {
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

    /*@Test
    public void testCriarAnuncio() {
        
        Anuncio a1 = new Anuncio("Anuncio_001", "2021/03/01", "2021/05/31",
        "2021/03/05", "2021/04/20", "2021/05/21", "2021/05/30");
        
        assertEquals("Anuncio_001", a1.getIdAnuncio());
        assertEquals("2021/03/01", a1.getDtInicioPub().toAnoMesDiaString());
        assertEquals("2021/05/31", a1.getDtFimPub().toAnoMesDiaString());
        assertEquals("2021/03/05", a1.getDtInicioCand().toAnoMesDiaString());
        assertEquals("2021/04/20", a1.getDtFimCand().toAnoMesDiaString());
        assertEquals("2021/05/21", a1.getDtInicioSeriacao().toAnoMesDiaString());
        assertEquals("2021/05/30", a1.getDtFimSeriacao().toAnoMesDiaString());
        System.out.println("Teste deu certo");
    }
    
    @Test
    public void testEformaData() {
        
        String dataTexto = "2021/11/12";
        int dia = Integer.parseInt(dataTexto.substring(8,10));
        System.out.println("dia: " + dia);
        int mes = Integer.parseInt(dataTexto.substring(5,7));
        System.out.println("mes: " + mes);
        int ano = Integer.parseInt(dataTexto.substring(0,4));
        System.out.println("ano: " + ano);
        Data testeData = new Data(ano, mes, dia);
        System.out.println(testeData.toString());
    }        
     
    @Test (expected = DataInvalidaException.class)
    public void testCriarAnuncioDataInPubIncorreta() {
        
        System.out.println("Teste: data de início publicitação anterior à data atual: ");
        Anuncio a1 = new Anuncio("Anuncio_001", "2021/01/01", "2021/05/31",
        "2021/03/05", "2021/05/20", "2021/05/21", "2021/05/30");
    }
    
    @Test (expected = DataInvalidaException.class)
    public void testCriarAnuncioDataFimPubIncorreta() {
        
        System.out.println("Teste: data do fim publicitação menor que data de "
                + "início da publicitação: ");
        Anuncio a1 = new Anuncio("Anuncio_001", "2021/03/01", "2021/02/28",
        "2021/03/05", "2021/05/20", "2021/05/21", "2021/05/30");
    }
    
    @Test (expected = DataInvalidaException.class)
    public void testCriarAnuncioDataInCandIncorreta() {
        
        System.out.println("Teste: data de início candidatura anterior à data do "
                + "período de publicitação: ");
        Anuncio a1 = new Anuncio("Anuncio_001", "2021/03/01", "2021/05/31",
        "2021/02/28", "2021/05/20", "2021/05/21", "2021/05/30");
    }
    
    @Test (expected = DataInvalidaException.class)
    public void testCriarAnuncioDataFimCandIncorreta() {
        
        System.out.println("Teste: data do fim candidatura posterior à data do "
                + " fim do período de publicitação: ");
        Anuncio a1 = new Anuncio("Anuncio_001", "2021/03/01", "2021/05/31",
        "2021/03/05", "2021/06/01", "2021/05/21", "2021/05/30");
    }
    
    @Test (expected = DataInvalidaException.class)
    public void testCriarAnuncioDataInSeriacaoIncorreta() {
        
        System.out.println("Teste: data de início da seriação anterior à data do "
                + "fim do período de candidaturas: ");
        Anuncio a1 = new Anuncio("Anuncio_001", "2021/03/01", "2021/05/31",
        "2021/03/05", "2021/05/20", "2021/05/19", "2021/05/30");
    }
    
     @Test (expected = DataInvalidaException.class)
    public void testCriarAnuncioDataFimSeriacaoIncorreta() {
        
        System.out.println("Teste: data do fim da seriação posterior à data do "
                + "fim do período de publicitação: ");
        Anuncio a1 = new Anuncio("Anuncio_001", "2021/03/01", "2021/05/31",
        "2021/03/05", "2021/05/20", "2021/05/21", "2021/06/01");
    }*/
}
