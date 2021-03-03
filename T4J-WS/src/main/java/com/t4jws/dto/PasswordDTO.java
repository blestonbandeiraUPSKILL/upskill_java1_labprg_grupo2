package com.t4jws.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.t4jws.exception.PasswordInvalidaException;


@JsonPropertyOrder({"password"})

public class PasswordDTO {

    @JsonProperty("password")
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
