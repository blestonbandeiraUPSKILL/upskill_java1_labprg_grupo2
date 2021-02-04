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

public class RepositorioAdministrativo implements Serializable{
    
    /**
     * Define uma instância estática do Repositório em que estão registados todos
     * os Administradores da plataforma
     */
    private static RepositorioAdministrativo repositorioAdministrativo;
    
    /**
     * Define o atributo da classe RepositorioAdministrativo como uma lista de
     * tipos da classe Administrativo
     */
    private List<Administrativo> listaAdministradores;
    
    /**
     * Inicializa o Repositório de Administrativos
     */
    private RepositorioAdministrativo(){
        listaAdministradores = new ArrayList<>();
    }
          
    /**
     * Adiciona um Administrador à lista de Administradores 
     * @param administrativo do tipo da classe Administrativo
     * @throws AdministrativoDuplicadoException
     */
    public void addAdministrador(Administrativo administrativo) throws AdministrativoDuplicadoException {
        Administrativo a = getAdministrativoByEmail(administrativo.getEmail());
        if (a == null) {
            this.listaAdministradores.add(administrativo);
        } else {
            throw new AdministrativoDuplicadoException(a.getEmail() + ": Administrador já registado");
        }
    }
    
    /**
     * Adiciona um Administrador à lista de Administradores 
     * @param nome o nome do Administrador
     * @param email o email do Administrador em formato da classe Email
     * @param password a password do Administrador em formato da classe Password
     * @throws AdministrativoDuplicadoException
     */
    public void addAdministrador(String nome, Email email, Password password) throws AdministrativoDuplicadoException {
        Administrativo a = getAdministrativoByEmail(email);
        if (a == null) {
            Administrativo administrativo = new Administrativo(nome, email, password);
            this.listaAdministradores.add(administrativo);
        } else {
            throw new AdministrativoDuplicadoException(a.getEmail() + ": Administrador já registado");
        }
    }
    
    /**
     * Adiciona um Administrador à lista de Administradores 
     * @param nome o nome do Administrador
     * @param email  o email do Administrador em formato String
     * @param password a password do Administrador em formato da classe Password
     * @throws AdministrativoDuplicadoException
     */
    public void addAdministrador(String nome, String email, Password password) throws AdministrativoDuplicadoException {
        Email emailAd = new Email(email);
        Administrativo a = getAdministrativoByEmail(emailAd);
        if (a == null) {
            Administrativo administrativo = new Administrativo(nome, emailAd, password);
            this.listaAdministradores.add(administrativo);
        } else {
            throw new AdministrativoDuplicadoException(a.getEmail() + ": Administrador já registado");
        }
    }
    
    /**
     * Adiciona um Administrador à lista de Administradores 
     * @param nome o nome do Administrador
     * @param email  o email do Administrador em formato String
     * @param password a password do Administrador em formato String
     * @throws AdministrativoDuplicadoException
     */
    public void addAdministrador(String nome, String email, String password) throws AdministrativoDuplicadoException {
        Email emailAd = new Email(email);
        Administrativo a = getAdministrativoByEmail(emailAd);
        if (a == null) {
            Password passAdm = new Password(password);
            Administrativo administrativo = new Administrativo(nome, emailAd, passAdm);
            this.listaAdministradores.add(administrativo);
        } else {
            throw new AdministrativoDuplicadoException(a.getEmail() + ": Administrador já registado");
        }
    }
    
    /**
     * Atualiza a lista de Administrativos
     *
     * @param listaAdministradores
     */
    public void setListaAdministradores(List<Administrativo> listaAdministradores) {
        this.listaAdministradores = listaAdministradores;
    }

    /**
     * Devolve a lista de Administrativos
     *
     * @return 
     */
    public ArrayList<Administrativo> getListaAdministradores() {

        return new ArrayList<Administrativo>(listaAdministradores);
    }
    
    /**
     * Devolve um Administrativo de acordo com o email registado
     * @param email o email do Administrador
     * @return Administrativo registado
     */
    private Administrativo getAdministrativoByEmail(Email email) {
        Administrativo administrativo = null;
        for (int i = 0; i < this.listaAdministradores.size(); i++) {
            administrativo = this.listaAdministradores.get(i);
            if (administrativo.getEmail().equals(email)) {
                Administrativo copia = new Administrativo(administrativo);
                return copia;
            }
        }
        return null;
    }   
    
    /**
     * Devolve uma instância estática do Repositório de Administrativos
     * @return RepositorioAdministrativo
     */
    public static RepositorioAdministrativo getInstance() {
        if(repositorioAdministrativo == null) {
            repositorioAdministrativo = new RepositorioAdministrativo();
        }
        return repositorioAdministrativo;
    }
    
    /**
     * Informa se a lista de Administradores está ou não vazia
     * @return 
     */
    public boolean isVazia() {
        return listaAdministradores.isEmpty();
    }
}
