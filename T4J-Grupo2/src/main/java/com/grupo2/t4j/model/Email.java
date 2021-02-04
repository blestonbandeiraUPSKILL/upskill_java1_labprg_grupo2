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

public class Email implements IEmail {
   
    private String email;
    private String to;
    private String subject;
    private String text;

    public Email() {
    }
    public Email (String email){
        setEmail(email);
    }
    
    public Email (Email email){
        setEmail(email.email);
    }

    public String getTo() {
        return to;
    }

    public String getSubject() {
        return subject;
    }

    public String getText() {
        return text;
    }
    
     public String getEmailText() {
        return email;
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

    @Override
    public void setTo(String email) {
       setEmail(email);
    }

    @Override
    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public void setText(String text) {
        this.text = text;
    }

    @Override
    public void send() {
        System.out.println("Email enviado para " + getTo());
    }
}


