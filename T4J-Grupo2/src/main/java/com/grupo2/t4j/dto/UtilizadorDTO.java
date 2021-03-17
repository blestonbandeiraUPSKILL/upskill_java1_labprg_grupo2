package com.grupo2.t4j.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.grupo2.t4j.domain.Email;
import com.grupo2.t4j.domain.Password;

public class UtilizadorDTO {

    private String nome;
    private Email email;
    private Password password;
    private String rolename;

    public UtilizadorDTO() {
    }

    public UtilizadorDTO(Email email, String nome, Password password) {
        this.email = email;
        this.nome = nome;
        this.password = password;
    }


    public UtilizadorDTO(Email email, String nome, Password password, String rolename) {
        this.email = email;
        this.nome = nome;
        this.password = password;
        this.rolename = rolename;
    }

    public String getNome() {
        return nome;
    }

    public Email getEmail() {
        return email;
    }

    public Password getPassword() {
        return password;
    }

    public String getRolename() {
        return rolename;
    }
}
