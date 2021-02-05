/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.grupo2.t4j.repository;
/**
 *
 * @author CAD
 */

import com.grupo2.t4j.model.*;
import com.grupo2.t4j.exception.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RepositorioUtilizador implements Serializable{

     /**
     * Define uma instância estática do Repositório em que estão registados todos
     * os Utilizadores da plataforma
     */
    private static RepositorioUtilizador repositorioUtilizador;
    
    /**
     * Define o atributo da classe RepositorioUtilizador como uma lista de
     * tipos da classe Utilizador
     */
    private List<Utilizador> listaUtilizadores;
    
    /**
     * Inicializa o Repositório de Utilizadores
     */
    private RepositorioUtilizador(){
        listaUtilizadores = new ArrayList<>();
    }
          
    /**
     * Adiciona um Utilizador à lista de Utilizadores 
     * @param utilizador do tipo da classe Utilizador
     * @throws UtilizadorDuplicadoException
     */
    public void addUtilizador(Utilizador utilizador) throws UtilizadorDuplicadoException {
        Utilizador u = getUtilizadorByEmail(utilizador.getEmail());
        if (u == null) {
            this.listaUtilizadores.add(utilizador);
        } else {
            throw new UtilizadorDuplicadoException(u.getEmail() + ": Utilizador já registado!");
        }
    }
    
    /**
     * Adiciona um Utilizador à lista de Utilizadores
     * @param nome o nome do Utilizador
     * @param email o email do Utilizador em formato da classe Email
     * @param password a password do Utilizador em formato da classe Password
     * @throws UtilizadorDuplicadoException
     */
    public void addUtilizador(String nome, Email email, Password password) throws UtilizadorDuplicadoException {
        Utilizador u = getUtilizadorByEmail(email);
        if (u == null) {
            Utilizador utilizador = new Utilizador(nome, email, password);
            this.listaUtilizadores.add(utilizador);
        } else {
            throw new UtilizadorDuplicadoException(u.getEmail() + ": Utilizador já registado!");
        }
    }
    
    /**
     * Adiciona um Utilizador à lista de Utilizadores
     * @param nome o nome do Utilizador
     * @param emailUt o email do Utilizador em formato String
     * @param password a password do Utilizador em formato da classe Password
     * @throws UtilizadorDuplicadoException
     */
    public void addUtilizador(String nome, String emailUt, Password password) throws UtilizadorDuplicadoException {
        Email email = new Email(emailUt);
        Utilizador u = getUtilizadorByEmail(email);
        if (u == null) {
            Utilizador utilizador = new Utilizador(nome, email, password);
            this.listaUtilizadores.add(utilizador);
        } else {
            throw new UtilizadorDuplicadoException(u.getEmail() + ": Utilizador já registado!");
        }
    }
    
    /**
     * Adiciona um Utilizador à lista de Utilizadores
     * @param nome o nome do Utilizador
     * @param emailUt o email do Utilizador em formato String
     * @param passUt a password do Utilizador em formato String
     * @throws UtilizadorDuplicadoException
     */
    public void addUtilizador(String nome, String emailUt, String passUt) throws UtilizadorDuplicadoException {
        Email email = new Email(emailUt);
        Utilizador u = getUtilizadorByEmail(email);
        if (u == null) {
            Utilizador utilizador = new Utilizador(nome, emailUt, passUt);
            this.listaUtilizadores.add(utilizador);
        } else {
            throw new UtilizadorDuplicadoException(u.getEmail() + ": Utilizador já registado!");
        }
    }
    
    /**
     * Devolve um Utilizador de acordo com o email registado
     * @param email o email do Utilizador
     * @return Utilizador registado
     */
    public Utilizador getUtilizadorByEmail(Email email) {
        Utilizador utilizador = null;
        for (int i = 0; i < this.listaUtilizadores.size(); i++) {
            utilizador = this.listaUtilizadores.get(i);
            if (utilizador.getEmail().equals(email)) {
                return utilizador;
            }
        }
        return null;
    }   
    
    /**
     * Devolve uma instância estática do Repositório de Utilizadores
     * @return RepositorioUtilizador
     */
    public static RepositorioUtilizador getInstance() {
        if(repositorioUtilizador == null) {
            repositorioUtilizador = new RepositorioUtilizador();
        }
        return repositorioUtilizador;
    }
    
    /**
     * Atualiza a lista de Utilizadores
     * @param listaUtilizadores
     */
    public void setListaUtilizadores(List<Utilizador> listaUtilizadores){
        this.listaUtilizadores = listaUtilizadores;
    }
    
    /**
     * Deevolve a lista de Utilizadores
     * @return
     */
    public ArrayList<Utilizador> getListaUtilizadores(){
        return new ArrayList<Utilizador>(listaUtilizadores);
    }
    
    /**
     * Informa se a lista de Utilizadores está ou não vazia
     * @return
     */
    public boolean isVazia() {
        return listaUtilizadores.isEmpty();
    }
}

