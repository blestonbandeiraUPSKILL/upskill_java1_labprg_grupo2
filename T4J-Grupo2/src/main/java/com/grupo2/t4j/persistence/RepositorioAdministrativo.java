/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.persistence;

/**
 *
 * @author CAD
 */

import com.grupo2.t4j.exception.AdministrativoDuplicadoException;
import com.grupo2.t4j.domain.Administrativo;
import com.grupo2.t4j.domain.Email;
import com.grupo2.t4j.domain.Password;
import com.grupo2.t4j.domain.Rolename;

import java.util.ArrayList;

public interface RepositorioAdministrativo{

    void save(Email email, String nome, Password password, Rolename rolename) throws AdministrativoDuplicadoException;

    void save(Administrativo administrativo) throws AdministrativoDuplicadoException;

    ArrayList<Administrativo> getAll();

    Administrativo findByEmail(String email);

}
