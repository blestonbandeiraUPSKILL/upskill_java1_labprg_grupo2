package com.grupo2.t4j.controller;

import com.grupo2.t4j.domain.ReconhecimentoGP;
import com.grupo2.t4j.dto.ReconhecimentoGPDTO;
import com.grupo2.t4j.persistence.FabricaRepositorios;
import com.grupo2.t4j.persistence.RepositorioReconhecimentoGP;
import com.grupo2.t4j.persistence.database.FabricaRepositoriosDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class RegistarReconhecimentoGPController {

    private FabricaRepositorios fabricaRepositorios = new FabricaRepositoriosDatabase();
    private RepositorioReconhecimentoGP repositorioReconhecimentoGP = fabricaRepositorios.getRepositorioReconhecimentoGP();

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

     /**
      * Devolve uma lista de todos os reconhecimentos conferidos a um freelancer
      * @param email
      * @return
      * @throws SQLException 
      */
    public List<ReconhecimentoGPDTO> getAll(String email) throws SQLException{
        List<ReconhecimentoGP> reconhecimentosGP = repositorioReconhecimentoGP.getAll(email);
        List<ReconhecimentoGPDTO> reconhecimentosGPDTO = new ArrayList<>();

        for(ReconhecimentoGP reconhecimentoGP : reconhecimentosGP) {
            reconhecimentosGPDTO.add((ReconhecimentoGPDTO) reconhecimentoGP.toDTO());
        }
        return reconhecimentosGPDTO;
    }


}
