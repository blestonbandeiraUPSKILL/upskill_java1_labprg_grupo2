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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Email implements IEmail {
   
    /**
     * O email
     */
    private String email;
    
    /**
     * O destinatario
     */
    private String to;
    
    /**
     * O assunto do email
     */
    private String subject;
    
    /**
     * O corpo do email
     */
    private String text;

    /**
     * Construtor vazio do email
     */
    public Email() {
    }
    
    /**
     * Construtor email String
     * @param email 
     */
    public Email (String email){
        setEmail(email);
    }
    
    /**
     * Construtor email
     * @param email 
     */
    public Email (Email email){
        setEmail(email.email);
    }

    /**
     * Devolve o destinatario do email
     * @return 
     */
    public String getTo() {
        return to;
    }

    /**
     * Devolve o assunto do email
     * @return 
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Devolve o corpo do email
     * @return 
     */
    public String getText() {
        return text;
    }
    
    /**
     * Devolve o email em formato texto
     * @return 
     */
     public String getEmailText() {
        return email;
    }
     
     /**
      * Actualiza o email
      * @param email 
      */
    public void setEmail(String email){
       // if(eEmailValido(email)){
            this.email = email;
        /*}
        else{
            throw new IllegalArgumentException("O email é inválido!");
        }*/
    }
    
    // https://receitasdecodigo.com.br/java/validar-email-em-java
    /**
     * Valida o email
     * @param email
     * @return 
     */
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

    /**
     * Actualiza o destinatario
     * @param email 
     */
    @Override
    public void setTo(String email) {
       setEmail(email);
    }

    /**
     * Actualiza o assunto do email
     * @param subject 
     */
    @Override
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * Actualiza o corpo do email
     * @param text 
     */
    @Override
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Envia o email
     */
    @Override
    public void send() {
        System.out.println("Email enviado para " + getTo());
    }

    @Override
    public String toString() {
        return email;
    }
}


