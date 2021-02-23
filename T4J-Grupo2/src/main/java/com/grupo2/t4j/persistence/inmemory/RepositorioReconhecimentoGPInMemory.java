/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.persistence.inmemory;

import com.grupo2.t4j.exception.ReconhecimentoDuplicadoException;
import com.grupo2.t4j.model.Data;
import com.grupo2.t4j.model.Email;
import com.grupo2.t4j.model.ReconhecimentoGP;
import com.grupo2.t4j.persistence.RepositorioReconhecimentoGP;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author acris
 */
public class RepositorioReconhecimentoGPInMemory implements Serializable, RepositorioReconhecimentoGP{
    
       /**
     * Define uma instância estática do Repositório em que estão registados todos
     * os Reconhecimento de graus de proficiencia
     */
    public static RepositorioReconhecimentoGPInMemory repositorioReconhecimentoGPInMemory;
    
    /**
     * Define o atributo da classe RepositorioAdministrativo como uma lista de
     * tipos da classe Administrativo
     */
    private List<ReconhecimentoGP> listaReconhecimentoGP;
    
    /**
     * Inicializa o Repositório de Reconhecimento de graus de proficiencia
     */
    RepositorioReconhecimentoGPInMemory(){
        listaReconhecimentoGP = new ArrayList<>();
    }
    
    /**
     * Devolve uma instância estática do Repositório de Administrativos
     * @return RepositorioAdministrativo
     */
    public static RepositorioReconhecimentoGPInMemory getInstance() {
        if(repositorioReconhecimentoGPInMemory == null) {
            repositorioReconhecimentoGPInMemory = new RepositorioReconhecimentoGPInMemory();
        }
        return repositorioReconhecimentoGPInMemory;
    }


    public void save(int idGrauProficiencia, Data dataReconhecimento,
             Email emailFreelancer, String idCompetenciaTecnica) throws ReconhecimentoDuplicadoException {
       // ReconhecimentoGP rgp = findByGrauEmail(idGrauProficiencia ,emailFreelancer.getEmailText());
       /* if (rgp == null) {
            ReconhecimentoGP reconhecimentoGP = new ReconhecimentoGP(idGrauProficiencia, 
                    dataReconhecimento, emailFreelancer, idCompetenciaTecnica);
            this.listaReconhecimentoGP.add(reconhecimentoGP);
        } else {
            throw new ReconhecimentoDuplicadoException("Reconhecimento já registado");
        }*/
    }

    @Override
    public void save(String idGrauProficiencia, Data dataReconhecimento, Email emailFreelancer, String idCompetenciaTecnica) throws ReconhecimentoDuplicadoException, SQLException {

    }

    @Override
    public boolean save (ReconhecimentoGP reconhecimentoGP)throws ReconhecimentoDuplicadoException {
        /*ReconhecimentoGP rgp = findByEmailCompetencia(reconhecimentoGP.getEmailFreelancer().getEmailText(),
                reconhecimentoGP.getIdGrauProficiencia());
        if (rgp == null) {
            ReconhecimentoGP reconhecimento = new ReconhecimentoGP(reconhecimentoGP);
            this.listaReconhecimentoGP.add(reconhecimento);
        } else {
            throw new ReconhecimentoDuplicadoException(reconhecimentoGP.getIdGrauProficiencia() 
                    + ": Grau de proficiência já registado para a seguinte competência "
                            + "técnica" +reconhecimentoGP.getIdCompetenciaTecnica());
        }*/
        return false;
    }
    
    @Override
    public ArrayList<ReconhecimentoGP> getAll() {
        return new ArrayList<ReconhecimentoGP>(listaReconhecimentoGP);
    }
 
    @Override
    public List<ReconhecimentoGP> findByEmail(String email) {
        ReconhecimentoGP reconhecimentoGP = null;
        List<ReconhecimentoGP> reconhecimentosGP = new ArrayList<>();
        for (int i = 0; i < this.listaReconhecimentoGP.size(); i++) {
            reconhecimentoGP = this.listaReconhecimentoGP.get(i);
            if (reconhecimentoGP.getEmailFreelancer().getEmailText().equals(email)) {
                reconhecimentosGP.add(reconhecimentoGP);
            }
        }
        return reconhecimentosGP;
    }

    @Override
    public ReconhecimentoGP findByEmailCompetencia(String email, String idCompetenciaTecnica) {
        List<ReconhecimentoGP> reconhecimentosGP = findByEmail(email);
        for (ReconhecimentoGP rcp : reconhecimentosGP){
            if (rcp.getIdCompetenciaTecnica().equals(idCompetenciaTecnica))
                 return rcp;
        }
        return null;
    }
    
     private ReconhecimentoGP findByGrauEmail(String idGrauProficiencia, String emailFreelancer) {
        ReconhecimentoGP reconhecimentoGP = null;
        for (int i = 0; i < this.listaReconhecimentoGP.size(); i++) {
            reconhecimentoGP = this.listaReconhecimentoGP.get(i);
            if (reconhecimentoGP.getEmailFreelancer().getEmailText().equals(emailFreelancer)) {
                return reconhecimentoGP;
            }
        }
        return null;
    }  
     
     public int adicionarListaReconhecimentoGP(RepositorioReconhecimentoGPInMemory outraListaReconhecimentoGP){
        int totalReconhecimentoGPAdicionados = 0;

        for (ReconhecimentoGP reconhecimentoGP : outraListaReconhecimentoGP.listaReconhecimentoGP) {
            boolean reconhecimentoGPAdicionado = save(reconhecimentoGP);
            if (reconhecimentoGPAdicionado) {
                totalReconhecimentoGPAdicionados++;
            }
        }
        return totalReconhecimentoGPAdicionados;
    }
}
