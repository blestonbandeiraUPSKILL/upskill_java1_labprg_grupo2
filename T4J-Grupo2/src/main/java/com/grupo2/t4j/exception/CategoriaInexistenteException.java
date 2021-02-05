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
public class CategoriaInexistenteException extends IllegalArgumentException{
    public CategoriaInexistenteException(String s) {
        super(s);
    }
}
