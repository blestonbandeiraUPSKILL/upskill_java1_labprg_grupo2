/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.persistence.inmemory;

/**
 *
 * @author CAD
 */

import com.grupo2.t4j.exception.AdministrativoDuplicadoException;
import com.grupo2.t4j.model.Administrativo;
import com.grupo2.t4j.model.Email;
import com.grupo2.t4j.model.Password;
import com.grupo2.t4j.model.Rolename;
import com.grupo2.t4j.persistence.RepositorioAdministrativo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RepositorioAdministrativoInMemory implements Serializable, RepositorioAdministrativo {
    
    /**
     * Define uma instância estática do Repositório em que estão registados todos
     * os Administrativos da plataforma
     */
    public static RepositorioAdministrativoInMemory repositorioAdministrativoInMemory;
    
    /**
     * Define o atributo da classe RepositorioAdministrativo como uma lista de
     * tipos da classe Administrativo
     */
    private List<Administrativo> listaAdministrativos;
    
    /**
     * Inicializa o Repositório de Administrativos
     */
    RepositorioAdministrativoInMemory(){
        listaAdministrativos = new ArrayList<>();
    }


    /**
     * Devolve uma instância estática do Repositório de Administrativos
     * @return RepositorioAdministrativo
     */
    public static RepositorioAdministrativoInMemory getInstance() {
        if(repositorioAdministrativoInMemory == null) {
            repositorioAdministrativoInMemory = new RepositorioAdministrativoInMemory();
        }
        return repositorioAdministrativoInMemory;
    }

    @Override
    public void save(Email email, String nome, Password password, Rolename rolename) throws AdministrativoDuplicadoException {
        Administrativo a = findByEmail(email.getEmailText());
        if (a == null) {
            Administrativo administrativo = new Administrativo(email, nome, password, Rolename.ADMINISTRATIVO);
            this.listaAdministrativos.add(administrativo);
        } else {
            throw new AdministrativoDuplicadoException(a.getEmail().getEmailText() +
                    ": Administrador já registado");
        }
    }

    @Override
    public ArrayList<Administrativo> getAll() {
        return new ArrayList<Administrativo>(listaAdministrativos);
    }

    @Override
    public Administrativo findByEmail(String email) {
        Administrativo administrativo = null;
        for (int i = 0; i < this.listaAdministrativos.size(); i++) {
            administrativo = this.listaAdministrativos.get(i);
            if (administrativo.getEmail().getEmailText().equals(email)) {
                return administrativo;
            }
        }
        return null;
    }
    




}
