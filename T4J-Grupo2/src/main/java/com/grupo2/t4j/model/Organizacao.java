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
     * O endereço postal da organização
     */
    private EnderecoPostal enderecoOrg;
    
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
    private Colaborador colabGestor;
    
    /**
     * O endereço da organização por omissão
     */
    private static final EnderecoPostal ENDERECO_POR_OMISSAO = new EnderecoPostal("Rua ", "s/n", "Portugal", "1111-111");
    
    /**
     * O telefone da organização por omissão
     */
    private static final String TELEFONE_ORG_POR_OMISSAO = "999999999";
    
    /**
     * O construtor vazio da classeorganização
     */
    public Organizacao(){
    }
    
    /**
     * O construtor completo da classe Organização
     * @param nome o nome da organização 
     * @param NIF o NIF da organização
     * @param enderecoOrg o endereço postal da organização
     * @param websiteOrg o website da organização
     * @param telefone o telefone da organização
     * @param emailOrg o email da organização
     * @param colabGestor o gestor da organização
     */
    public Organizacao(String nome, String NIF, EnderecoPostal enderecoOrg,
                       Website websiteOrg, String telefone, Email emailOrg, Colaborador
                               colabGestor){
        if (websiteOrg instanceof Website) {
            this.websiteOrg = websiteOrg;
        }
        else {
            this.websiteOrg = new Website(Objects.requireNonNull(websiteOrg));
        }
        setNome(nome);
        setNif(NIF);
        this.enderecoOrg = new EnderecoPostal(enderecoOrg);
        setTelefone(telefone);
        this.emailOrg = new Email(emailOrg);
        this.colabGestor = new Colaborador(colabGestor);
    }

    /**
     * O construtor completo da classe Organização com os parâmetros do endereço postal
     * @param nome o nome da organização 
     * @param NIF o NIF da organização
     * @param arruamento o arruamento do endereço postal
     * @param numeroPorta o número da porta do endereço postal
     * @param localidade a localidade do endereço postal
     * @param codigoPostal o código postal
     * @param websiteOrg o website da organização 
     * @param telefone o telefone da organização
     * @param emailOrg o email da organização
     * @param colabGestor o gestor da organização
     */
    public Organizacao(String nome, String NIF, String arruamento, String numeroPorta,
                       String localidade, String codigoPostal, Website websiteOrg, String telefone,
                       Email emailOrg, Colaborador colabGestor){
        setNome(nome);
        setNif(NIF);
        this.enderecoOrg = new EnderecoPostal(arruamento, numeroPorta, localidade, codigoPostal);
        if (websiteOrg instanceof Website) {
            this.websiteOrg = websiteOrg;
        }
        else {
            this.websiteOrg = new Website(websiteOrg);
        }
        setTelefone(telefone);
        this.emailOrg = new Email(emailOrg);
        this.colabGestor = new Colaborador(colabGestor);
    }

    /**
     * O construtor completo da classe Organização com os parâmetros do endereço postal
     * e o email e o website da organização em formato String
     * @param nome o nome da organização 
     * @param NIF o NIF da organização
     * @param arruamento o arruamento do endereço postal
     * @param numeroPorta o número da porta do endereço postal
     * @param localidade a localidade do endereço postal
     * @param codigoPostal o código postal
     * @param website o website da organização em formato String 
     * @param telefone o telefone da organização
     * @param email o email da organização em formato String
     * @param colabGestor o gestor da organização
     */
    public Organizacao(String nome, String NIF, String arruamento, String numeroPorta,
                       String localidade, String codigoPostal, String website, String telefone,
                       String email, Colaborador colabGestor){
        setNome(nome);
        setNif(NIF);
        this.enderecoOrg = new EnderecoPostal(arruamento, numeroPorta, localidade, codigoPostal);
        if (websiteOrg instanceof Website) {
            this.websiteOrg = websiteOrg;
        }
        else {
            this.websiteOrg = new Website(websiteOrg);
        }
        setTelefone(telefone);
        this.emailOrg = new Email(email);
        this.colabGestor = new Colaborador(colabGestor);
    }

    /**
     * O construtor completo da classe Organização com os parâmetros do endereço postal
     * e o email e o website da organização em formato String. Também seguem cada um dos 
     * parâmetros de construção do colaborador gestor, com o rolename de GESTOR pré-definido
     * @param nome o nome da organização 
     * @param NIF o NIF da organização
     * @param arruamento o arruamento do endereço postal
     * @param numeroPorta o número da porta do endereço postal
     * @param localidade a localidade do endereço postal
     * @param codigoPostal o código postal
     * @param website o website da organização em formato String 
     * @param telefone o telefone da organização
     * @param email o email da organização em formato String
     * @param nomeColab o nome do colaborador gestor
     * @param emailColab o email do colaborador gestor
     * @param passColab a password do colaborador gestor
     * @param funcao a função do colaborador gestor
     * @param telefoneColab o telefone do colaborador gestor
     */
    public Organizacao(String nome, String NIF, String arruamento, String numeroPorta,
                       String localidade, String codigoPostal, String website, String telefone,
                       String email, String nomeColab, Email emailColab, Password passColab,
                       String funcao, String telefoneColab){
        setNome(nome);
        setNif(NIF);
        this.enderecoOrg = new EnderecoPostal(arruamento, numeroPorta, localidade, codigoPostal);
        this.websiteOrg = new Website(website);
        setTelefone(telefone);
        this.emailOrg = new Email(email);
        this.colabGestor = new Colaborador(nomeColab, emailColab, passColab, 
                funcao, telefoneColab, Rolename.GESTOR);
    }
    
    /**
     * O construtor completo da classe Organização com os parâmetros do endereço postal
     * e o email e o website da organização em formato String. Também seguem cada um dos 
     * parâmetros de construção do colaborador gestor, com o rolename de GESTOR pré-definido.
     * O colaborador gestor vai com password por omissão.
     * @param nome o nome da organização 
     * @param NIF o NIF da organização
     * @param arruamento o arruamento do endereço postal
     * @param numeroPorta o número da porta do endereço postal
     * @param localidade a localidade do endereço postal
     * @param codigoPostal o código postal
     * @param website o website da organização em formato String 
     * @param telefone o telefone da organização
     * @param email o email da organização em formato String
     * @param nomeColab o nome do colaborador gestor
     * @param emailColab o email do colaborador gestor
     * @param funcao a função do colaborador gestor
     * @param telefoneColab o telefone do colaborador gestor
     */
    public Organizacao(String nome, String NIF, String arruamento, String numeroPorta,
                       String localidade, String codigoPostal, String website, String telefone,
                       String email, String nomeColab, String emailColab,
                       String funcao, String telefoneColab){
        setNome(nome);
        setNif(NIF);
        this.enderecoOrg = new EnderecoPostal(arruamento, numeroPorta, localidade, codigoPostal);
        this.websiteOrg = new Website(website);
        setTelefone(telefone);
        this.emailOrg = new Email(email);
        this.colabGestor = new Colaborador(nomeColab, new Email(emailColab),
                funcao, telefoneColab, Rolename.GESTOR);
    }
       
    /**
     * O construtor da classe Organização que recebe como parâmetro outra organização
     * @param organizacao
     */
    public Organizacao(Organizacao organizacao){
        setNome(organizacao.getNome());
        setNif(organizacao.getNif());
        this.enderecoOrg = new EnderecoPostal(organizacao.getEnderecoPostal());
        this.websiteOrg = new Website(organizacao.getWebsite());
        setTelefone(organizacao.getTelefone());
        this.emailOrg = new Email(organizacao.getEmail());
        this.colabGestor = new Colaborador(organizacao.getColabGestor());
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
     * @param enderecoOrg
     */
    public void setEnderecoPostal(EnderecoPostal enderecoOrg){
        this.enderecoOrg = enderecoOrg;
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
     * Devolve o endereço postal da organização
     * @return endereco
     */
    public EnderecoPostal getEnderecoPostal(){
        return enderecoOrg;
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

    /**
     * Devolve o colaborador gestor da organização
     * @return
     */
    public Colaborador getColabGestor() {
        return colabGestor;
    }
      
    ////////////////////////////////////////////////////
    //  Verificar se precisa desses construtores abaixo e se precisamos ter setColabGestor
    //  Se sim, devemos colocar setColaborador em cada construtor ao invés de new, para
    //  termos coerência.
    
    /**
     *
     * @param nomeOrganizacao
     * @param nif
     * @param telefone
     * @param website
     * @param email
     * @param enderecoPostal
     */
    public Organizacao(String nomeOrganizacao, String nif, String telefone,
                       Website website, Email email, EnderecoPostal enderecoPostal) {
        setNome(nomeOrganizacao);
        setNif(nif);
        setTelefone(telefone);
        setWebsite(website);
        setEmail(new Email(email));
        setEnderecoPostal(enderecoPostal);
    }
    
     /**
     *
     * @param arruamento
     * @param numeroPorta
     * @param localidade
     * @param codigoPostal
     * @return
     */
    public static EnderecoPostal novoEndereco(String arruamento, String numeroPorta,
                                              String localidade, String codigoPostal) {
        return new EnderecoPostal(arruamento, numeroPorta, localidade, codigoPostal);
    }
    
    /*public Organizacao(String nome, String NIF, String arruamento, String numeroPorta,
                       String localidade, String codigoPostal, String website, String telefone,
                       String email, String nomeColab, String emailColab, Password password,
                       String funcao, String telefoneColab){
        setNome(nome);
        setNif(NIF);
        this.enderecoOrg = new EnderecoPostal(arruamento, numeroPorta, localidade, codigoPostal);
        //this.websiteOrg = new Website(website);
        if (websiteOrg instanceof Website) {
            this.websiteOrg = websiteOrg;
        }
        else {
            this.websiteOrg = new Website(websiteOrg);
        }
        setTelefone(telefone);
        this.emailOrg = new Email(email);
        Password passCol = new Password()
        this.colabGestor = new Colaborador(nomeColab, emailColab, password, funcao, telefoneColab);
    }*/
    
    /**
     *
     * @param nomeGestor
     * @param emailGestor
     * @param telefoneGestor
     * @param rolename
     * @return
     */
    public static Colaborador novoColaborador(String nomeGestor, Email emailGestor, String telefoneGestor, Rolename rolename) {
        String funcao = "Gestor";
        return new Colaborador(nomeGestor, emailGestor, funcao, telefoneGestor, rolename);
    }
    
    private void setColabGestor(String nomeGestor, String emailGestor, String telefoneGestor, Rolename role) {
        String funcao = "Gestor";
        colabGestor = new Colaborador(nomeGestor, new Email(emailGestor), funcao, telefoneGestor, role.GESTOR);
    }
    
    /**
     *
     * @param nomeOrganizacao
     * @param nif
     * @param arruamento
     * @param numeroPorta
     * @param localidade
     * @param codPostal
     * @param telefoneOrg
     * @param website
     * @param emailOrg
     * @param nomeGestor
     * @param emailGestor
     * @param telefoneGestor
     * @param role
     */
    public Organizacao(String nomeOrganizacao, 
                       String nif, String arruamento, 
                       String numeroPorta, String localidade, 
                       String codPostal, String telefoneOrg, 
                       String website, String emailOrg, 
                       String nomeGestor, String emailGestor, 
                       String telefoneGestor, Rolename role) {
        setNome(nomeOrganizacao);
        setNif(nif);
        setEnderecoPostal(new EnderecoPostal(arruamento, numeroPorta, localidade, codPostal));
        setTelefone(telefoneOrg);
        setWebsite(new Website(website));
        setEmail(new Email(emailOrg));
        setColabGestor(nomeGestor, emailGestor, telefoneGestor, role);
    }
    
    /**
     *
     * @return
     */
    public String getNomeGestor() {
        return colabGestor.getNome();
    }

    /**
     *
     * @return
     */
    public String getTelefoneGestor() {
        return colabGestor.getTelefone();
    }

    /**
     * 
     * @return
     */
    public Email getEmailGestor() {
        return colabGestor.getEmail();
    }
}
