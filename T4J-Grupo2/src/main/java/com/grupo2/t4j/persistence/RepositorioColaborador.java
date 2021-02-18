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

import com.grupo2.t4j.model.*;
import com.grupo2.t4j.exception.*;

import java.util.ArrayList;

public interface RepositorioColaborador {


    void save(Email email, String nome,  String funcao, String telefone, Rolename rolename) throws ColaboradorDuplicadoException;

    boolean save(Colaborador colaborador);

    Colaborador findByEmail(String emailCol);

    ArrayList<Colaborador> getAll();

}