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

    @Override
    public void save(String idGrauProficiencia, Data dataReconhecimento,Email emailFreelancer) throws ReconhecimentoDuplicadoException {
        ReconhecimentoGP rgp = findByGrauEmail(idGrauProficiencia ,emailFreelancer.getEmailText());
        if (rgp == null) {
            ReconhecimentoGP reconhecimentoGP = new ReconhecimentoGP(idGrauProficiencia, dataReconhecimento, emailFreelancer);
            this.listaReconhecimentoGP.add(reconhecimentoGP);
        } else {
            throw new ReconhecimentoDuplicadoException("Reconhecimento já registado");
        }
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
    
}
