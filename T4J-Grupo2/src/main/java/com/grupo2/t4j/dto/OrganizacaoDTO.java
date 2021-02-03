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
import com.grupo2.t4j.exception.*;
import com.grupo2.t4j.model.*;

@JsonPropertyOrder({"nome", "NIF", "enderecoPostal", "website", "telefone", "email", "colaborador"})
@JacksonXmlRootElement(localName = "organizacao")

public class OrganizacaoDTO {
    
    @JacksonXmlProperty(localName = "nome")
    private String nome;

    @JacksonXmlProperty(localName = "NIF")
    private String NIF;
    
    @JacksonXmlProperty(localName = "enderecoPostal")
    private EnderecoPostal enderecoPostal;
    
    @JacksonXmlProperty(localName = "website")
    private Website website;
    
    @JacksonXmlProperty(localName = "telefone")
    private String telefone;
    
    @JacksonXmlProperty(localName = "email")
    private Email email;

    @JacksonXmlProperty(localName = "password")
    private Colaborador colaborador;


    public OrganizacaoDTO () {
    }
    
    public OrganizacaoDTO (String nome, String NIF, EnderecoPostal enderecoOrg, 
            Website websiteOrg, String telefone, Email emailOrg, Colaborador 
            colabGestor){
        setNome(nome);
        setNif(NIF);
        this.enderecoPostal = new EnderecoPostal(enderecoOrg);
        this.website = new Website(websiteOrg);
        setTelefone(telefone);
        this.email = new Email(emailOrg);
        this.colaborador = new Colaborador(colabGestor);
    }
    
    public final void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome é inválido!");
        }
        this.nome = nome;
    }
    
    public void setNif(String NIF) throws NifInvalidoException {
        long numNif = Long.parseLong(NIF);
        if (numNif >= 100000000 && numNif <= 999999999) {
            this.NIF = NIF;
        } else {
            throw new NifInvalidoException(NIF + ": NIF inválido!");
        }
    }
    
    public void setEnderecoPostal(EnderecoPostal enderecoOrg){
        this.enderecoPostal = enderecoOrg;
    }
    
    public void setWebsite(Website websiteOrg){
        this.website = websiteOrg;
    }
    
    public final void setTelefone(String telefone) {
        int numero = Integer.parseInt(telefone);
        if (numero < 100000000 || numero > 999999999) {
            throw new IllegalArgumentException("Número é inválido!");
        }
        this.telefone = telefone;
    }  
    
    
    public final void setEmail(Email emailOrg){
        this.email = emailOrg;
    }
    
}