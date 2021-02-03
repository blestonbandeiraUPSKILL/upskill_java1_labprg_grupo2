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
    
    private Plataforma plataforma;
    private List<Administrativo> listaAdministradores = new ArrayList<>();
    
    public RepositorioAdministrativo(Plataforma plataforma, List<Administrativo> listaAdministradores){
        repositorioAdministrativo.plataforma = plataforma;
        repositorioAdministrativo.listaAdministradores = listaAdministradores;
    }
          
    public void addAdministrador(Administrativo administrativo) throws AdministrativoDuplicadoException {
        Administrativo a = getAdministrativoByEmail(administrativo.getEmail());
        if (a == null) {
            this.listaAdministradores.add(administrativo);
        } else {
            throw new AdministrativoDuplicadoException(a.getEmail() + ": Utilizador já registado");
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
        if(RepositorioAdministrativo.repositorioAdministrativo == null) {
            List<Administrativo> newListaAdministradores = new ArrayList<>();
            RepositorioAdministrativo.repositorioAdministrativo = new RepositorioAdministrativo(Plataforma.getInstance(), newListaAdministradores);
        }
        return repositorioAdministrativo;
    }
}
