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

import java.sql.SQLException;
import java.util.ArrayList;

public interface RepositorioColaborador {


    void save(Email email, String nome, Password passowrd, String funcao, String telefone) throws ColaboradorDuplicadoException;

    boolean save(Colaborador colaborador) throws SQLException;

    Colaborador findByEmail(String emailCol) throws SQLException;

    ArrayList<Colaborador> getAll() throws SQLException;

    Password findPassword(String email) throws SQLException;

}
