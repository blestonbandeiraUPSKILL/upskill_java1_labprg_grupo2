package com.grupo2.t4j.controller;

import com.grupo2.t4j.files.FicheiroRepositorioReconhecimentoGP;
import com.grupo2.t4j.model.Email;
import com.grupo2.t4j.model.ReconhecimentoGP;
import com.grupo2.t4j.persistence.FabricaRepositorios;
import com.grupo2.t4j.persistence.RepositorioReconhecimentoGP;
import com.grupo2.t4j.persistence.database.FabricaRepositoriosDatabase;
import com.grupo2.t4j.persistence.inmemory.RepositorioReconhecimentoGPInMemory;

import java.io.File;
import java.sql.SQLException;
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
    * @param idGrauProficiencia as código do grau de proficiência
    * @param dataReconhecimento as data de atribuição do grau de proficiência
    * @param emailFreelancer as email do freelancer
    * @return boolean
    */     
     public boolean registarReconhecimentoGP(int idGrauProficiencia,
                                             String emailFreelancer, String dataReconhecimento) throws SQLException {
       
        return repositorioReconhecimentoGP.save(idGrauProficiencia, emailFreelancer, dataReconhecimento);
    }

    public List<ReconhecimentoGP> getAll(String email) throws SQLException{
        return repositorioReconhecimentoGP.getAll(email);
    }
   
    public ReconhecimentoGP findByEmailCompetencia(String email, String idCompetenciaTecnica) throws SQLException{
        return repositorioReconhecimentoGP.findByEmailCompetencia(email, idCompetenciaTecnica);
    }


}
