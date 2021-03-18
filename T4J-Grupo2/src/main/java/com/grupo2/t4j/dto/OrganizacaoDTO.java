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
import com.grupo2.t4j.domain.Email;
import com.grupo2.t4j.domain.Website;
import com.grupo2.t4j.exception.NifInvalidoException;

import java.util.Objects;


public class OrganizacaoDTO {

    private String nome;
    private String nif;
    private int idEnderecoPostal;
    private Website website;
    private String telefone;
    private Email email;
    private Email emailGestor;

    public OrganizacaoDTO () {
    }

    public OrganizacaoDTO(String nif, String nome, Website websiteOrg,
                       String telefone, Email emailOrg, Email emailGestor, int idEnderecoPostal){
        this.nif = nif;
        this.nome = nome;
        if (websiteOrg instanceof Website) {
            this.website = websiteOrg;
        }
        else {
            this.website = new Website(Objects.requireNonNull(websiteOrg));
        }
        this.telefone = telefone;
        this.email = emailOrg;
        this.emailGestor = emailGestor;
        this.idEnderecoPostal = idEnderecoPostal;
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

    public Email getEmail() {
        return email;
    }

    public Email getEmailGestor() {
        return emailGestor;
    }
}