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
import com.grupo2.t4j.repository.RepositorioColaborador;
import java.io.File;
import java.util.List;

public class RegistarColaboradorController {
    
    private RepositorioColaborador repositorioColaborador;
    private FicheiroRepositorioColaborador ficheiroC;


    public Colaborador getColaboradorByEmail(Email email) {
        return RepositorioColaborador.getInstance().getColaboradorByEmail(email.getEmailText());
    }

    public Colaborador novoColaborador(Email email, String nome, String funcao, String telefone, Rolename rolename) {
        return RepositorioColaborador.getInstance().novoColaborador(email, nome, funcao, telefone, Rolename.COLABORADOR);
    }
    
    public boolean registarColaborador(Colaborador colaborador) {
        return repositorioColaborador.getInstance().addColaborador(colaborador);
    }

    public List<Colaborador> getColaboradores() {
        return repositorioColaborador.getInstance().getListaColaboradores();
    }
    
    //////FICHEIROS////////
    public RegistarColaboradorController() {
        ficheiroC = new FicheiroRepositorioColaborador();
        
        desserializar();
    }
    public boolean serializar() {
        return ficheiroC.serializar(repositorioColaborador);
    }

    public boolean serializar(File ficheiroExportar) {
        return ficheiroC.serializar(ficheiroExportar, repositorioColaborador);
    }

    public void desserializar() {
        repositorioColaborador = ficheiroC.desserializar();
    }

    public int desserializar(File ficheiroImportar) {
        RepositorioColaborador listaColaboradorImportada = ficheiroC.desserializar(ficheiroImportar);

        return repositorioColaborador.getInstance().adicionarListaColaborador(listaColaboradorImportada);
    }


}
