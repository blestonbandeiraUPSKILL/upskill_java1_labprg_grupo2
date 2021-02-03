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
public class  Password {

    private String password;
    
    public Password (String password){
        setPassword(password);
    }
    
    public Password (Password password){
        setPassword(password.password);
    }
    
    public void setPassword(String password){
        if(eSenhaValida(password)){
            this.password = password;
        }
        else{
            throw new IllegalArgumentException("A password não é válida!");
        }
    }
        
    private static boolean eSenhaValida(String password){
        if(password == null){
            return false;
        }
        if(password.length()<8){
            return false;
        }
        return true;
    }
}
