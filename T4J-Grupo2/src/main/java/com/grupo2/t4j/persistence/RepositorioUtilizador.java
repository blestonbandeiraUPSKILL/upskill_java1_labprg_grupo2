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

import com.grupo2.t4j.exception.UtilizadorDuplicadoException;
import com.grupo2.t4j.model.Email;
import com.grupo2.t4j.model.Password;
import com.grupo2.t4j.model.Utilizador;

import java.util.ArrayList;

public interface RepositorioUtilizador {


     void save(Email email, String nome, Password password) throws UtilizadorDuplicadoException;

     boolean save(Utilizador utilizador);

     Utilizador findByEmail(String emailUt);

    ArrayList<Utilizador> getAll();

}

