/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.repository;

/**
 *
 * @author acris
 */
public class RepositorioAreaActividade {
    private static RepositorioAreaActividade instance;
    
    public static RepositorioAreaActividade getInstance(){
        if (instance == null){
            instance = new RepositorioAreaActividade();
        }
        return instance;
    }
    
}
