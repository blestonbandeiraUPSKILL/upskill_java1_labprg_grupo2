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
     * os Administrativos da plataforma
     */
    private static RepositorioAdministrativo repositorioAdministrativo;
    
    /**
     * Define o atributo da classe RepositorioAdministrativo como uma lista de
     * tipos da classe Administrativo
     */
    private List<Administrativo> listaAdministrativos;
    
    /**
     * Inicializa o Repositório de Administrativos
     */
    private RepositorioAdministrativo(){
        listaAdministrativos = new ArrayList<>();
    }
          
    /**
     * Adiciona um Administrativo à lista de Administrativos
     * @param administrativo do tipo da classe Administrativo
     * @throws AdministrativoDuplicadoException
     */
    public void addAdministrativo(Administrativo administrativo) throws AdministrativoDuplicadoException {
        Administrativo a = getAdministrativoByEmail(administrativo.getEmail());
        if (a == null) {
            this.listaAdministrativos.add(administrativo);
        } else {
            throw new AdministrativoDuplicadoException(a.getEmail().getEmailText() + 
                    ": Administrador já registado");
        }
    }
    
    /**
     * Adiciona um Administrativo à lista de Administradores 
     * @param nome o nome do Administrativo
     * @param email o email do Administrativo em formato da classe Email
     * @param password a password do Administrativo em formato da classe Password
     * @throws AdministrativoDuplicadoException
     */
    public void addAdministrativo(String nome, Email email, Password password) throws AdministrativoDuplicadoException {
        Administrativo a = getAdministrativoByEmail(email);
        if (a == null) {
            Administrativo administrativo = new Administrativo(nome, email, password);
            this.listaAdministrativos.add(administrativo);
        } else {
            throw new AdministrativoDuplicadoException(a.getEmail().getEmailText() + 
                    ": Administrador já registado");
        }
    }
    
    /**
     * Adiciona um Administrativo à lista de Administrativos
     * @param nome o nome do Administrativo
     * @param email  o email do Administrativo em formato String
     * @param password a password do Administrativo em formato da classe Password
     * @throws AdministrativoDuplicadoException
     */
    public void addAdministrativo(String nome, String email, Password password) throws AdministrativoDuplicadoException {
        Email emailAd = new Email(email);
        Administrativo a = getAdministrativoByEmail(emailAd);
        if (a == null) {
            Administrativo administrativo = new Administrativo(nome, emailAd, password);
            this.listaAdministrativos.add(administrativo);
        } else {
            throw new AdministrativoDuplicadoException(a.getEmail().getEmailText() 
                    + ": Administrador já registado");
        }
    }
    
    /**
     * Adiciona um Administrativo à lista de Administrativos
     * @param nome o nome do Administrativo
     * @param email  o email do Administrativo em formato String
     * @param password a password do Administrativo em formato String
     * @throws AdministrativoDuplicadoException
     */
    public void addAdministrativo(String nome, String email, String password) throws AdministrativoDuplicadoException {
        Email emailAd = new Email(email);
        Administrativo a = getAdministrativoByEmail(emailAd);
        if (a == null) {
            Password passAdm = new Password(password);
            Administrativo administrativo = new Administrativo(nome, emailAd, passAdm);
            this.listaAdministrativos.add(administrativo);
        } else {
            throw new AdministrativoDuplicadoException(a.getEmail().getEmailText() 
                    + ": Administrador já registado");
        }
    }
    
    /**
     * Atualiza a lista de Administrativos
     *
     * @param listaAdministrativos
     */
    public void setListaAdministrativos(List<Administrativo> listaAdministrativos) {
        this.listaAdministrativos = listaAdministrativos;
    }

    /**
     * Devolve a lista de Administrativos
     *
     * @return 
     */
    public ArrayList<Administrativo> getListaAdministrativos() {

        return new ArrayList<Administrativo>(listaAdministrativos);
    }
    
    /**
     * Devolve um Administrativo de acordo com o email registado
     * @param email o email do Administrativo
     * @return Administrativo registado
     */
    public Administrativo getAdministrativoByEmail(Email email) {
        Administrativo administrativo = null;
        for (int i = 0; i < this.listaAdministrativos.size(); i++) {
            administrativo = this.listaAdministrativos.get(i);
            if (administrativo.getEmail().equals(email)) {
                return administrativo;
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
     * Informa se a lista de Administrativos está ou não vazia
     * @return 
     */
    public boolean isVazia() {
        return listaAdministrativos.isEmpty();
    }
}
