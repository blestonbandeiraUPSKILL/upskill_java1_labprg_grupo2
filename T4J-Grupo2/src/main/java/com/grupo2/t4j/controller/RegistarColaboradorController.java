/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.controller;

/**
 *
 * @author CAD
 */

import com.grupo2.t4j.model.Colaborador;
import com.grupo2.t4j.repository.RepositorioColaborador;
import java.util.List;

public class RegistarColaboradorController {
    
    private RepositorioColaborador repositorioColaborador;

    public boolean registarColaborador(String nome, String emailCol, String funcao, String telefone) {
        return repositorioColaborador.getInstance().verificacaoAddColaborador(nome, emailCol, funcao, telefone);
    }

    public List<Colaborador> getColaboradores() {
        return repositorioColaborador.getInstance().getListaColaboradores();
    }
}
