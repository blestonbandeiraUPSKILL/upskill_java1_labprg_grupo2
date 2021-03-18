/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.domain;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 *
 * @author carol
 */
public class CandidaturaTest {

    @Test
    public void testCriarCandidatura(){
        Candidatura c1 = new Candidatura( 500.0, 30, "apresentacao", "motivacao",
                101, "nome@mail.com");

        //assertEquals( 500.0, c1.getValorPretendido());
        assertEquals(30, c1.getNumeroDias());
        assertEquals("apresentacao", c1.getApresentacao());
        assertEquals("motivacao", c1.getMotivacao());
        assertEquals(101, c1.getIdAnuncio());
        assertEquals("nome@mail.com", c1.getEmailFreelancer());
    }
    
}
