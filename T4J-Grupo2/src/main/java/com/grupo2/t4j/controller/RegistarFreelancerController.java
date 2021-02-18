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

import com.grupo2.t4j.files.*;
import com.grupo2.t4j.model.*;
import com.grupo2.t4j.persistence.*;
import com.grupo2.t4j.persistence.database.*;
import com.grupo2.t4j.persistence.inmemory.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
public class RegistarFreelancerController {
    
    private FabricaRepositorios fabricaRepositorios = new FabricaRepositoriosInMemory();
    //private FabricaRepositorios fabricaRepositorios = new FabricaRepositoriosDatabase();
    private RepositorioFreelancer repositorioFreelancer = fabricaRepositorios.getRepositorioFreelancer();

    private RepositorioColaboradorInMemory repositorioColaboradorInMemory;
    private FicheiroRepositorioColaborador ficheiroC;

    public boolean registarColaborador(String email, String nome, String funcao, String telefone, Rolename rolename) {
        Colaborador colaborador = new Colaborador(email, nome, funcao, telefone, Rolename.COLABORADOR);

        return repositorioColaborador.save(colaborador);
    }

    public List<Colaborador> getAll() {
        return repositorioColaborador.getAll();
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
