package com.t4jws.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class EmailDTO {

    private String email;

    private String to;

    private String subject;

    private String text;

    public EmailDTO() {
    }

    public EmailDTO (String email){
        setEmail(email);
    }

    public EmailDTO (EmailDTO emailDTO){
       setEmail(emailDTO.email);
    }

    public void setEmail(String email){
        if(eEmailValido(email)){
            this.email = email;
        }
        else{
            throw new IllegalArgumentException("O email é inválido!");
        }
    }

    public void setTo(String to) {
        this.to = to;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setText(String text) {
        this.text = text;
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
