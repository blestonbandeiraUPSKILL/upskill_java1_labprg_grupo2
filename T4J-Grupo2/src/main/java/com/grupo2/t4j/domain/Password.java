/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.domain;

/**
 *
 * @author CAD
 */

import com.grupo2.t4j.exception.PasswordInvalidaException;

public class  Password {

    /**
     * A password
     */
    private String password;
    
    /**
     * Construtor Password
     * @param password 
     */
    public Password (String password){
        setPassword(password);
    }
    
    /**
     * Construtor password
     * @param password 
     */
    public Password (Password password){
        setPassword(password.password);
    }
    
    /**
     * Atualiza a password 
     * @param password 
     */
    public void setPassword(String password){
        if(eSenhaValida(password)){
            this.password = password;
        }
        else{
            throw new PasswordInvalidaException("A password não é válida!");
        }
    }
    
    /**
     * Devolve a password
     * @return 
     */
    public String getPasswordText(){
        return password;
    }
        
    /**
     * Verifica se a password e valida
     * @param password
     * @return 
     */
    private static boolean eSenhaValida(String password){
        if(password == null){
            return false;
        }
        if(password.length()<8){
            return false;
        }
        return true;
    }

    /**
     * Representacao textual da password
     * @return 
     */
    @Override
    public String toString() {
        return "Password{" +
                "password='" + password + '\'' +
                '}';
    }
}
