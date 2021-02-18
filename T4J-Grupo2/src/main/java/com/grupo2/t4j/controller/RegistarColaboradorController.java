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

import com.grupo2.t4j.files.FicheiroRepositorioColaborador;
import com.grupo2.t4j.model.*;
import com.grupo2.t4j.persistence.inmemory.RepositorioColaboradorInMemory;

import java.io.File;
import java.util.List;

public class RegistarColaboradorController {
    
    private RepositorioColaboradorInMemory repositorioColaboradorInMemory;
    private FicheiroRepositorioColaborador ficheiroC;


    public Colaborador getColaboradorByEmail(Email email) {
        return RepositorioColaboradorInMemory.getInstance().getColaboradorByEmail(email.getEmailText());
    }

    public Colaborador novoColaborador(Email email, String nome, String funcao, String telefone, Rolename rolename) {
        return RepositorioColaboradorInMemory.getInstance().novoColaborador(email, nome, funcao, telefone, Rolename.COLABORADOR);
    }
    
    public boolean registarColaborador(Colaborador colaborador) {
        return repositorioColaboradorInMemory.getInstance().addColaborador(colaborador);
    }

    public List<Colaborador> getColaboradores() {
        return repositorioColaboradorInMemory.getInstance().getListaColaboradores();
    }
    
    //////FICHEIROS////////
    public RegistarColaboradorController() {
        ficheiroC = new FicheiroRepositorioColaborador();
        
        desserializar();
    }
    public boolean serializar() {
        return ficheiroC.serializar(repositorioColaboradorInMemory);
    }

    public boolean serializar(File ficheiroExportar) {
        return ficheiroC.serializar(ficheiroExportar, repositorioColaboradorInMemory);
    }

    public void desserializar() {
        repositorioColaboradorInMemory = ficheiroC.desserializar();
    }

    public int desserializar(File ficheiroImportar) {
        RepositorioColaboradorInMemory listaColaboradorImportada = ficheiroC.desserializar(ficheiroImportar);

        return repositorioColaboradorInMemory.getInstance().adicionarListaColaborador(listaColaboradorImportada);
    }


}
