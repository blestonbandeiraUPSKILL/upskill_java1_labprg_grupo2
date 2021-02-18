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
import java.util.Calendar;
import java.util.List;

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
    private String codigoEnderecoPostal;

    
    /**
     * Construtor completo da classe Freelancer
     * @param email - o email do Freelancer no formato da classe Email
     * @param nome - o nome do Freelancer
     * @param password - a password do Freelancer no formato da classe Password
     * @param nif - o NIF do Freelancer
     *
     * da classe EnderecoPostal
     */
    public Freelancer(Email email, String nome, Password password,
            String nif, String codigoEnderecoPostal){
        super(email, nome, password);
        setNIF(nif);
        setTelefone(telefone);
        setEndereco(codigoEnderecoPostal);
    }
    
    /**
     * Construtor da classe Freelancer sem a password 
     * @param email - o email do Freelancer no formato de texto
     * @param nome - o nome do Freelancer
     * @param nif - o NIF do Freelancer
     * @param codigoEnderecoPostal - o endereço postal do Freelancer no formato
     * da classe EnderecoPostal
     */
    public Freelancer(Email email, String nome, Password password, String nif, String telefone, String codigoEnderecoPostal){
        super(email, nome, password);
        setNIF(nif);
        setTelefone(telefone);
        setEndereco(codigoEnderecoPostal);
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
        setEndereco(freelancer.codigoEnderecoPostal);
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
     * Define o endereço postal do Freelancer
     * @param codigoEnderecoPostal
     */
    public void setEndereco(String codigoEnderecoPostal){
        if (codigoEnderecoPostal == null || codigoEnderecoPostal.trim().isEmpty()) {
            throw new CodigoInvalidoException("O código do Endereço Postal é inválido.");
        }
        this.codigoEnderecoPostal = codigoEnderecoPostal;
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
     * Devolve o endereço postal do Freelancer
     * @return enderecoPostalFreelancer
     */
    public String getEnderecoFreelancer(){
        return codigoEnderecoPostal;
    }
    
    /**
     * Representação textual da classe Freelancer
     * @return Nome, email, password, NIF, telefone e endereço postal do Freelancer
     */   
    @Override
    public String toString(){
        return String.format("Nome freelancer: %s  %nEmail: %s"
                + "%s %nNIF: %s %nTelefone: %s %nEndereço Postal: %s",
                super.getNome(), super.getEmail().getEmailText(), 
                nif, telefone, codigoEnderecoPostal.toString());
    }

}
