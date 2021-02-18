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
    public void save(Email email, String nome, Password password, Rolename rolename,
            String NIF, EnderecoPostal enderecoPostalFreelancer) throws FreelancerDuplicadoException {
        Freelancer f = findByNIF(NIF);
        if (f == null) {
            Freelancer freelancer = new Freelancer(email, nome, password, rolename,
            NIF, enderecoPostalFreelancer);
            this.listaFreelancers.add(freelancer);
        } else {
            throw new FreelancerDuplicadoException(NIF + ": NIF já registado!");
        }
    }

    @Override
    public boolean save(Freelancer freelancer) {
        Freelancer f = findByNIF(freelancer.getNIF());
        if (f == null) {
            Freelancer freel = new Freelancer(freelancer);
            this.listaFreelancers.add(freel);
        } else {
            throw new FreelancerDuplicadoException(freelancer.getNIF() + ": NIF já registado!");
        }
        return false;
    }

    @Override
    public Freelancer findByNIF(String NIF) {
        Freelancer freelancer = null;
        for (int i = 0; i < this.listaFreelancers.size(); i++) {
            freelancer = this.listaFreelancers.get(i);
            if (freelancer.getNIF().equals(NIF)) {
                return freelancer;
            }
        }
        return null;
    }

    @Override
    public ArrayList<Freelancer> getAll() {
        return new ArrayList<Freelancer>(listaFreelancers);
    }
    
    public int adicionarListaFreelancer(RepositorioFreelancerInMemory outraListaFreelancers) {
        int totalFreelancersAdicionados = 0;

        for (Freelancer freelancer : outraListaFreelancers.listaFreelancers) {
            boolean freelancerAdicionado = save(freelancer);
            if (freelancerAdicionado) {
                totalFreelancersAdicionados++;
            }
        }
        return totalFreelancersAdicionados;
    }
}
