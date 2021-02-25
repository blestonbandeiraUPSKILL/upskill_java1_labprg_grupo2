package com.grupo2.t4j.controller;

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
public class RegistarReconhecimentoGPController {
    
    //private FabricaRepositorios fabricaRepositorios = new FabricaRepositoriosInMemory();
    private FabricaRepositorios fabricaRepositorios = new FabricaRepositoriosDatabase();
    private RepositorioReconhecimentoGP repositorioReconhecimentoGP = fabricaRepositorios.getRepositorioReconhecimentoGP();
    
    private RepositorioReconhecimentoGPInMemory repositorioReconhecimentoGPInMemory;
    private FicheiroRepositorioReconhecimentoGP ficheiroRGP;

   /**
    * Reconhecer o grau de proficiência boolean
    *
    * @param idGrauProficiência as código do grau de proficiência
    * @param dataReconhecimento as data de atribuição do grau de proficiência
    * @param emailFreelancer as email do freelancer
    * @param idCompetenciaTecnica as código da competência técnica
    * @return boolean
    */     
     public boolean registarReconhecimentoGP(int idGrauProficiencia, String dataReconhecimento,
             Email emailFreelancer, String idCompetenciaTecnica) throws SQLException {
        
        ReconhecimentoGP reconhecimentoGP = new ReconhecimentoGP(idGrauProficiencia, 
                dataReconhecimento, emailFreelancer, idCompetenciaTecnica);
       
        return repositorioReconhecimentoGP.save(reconhecimentoGP);
    }

    public List<ReconhecimentoGP> getAll() throws SQLException{
        return repositorioReconhecimentoGP.getAll();
    }
   
    public ReconhecimentoGP findByEmailCompetencia(String email, String idCompetenciaTecnica) throws SQLException{
        return repositorioReconhecimentoGP.findByEmailCompetencia(email, idCompetenciaTecnica);
    }

    ///////API
   
    
     //////FICHEIROS////////
    public RegistarReconhecimentoGPController() {
        ficheiroRGP = new FicheiroRepositorioReconhecimentoGP();
        
        desserializar();
    }
    public boolean serializar() {
        return ficheiroRGP.serializar(repositorioReconhecimentoGPInMemory);
    }

    public boolean serializar(File ficheiroExportar) {
        return ficheiroRGP.serializar(ficheiroExportar, repositorioReconhecimentoGPInMemory);
    }

    public void desserializar() {
        repositorioReconhecimentoGPInMemory = ficheiroRGP.desserializar();
    }

    /*public int desserializar(File ficheiroImportar) {
        RepositorioReconhecimentoGPInMemory listaReconhecimentoGPImportada = ficheiroRGP.desserializar(ficheiroImportar);

        return repositorioReconhecimentoGPInMemory.adicionarListaReconhecimentoGP(listaReconhecimentoGPImportada);
    }*/

}
