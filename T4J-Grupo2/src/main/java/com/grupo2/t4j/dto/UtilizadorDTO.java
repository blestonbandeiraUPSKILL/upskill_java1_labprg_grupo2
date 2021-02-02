package com.grupo2.t4j.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.grupo2.t4j.model.Email;
import com.grupo2.t4j.model.Password;

@JsonPropertyOrder({"nome", "email", "password"})
@JacksonXmlRootElement(localName = "utilizador")

public class UtilizadorDTO {

    @JacksonXmlProperty(localName = "nome")
    private String nome;

    @JacksonXmlProperty(localName = "email")
    private Email email;

    @JacksonXmlProperty(localName = "password")
    private Password password;

    public UtilizadorDTO() {
    }

    public UtilizadorDTO(String nome, Email email, Password password) {
        this.nome = nome;
        setEmail(email);
        setPassword(password);
    }

    public final void setNome(String nome){
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome é inválido!");
        }
        this.nome = nome;
    }

    public final void setEmail(Email email){
        this.email = email;
    }

    public final void setPassword(Password password){
        this.password = password;
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
}
