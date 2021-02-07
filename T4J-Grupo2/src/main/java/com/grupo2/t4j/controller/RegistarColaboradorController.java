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
import com.grupo2.t4j.model.Colaborador;
import com.grupo2.t4j.repository.RepositorioColaborador;
import java.io.File;
import java.util.List;

public class RegistarColaboradorController {
    
    private RepositorioColaborador repositorioColaborador;
    private FicheiroRepositorioColaborador ficheiroC;

    public boolean registarColaborador(String nome, String emailCol, String funcao, String telefone) {
        return repositorioColaborador.getInstance().verificacaoAddColaborador(nome, emailCol, funcao, telefone);
    }
    
     public boolean registarColaborador(Colaborador colaborador) {
        return repositorioColaborador.getInstance().verificacaoAddColaborador(colaborador.getNome(), 
                colaborador.getEmail().getEmailText(), colaborador.getFuncao(), 
                colaborador.getTelefone());
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
