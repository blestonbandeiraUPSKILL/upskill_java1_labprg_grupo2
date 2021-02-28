package com.grupo2.t4j.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.grupo2.t4j.model.Email;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@JsonPropertyOrder({"email", "to", "subject", "text"})

public class EmailDTO {

    @JacksonXmlProperty(localName = "email")
    private String email;

    @JacksonXmlProperty(localName = "to")
    private String to;

    @JacksonXmlProperty(localName = "subject")
    private String subject;

    @JacksonXmlProperty(localName = "text")
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
