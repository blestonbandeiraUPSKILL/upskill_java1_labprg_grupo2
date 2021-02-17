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

public class Freelancer extends Utilizador implements Serializable{
    
    /**
     * O NIF do Freelancer
     */
    private String NIF;
    
    /**
     * O telefone do Freelancer
     */
    private String telefone;
    
    /**
     * O endereço postal do Freelancer
     */
    private EnderecoPostal enderecoPostalFreelancer;
    
    /**
     * O papel do Freelancer dentro da plataforma
     */
    private Rolename rolename;
    
    /**
     * Por definição, todos da classe Freelancer tem rolename Freelancer.
     */
    private static final Rolename ROLENAME_POR_DEFINICAO = Rolename.FREELANCER;
    
    public Freelancer(Email email, String nome, Password password, Rolename rolename,
            String NIF, EnderecoPostal enderecoPostalFreelancer){
        super(email, nome, password, ROLENAME_POR_DEFINICAO);
        setNIF(NIF);
        setTelefone(telefone);
        setEndereco(enderecoPostalFreelancer);        
    }
    
    public Freelancer(String emailFree, String nome, Rolename rolename,
            String NIF, EnderecoPostal enderecoPostalFreelancer){
        super(emailFree, nome);
        super.setRolename(ROLENAME_POR_DEFINICAO);
        setNIF(NIF);
        setTelefone(telefone);
        setEndereco(enderecoPostalFreelancer);        
    }
    
     public Freelancer(String emailFree, String nome, Rolename rolename,
            String NIF, String arruamento, String numeroPorta, String localidade, 
            String codigoPostal){
        super(emailFree, nome);
        super.setRolename(ROLENAME_POR_DEFINICAO);
        setNIF(NIF);
        setTelefone(telefone);
        EnderecoPostal enderecoPostalFree = new EnderecoPostal(arruamento, numeroPorta,
        localidade, codigoPostal);
        setEndereco(enderecoPostalFree);        
    }
     
     public Freelancer(Freelancer freelancer){
        super(freelancer.getEmail(), freelancer.getNome(), freelancer.getPassword(), 
                freelancer.getRolename());
        setNIF(freelancer.NIF);
        setTelefone(freelancer.telefone);
        setEndereco(freelancer.enderecoPostalFreelancer);        
    }
    
    
    public void setNIF(String NIF)throws NifInvalidoException {
        long numNif = Long.parseLong(NIF);
        if (numNif >= 100000000 && numNif <= 999999999) {
            this.NIF = NIF;
        } else {
            throw new NifInvalidoException(NIF + ": NIF inválido!");
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
     * @param enderecoPostalFreelancer
     */
    public void setEndereco(EnderecoPostal enderecoPostalFreelancer){
        this.enderecoPostalFreelancer = enderecoPostalFreelancer;
    }
    
    public String getNIF(){
        return NIF;
    }
    
    public String getTelefone(){
        return telefone;
    }
    
    public EnderecoPostal getEnderecoFreelancer(){
        return enderecoPostalFreelancer;
    }
    
    /**
     * Representação textual da classe Freelancer
     * @return Nome, email, password, NIF, telefone e endereço postal do Freelancer
     */   
    @Override
    public String toString(){
        return String.format("Nome freelancer: %s  %nEmail: %s"
                + "%nPassword: %s %nNIF: %s %nTelefone: %s %nEndereço Postal: %s",
                super.getNome(), super.getEmail().getEmailText(), 
                super.getPassword().getPasswordText(), NIF, telefone, enderecoPostalFreelancer.toString());
    }
    
    /**
     * Representação textual da classe Freelancer sem a password
     * @return Nome, email, NIF, telefone e endereço postal do Freelancer
     */
    public String toStringSemPass(){
        return String.format("Nome freelancer: %s  %nEmail: %s"
                + "%nNIF: %s %nTelefone: %s %nEndereço Postal: %s",
                super.getNome(), super.getEmail().getEmailText(), 
                NIF, telefone, enderecoPostalFreelancer.toString());
    }
}
