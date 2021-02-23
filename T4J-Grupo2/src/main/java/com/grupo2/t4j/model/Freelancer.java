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

/**
 *
 * @author carol
 */
public class Freelancer extends Utilizador implements Serializable{
    
    /**
     * O NIF do Freelancer
     */
    private String nif;
    
    /**
     * O telefone do Freelancer
     */
    private String telefone;
    
    /**
     * O endereço postal do Freelancer
     */
    private int codigoEnderecoPostal;

    /**
     * O construtor vazio da classe Freelancer
     */
    public Freelancer(){
        
    }
    
    /**
     * Construtor completo da classe Freelancer
     * @param email - o email do Freelancer no formato da classe Email
     * @param nome - o nome do Freelancer
     * @param password - a password do Freelancer no formato da classe Password
     * @param telefone - o telefone do Freelancer
     * @param nif - o NIF do Freelancer
     * @param codigoEnderecoPostal - o iD da classe EnderecoPostal
     */
    public Freelancer(Email email, String nome, Password password,
            String nif, String telefone, int codigoEnderecoPostal){
        super(email, nome, password);
        setNIF(nif);
        setTelefone(telefone);
        this.codigoEnderecoPostal = codigoEnderecoPostal;
    }    
       
    /**
     * Construtor da classe Freelancer que recebe como parâmetro outra instância 
     * da classe Freelancer
     * @param freelancer
     */
    public Freelancer(Freelancer freelancer){
        super(freelancer.getEmail(), freelancer.getNome(), freelancer.getPassword());
        setNIF(freelancer.nif);
        setTelefone(freelancer.telefone);
        this.codigoEnderecoPostal = freelancer.codigoEnderecoPostal;
    }
    
    /**
     * Define o NIF do Freelancer
     * @param nif
     * @throws NifInvalidoException
     */
    public void setNIF(String nif)throws NifInvalidoException {
        long numNif = Long.parseLong(nif);
        if (numNif >= 100000000 && numNif <= 999999999) {
            this.nif = nif;
        } else {
            throw new NifInvalidoException(nif + ": NIF inválido!");
        }
    }
    
    /**
     * Verifica a validade do parâmetro recebido e regista o telefone do Freelancer
     * @param telefone o telefone do Freelancer
     */
    public void setTelefone(String telefone) {
        int numero = Integer.parseInt(telefone);
        if (numero < 100000000 || numero > 999999999) {
            throw new IllegalArgumentException("Número é inválido!");
        }
        this.telefone = telefone;
    }
         
    /**
     * Devolve o NIF do Freelancer
     * @return NIF
     */
    public String getNif(){
        return nif;
    }
    
    /**
     * Devolve o telefone do Freelancer
     * @return telefone
     */
    public String getTelefone(){
        return telefone;
    }
    
    /**
     * Devolve o iD doendereço postal do Freelancer
     * @return codigoEnderecoPostal
     */
    public int getEnderecoFreelancer(){
        return codigoEnderecoPostal;
    }
    
    /**
     * Representação textual da classe Freelancer em formato de exibição
     * @return Nome, email e NIF do Freelancer
     */   
    @Override
    public String toString(){
        return String.format("Nome: %-20s |Email: %-20s |NIF: %-10s", super.getNome(), 
                super.getEmail().getEmailText(), nif);
    }
    
    /**
     * Representação textual da classe Freelancer
     * @return Nome, email, NIF, telefone e endereço postal do Freelancer
     */   
    
    public String toStringCompleta(){
        return String.format("Nome freelancer: %s  %nEmail: %s"
                + "%s %nNIF: %s %nTelefone: %s %nEndereço Postal: %d",
                super.getNome(), super.getEmail().getEmailText(), 
                nif, telefone, codigoEnderecoPostal);
    }   
 }
