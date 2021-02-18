/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.model;

/**
 *
 * @author CAD
 */

import com.grupo2.t4j.exception.*;
import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Geral
 */
public class Organizacao implements Serializable{
    
    /**
     * O nome da organização
     */
    private String nomeOrg;
    
    /**
     * O NIF da organização
     */
    private String NIF;
    
    /**
     * O id do endereço postal da organização
     */
    private String idEnderecoOrg;
    
    /**
     * O website da organização
     */
    private Website websiteOrg;
    
    /**
     * O telefone da organização
     */
    private String telefone;
    
    /**
     * O email da organização
     */
    private Email emailOrg;
    
    /**
     * O gestor da organização
     */
    private Email emailGestor;
    
    /**
     * O construtor vazio da classeorganização
     */
    public Organizacao(){
    }

    public Organizacao(String nif, String nome, Website websiteOrg,
                       String telefone, Email emailOrg, Email emailGestor, String idEnderecoPostal){
        setNif(nif);
        setNome(nome);
        if (websiteOrg instanceof Website) {
            this.websiteOrg = websiteOrg;
        }
        else {
            this.websiteOrg = new Website(Objects.requireNonNull(websiteOrg));
        }
        setTelefone(telefone);
        this.emailOrg = new Email(emailOrg);
        this.emailGestor = emailGestor;
        this.idEnderecoOrg = idEnderecoPostal;
    }

    /**
     * Define o nome da organização
     * @param nome
     */
    public final void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome é inválido!");
        }
        this.nomeOrg = nome;
    }

    /**
     * Define o NIF da organização
     * @param NIF
     * @throws NifInvalidoException
     */
    public void setNif(String NIF) throws NifInvalidoException {
        long numNif = Long.parseLong(NIF);
        if (numNif >= 100000000 && numNif <= 999999999) {
            this.NIF = NIF;
        } else {
            throw new NifInvalidoException(NIF + ": NIF inválido!");
        }
    }

    /**
     * Define o endereço postal da organização
     * @param idEnderecoOrg
     */
    public void setEnderecoPostal(String idEnderecoOrg){
        this.idEnderecoOrg = idEnderecoOrg;
    }

    /**
     * Define o website da organização
     * @param websiteOrg
     */
    public void setWebsite(Website websiteOrg){
        this.websiteOrg = websiteOrg;
    }

    /**
     * Define o telefone da organização
     * @param telefone
     */
    public final void setTelefone(String telefone) {
        int numero = Integer.parseInt(telefone);
        if (numero < 100000000 || numero > 999999999) {
            throw new IllegalArgumentException("Número é inválido!");
        }
        this.telefone = telefone;
    }

    /**
     * Define o email da organização
     * @param emailOrg
     */
    public final void setEmail(Email emailOrg){
        this.emailOrg = emailOrg;
    }

    /**
     * Devolve o nome da organização
     * @return nomeOrg
     */
    public String getNome(){
        return nomeOrg;
    }

    /**
     * Devolve o NIF da organização
     * @return NIF
     */
    public String getNif(){
        return NIF;
    }

    /**
     * Devolve o id do endereço postal da organização
     * @return idEnderecoOrg
     */
    public String getIdEnderecoPostal(){
        return idEnderecoOrg;
    }

    /**
     * Devolve o website da organização
     * @return
     */
    public Website getWebsite(){
        return websiteOrg;
    }

    /**
     * Devolve o telefone da organização
     * @return
     */
    public String getTelefone(){
        return telefone;
    }

    /**
     * Devolve o email da organização
     * @return
     */
    public Email getEmail(){
        return new Email(emailOrg);
    }

    public Email getEmailGestor() {
        return emailGestor;
    }
}
