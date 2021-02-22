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

import com.grupo2.t4j.api.UsersAPI;
import com.grupo2.t4j.files.*;
import com.grupo2.t4j.model.*;
import com.grupo2.t4j.persistence.*;
import com.grupo2.t4j.persistence.database.*;
import com.grupo2.t4j.persistence.inmemory.*;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class RegistarHabilitacaoAcademicaController {
    
    private FabricaRepositorios fabricaRepositorios = new FabricaRepositoriosInMemory();
    //private FabricaRepositorios fabricaRepositorios = new FabricaRepositoriosDatabase();
    private RepositorioHabilitacaoAcademica repositorioHabilitacaoAcademica = fabricaRepositorios.getRepositorioHabilitacaoAcademica();
   
    private RepositorioHabilitacaoAcademicaInMemory repositorioHabilitacaoAcademicaInMemory;
    private FicheiroRepositorioHabilitacaoAcademica ficheiroHA;
    
    public boolean registarHabilitacaoAcademica(String idHabilitacao, String grau, String designacaoCurso,
           String nomeInstituicao, double mediaCurso) throws SQLException {

        HabilitacaoAcademica habilitacao = new HabilitacaoAcademica(idHabilitacao,
                grau, designacaoCurso, nomeInstituicao, mediaCurso);

        return repositorioHabilitacaoAcademica.save(habilitacao);
    }

    public List<HabilitacaoAcademica> getAll() throws SQLException{
        return repositorioHabilitacaoAcademica.getAll();
    }
    
        
    public HabilitacaoAcademica findByiD(String NIF) throws SQLException{
        return repositorioHabilitacaoAcademica.findById(NIF);
    }

    ///////API
    
    
     //////FICHEIROS////////
    public RegistarHabilitacaoAcademicaController() {
        ficheiroHA = new FicheiroRepositorioHabilitacaoAcademica();
        
        desserializar();
    }
    public boolean serializar() {
        return ficheiroHA.serializar(repositorioHabilitacaoAcademicaInMemory);
    }

    public boolean serializar(File ficheiroExportar) {
        return ficheiroHA.serializar(ficheiroExportar, repositorioHabilitacaoAcademicaInMemory);
    }

    public void desserializar() {
        repositorioHabilitacaoAcademicaInMemory = ficheiroHA.desserializar();
    }

    public int desserializar(File ficheiroImportar) {
        RepositorioHabilitacaoAcademicaInMemory listaHabilitacaoImportada = ficheiroHA.desserializar(ficheiroImportar);

        return repositorioHabilitacaoAcademicaInMemory.adicionarListaHabilitacao(listaHabilitacaoImportada);
    }
}
