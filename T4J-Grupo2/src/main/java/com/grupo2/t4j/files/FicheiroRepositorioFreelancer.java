/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.files;

/**
 *
 * @author CAD
 */

import com.grupo2.t4j.persistence.inmemory.RepositorioFreelancerInMemory;

import java.io.*;

public class FicheiroRepositorioFreelancer {
    
    public static final String NOME_FICHEIRO_SERIALIZAR = "RepositorioFreelancer.ltf";
    public FicheiroRepositorioFreelancer() {
    }

    public boolean serializar(RepositorioFreelancerInMemory repositorioFreelancerInMemory) {
        return serializar(NOME_FICHEIRO_SERIALIZAR, repositorioFreelancerInMemory);
    }

    public boolean serializar(String nomeFicheiro, RepositorioFreelancerInMemory repositorioFreelancerInMemory) {
        return serializar(new File(nomeFicheiro), repositorioFreelancerInMemory);
    }

    public boolean serializar(File ficheiro, RepositorioFreelancerInMemory repositorioFreelancerInMemory) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(
                    new FileOutputStream(ficheiro));
            try {
                out.writeObject(repositorioFreelancerInMemory);
                
                return true;
            } finally {
                out.close();
            }
        } catch (IOException ex) {
            return false;
        }
    }

    public RepositorioFreelancerInMemory desserializar() {
        return desserializar(NOME_FICHEIRO_SERIALIZAR);
    }

    public RepositorioFreelancerInMemory desserializar(String nomeFicheiro) {
        return desserializar(new File(nomeFicheiro));
    }

    public RepositorioFreelancerInMemory desserializar(File ficheiro) {
        RepositorioFreelancerInMemory repositorioFreelancerInMemory;
        try {
            ObjectInputStream in = new ObjectInputStream(
                    new FileInputStream(ficheiro));
            try {
                repositorioFreelancerInMemory = (RepositorioFreelancerInMemory) in.readObject();
                
                return repositorioFreelancerInMemory;
            } finally {
                in.close();
            }
        } catch (IOException | ClassNotFoundException ex) {
            return RepositorioFreelancerInMemory.getInstance();
        }
    }
}
