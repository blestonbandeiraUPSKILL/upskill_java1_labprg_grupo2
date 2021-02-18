/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.exception;

/**
 *
 * @author acris
 */
public class CompetenciaTecnicaInexistenteException extends IllegalArgumentException{
    public CompetenciaTecnicaInexistenteException(String s) {
        super(s);
    }
}
