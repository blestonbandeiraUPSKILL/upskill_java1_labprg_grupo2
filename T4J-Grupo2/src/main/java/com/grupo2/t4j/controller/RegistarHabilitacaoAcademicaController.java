package com.grupo2.t4j.controller;

import com.grupo2.t4j.files.FicheiroRepositorioHabilitacaoAcademica;
import com.grupo2.t4j.model.HabilitacaoAcademica;
import com.grupo2.t4j.persistence.FabricaRepositorios;
import com.grupo2.t4j.persistence.RepositorioHabilitacaoAcademica;
import com.grupo2.t4j.persistence.database.FabricaRepositoriosDatabase;
import com.grupo2.t4j.persistence.inmemory.RepositorioHabilitacaoAcademicaInMemory;

import java.io.File;
import java.sql.SQLException;
import java.util.List;
public class RegistarHabilitacaoAcademicaController {
    
    //private FabricaRepositorios fabricaRepositorios = new FabricaRepositoriosInMemory();
    private FabricaRepositorios fabricaRepositorios = new FabricaRepositoriosDatabase();
    private RepositorioHabilitacaoAcademica repositorioHabilitacaoAcademica = fabricaRepositorios.getRepositorioHabilitacaoAcademica();
   
    private RepositorioHabilitacaoAcademicaInMemory repositorioHabilitacaoAcademicaInMemory;
    private FicheiroRepositorioHabilitacaoAcademica ficheiroHA;

   /**
     * Registar habilitação académica boolean
     *
     * @param grau as grau atribuído pela habilitação académica
     * @param designacaoCurso as designação do curso
     * @param nomeInstituicao as nome da instituição académica
     * @param mediaCurso as média de final de curso em valores
     * @param emailFreelancer as email do freelancer
     * @return boolean
     */     
    public boolean registarHabilitacaoAcademica(String grau, String designacaoCurso,
           String nomeInstituicao, double mediaCurso, String emailFreelancer) throws SQLException {

        HabilitacaoAcademica habilitacao = new HabilitacaoAcademica(grau, designacaoCurso, nomeInstituicao, mediaCurso);


        return repositorioHabilitacaoAcademica.save(habilitacao, emailFreelancer);
    }



    public List<HabilitacaoAcademica> getAll(String emailFreelancer) throws SQLException{
        return repositorioHabilitacaoAcademica.getAll(emailFreelancer);
    }

    public List<HabilitacaoAcademica> getAllByEmail(String emailFreelancer) throws SQLException{
        return repositorioHabilitacaoAcademica.getAllByEmail(emailFreelancer);
    }
        
    public HabilitacaoAcademica findById(int idHabilitacao) throws SQLException{
        return repositorioHabilitacaoAcademica.findById(idHabilitacao);
    }
    
    public HabilitacaoAcademica findByGrauDesigInst(String grau, String designacaoCurso,
           String nomeInstituicao, String emailFreelancer) throws SQLException{
        return repositorioHabilitacaoAcademica.findByGrauDesigInst(grau, designacaoCurso,
           nomeInstituicao, emailFreelancer);
    }


    
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
