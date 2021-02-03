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
import java.util.ArrayList;
import java.util.List;

public class RepositorioAdministrativo {
    
    private static RepositorioAdministrativo repositorioAdministrativo;
    
    private List<Administrativo> listaAdministradores;
    
    private RepositorioAdministrativo(){
        listaAdministradores = new ArrayList<>();
    }
          
    public void addAdministrador(Administrativo administrativo) throws AdministrativoDuplicadoException {
        Administrativo a = getAdministrativoByEmail(administrativo.getEmail());
        if (a == null) {
            this.listaAdministradores.add(administrativo);
        } else {
            throw new AdministrativoDuplicadoException(a.getEmail() + ": Administrador já registado");
        }
    }
    
    public void addAdministrador(String nome, Email email, Password password) throws AdministrativoDuplicadoException {
        Administrativo a = getAdministrativoByEmail(email);
        if (a == null) {
            Administrativo administrativo = new Administrativo(nome, email, password);
            this.listaAdministradores.add(administrativo);
        } else {
            throw new AdministrativoDuplicadoException(a.getEmail() + ": Administrador já registado");
        }
    }
    
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
    
    public static RepositorioAdministrativo getInstance() {
        if(repositorioAdministrativo == null) {
            repositorioAdministrativo = new RepositorioAdministrativo();
        }
        return repositorioAdministrativo;
    }
}
