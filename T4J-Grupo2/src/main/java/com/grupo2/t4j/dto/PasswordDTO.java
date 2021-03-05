package com.grupo2.t4j.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.grupo2.t4j.exception.PasswordInvalidaException;

@JsonPropertyOrder({"password"})

public class PasswordDTO {

    @JacksonXmlProperty(localName = "password")
    private String password;

    public PasswordDTO (String password){
        setPassword(password);
    }

    public PasswordDTO (PasswordDTO password){
        setPassword(password.password);
    }

    public void setPassword(String password){
        if(eSenhaValida(password)){
            this.password = password;
        }
        else{
            throw new PasswordInvalidaException("A password não é válida!");
        }
    }

    public String getPasswordText(){
        return password;
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
