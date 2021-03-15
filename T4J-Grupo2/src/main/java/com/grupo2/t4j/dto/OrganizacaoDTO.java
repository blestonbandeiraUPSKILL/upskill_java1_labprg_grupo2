/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.dto;

/**
 *
 * @author Geral
 */

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.grupo2.t4j.domain.Website;
import com.grupo2.t4j.exception.NifInvalidoException;

import java.util.Objects;

@JsonPropertyOrder({"nome", "nif",
        "idEnderecoPostal", "website", "telefone", "email", "emailGestor"})
@JacksonXmlRootElement(localName = "organizacao")

public class OrganizacaoDTO {
    
    @JacksonXmlProperty(localName = "nome")
    private String nome;

    @JacksonXmlProperty(localName = "nif")
    private String nif;
    
    @JacksonXmlProperty(localName = "idEnderecoPostal")
    private int idEnderecoPostal;
    
    @JacksonXmlProperty(localName = "website")
    private Website website;
    
    @JacksonXmlProperty(localName = "telefone")
    private String telefone;
    
    @JacksonXmlProperty(localName = "email")
    private EmailDTO email;

    @JacksonXmlProperty(localName = "emailGestor")
    private EmailDTO emailGestor;


    public OrganizacaoDTO () {
    }

    public OrganizacaoDTO(String nif, String nome, Website websiteOrg,
                       String telefone, EmailDTO emailOrg, EmailDTO emailGestor, int idEnderecoPostal){
        setNif(nif);
        setNome(nome);
        if (websiteOrg instanceof Website) {
            this.website = websiteOrg;
        }
        else {
            this.website = new Website(Objects.requireNonNull(websiteOrg));
        }
        setTelefone(telefone);
        this.email = emailOrg;
        this.emailGestor = emailGestor;
        this.idEnderecoPostal = idEnderecoPostal;
    }
    
    public final void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome é inválido!");
        }
        this.nome = nome;
    }
    
    public void setNif(String nif) throws NifInvalidoException {
        long numNif = Long.parseLong(nif);
        if (numNif >= 100000000 && numNif <= 999999999) {
            this.nif = nif;
        } else {
            throw new NifInvalidoException(nif + ": NIF inválido!");
        }
    }
    
    public void setEnderecoPostal(int idEnderecoPostal){
        this.idEnderecoPostal = idEnderecoPostal;
    }
    
    public void setWebsite(Website website){
        this.website = website;
    }
    
    public final void setTelefone(String telefone) {
        int numero = Integer.parseInt(telefone);
        if (numero < 100000000 || numero > 999999999) {
            throw new IllegalArgumentException("Número é inválido!");
        }
        this.telefone = telefone;
    }
    
    public final void setEmail(EmailDTO emailOrg){
        this.email = emailOrg;
    }

    public String getNome() {
        return nome;
    }

    public String getNif() {
        return nif;
    }

    public int getIdEnderecoPostal() {
        return idEnderecoPostal;
    }

    public Website getWebsite() {
        return website;
    }

    public String getTelefone() {
        return telefone;
    }

    public EmailDTO getEmail() {
        return email;
    }

    public EmailDTO getEmailGestor() {
        return emailGestor;
    }
}