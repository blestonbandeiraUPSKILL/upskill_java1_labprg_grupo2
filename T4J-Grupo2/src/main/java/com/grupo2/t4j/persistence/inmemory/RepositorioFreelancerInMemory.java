/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.persistence.inmemory;

/**
 *
 * @author CAD
 */

import com.grupo2.t4j.exception.FreelancerDuplicadoException;
import com.grupo2.t4j.model.*;
import com.grupo2.t4j.persistence.RepositorioFreelancer;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RepositorioFreelancerInMemory implements Serializable, RepositorioFreelancer {
        
    /**
     * Define uma instância do Repositório em que estão registados todos
     * os freelancers registados na plataforma
     */
    private static RepositorioFreelancerInMemory repositorioFreelancerInMemory;
    
    /**
     * Define o atributo da classe RepositorioFreelancer como uma lista de
     * tipos da classe Freelancer
     */
    private List<Freelancer> listaFreelancers;
    
    /**
     * Inicializa o Repositório de Freelancers de uma plataforma
     *
     */
    public RepositorioFreelancerInMemory(){
        listaFreelancers = new ArrayList<>();
    }
    
    /**
     * Garante que existe apenas um repositorio para os freelancers
     * @return repositório dos freelancers na memória
     */
    public static RepositorioFreelancerInMemory getInstance(){
        if (repositorioFreelancerInMemory == null){
            repositorioFreelancerInMemory = new RepositorioFreelancerInMemory();
        }
        return repositorioFreelancerInMemory;
    }
    
    @Override
    public void save(Email email, String nome, Password password, String nif, 
            String telefone, String codigoEnderecoPostal) throws FreelancerDuplicadoException {
        Freelancer f = findByNif(nif);
        if (f == null) {
            Freelancer freelancer = new Freelancer(email, nome, password, nif, telefone,
                    codigoEnderecoPostal);
            this.listaFreelancers.add(freelancer);
        } else {
            throw new FreelancerDuplicadoException(nif + ": NIF já registado!");
        }
    }

    @Override
    public boolean save(Freelancer freelancer) throws FreelancerDuplicadoException {
        Freelancer f = findByNif(freelancer.getNif());
        if (f == null) {
            Freelancer freel = new Freelancer(freelancer);
            this.listaFreelancers.add(freel);
        } else {
            throw new FreelancerDuplicadoException(freelancer.getNif() + ": NIF já registado!");
        }
        return false;
    }

    @Override
    public Freelancer findByNif(String nif) {
        for (int i = 0; i < this.listaFreelancers.size(); i++) {
            Freelancer freelancer = this.listaFreelancers.get(i);
            if (freelancer.getNif().equals(nif)) {
                return freelancer;
            }
        }
        return null;
    }

    @Override
    public ArrayList<Freelancer> getAll() {
        return new ArrayList<Freelancer>(listaFreelancers);
    }
        
    public int adicionarListaFreelancer(RepositorioFreelancerInMemory outraListaFreelancer) {
        int totalFreelancersAdicionados = 0;

        for (Freelancer freelancer : outraListaFreelancer.listaFreelancers) {
            boolean freelancerAdicionado = save(freelancer);
            if (freelancerAdicionado) {
                totalFreelancersAdicionados++;
            }
        }
        return totalFreelancersAdicionados;
    }

}
