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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Email {
   
    private String email;
    
    public Email (String email){
        setEmail(email);
    }
    
    public Email (Email email){
        setEmail(email.email);
    }
    public void setEmail(String email){
        if(eEmailValido(email)){
            this.email = email;
        }
        else{
            throw new IllegalArgumentException("O email é inválido!");
        }
    }
    
    // https://receitasdecodigo.com.br/java/validar-email-em-java
    
    public static boolean eEmailValido(String email) {
        boolean eValido = false;
        if (email != null && email.length() > 0) {
            String expressao = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
            Pattern pattern = Pattern.compile(expressao, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(email);
            if (matcher.matches()) {
                eValido = true;
            }
        }
        return eValido;
    }
}
