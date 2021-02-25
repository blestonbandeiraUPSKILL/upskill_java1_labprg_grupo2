/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.files;

import com.grupo2.t4j.persistence.inmemory.RepositorioAreaActividadeInMemory;

import java.io.*;

/**
 *
 * @author acris
 */
public class FicheiroRepositorioAreaActividade {
    public static final String NOME_FICHEIRO_SERIALIZAR = "RepositorioAreaActividade.ltf";
    public FicheiroRepositorioAreaActividade() {
    }

    public boolean serializar(RepositorioAreaActividadeInMemory repositorioAreaActividadeInMemory) {
        return serializar(NOME_FICHEIRO_SERIALIZAR, repositorioAreaActividadeInMemory);
    }

    public boolean serializar(String nomeFicheiro, RepositorioAreaActividadeInMemory repositorioAreaActividadeInMemory) {
        return serializar(new File(nomeFicheiro), repositorioAreaActividadeInMemory);
    }

    public boolean serializar(File ficheiro, RepositorioAreaActividadeInMemory repositorioAreaActividadeInMemory) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(
                    new FileOutputStream(ficheiro));
            try {
                out.writeObject(repositorioAreaActividadeInMemory);
                
                return true;
            } finally {
                out.close();
            }
        } catch (IOException ex) {
            return false;
        }
    }

    public RepositorioAreaActividadeInMemory desserializar() {
        return desserializar(NOME_FICHEIRO_SERIALIZAR);
    }

    public RepositorioAreaActividadeInMemory desserializar(String nomeFicheiro) {
        return desserializar(new File(nomeFicheiro));
    }

    public RepositorioAreaActividadeInMemory desserializar(File ficheiro) {
        RepositorioAreaActividadeInMemory repositorioAreaActividadeInMemory;
        try {
            ObjectInputStream in = new ObjectInputStream(
                    new FileInputStream(ficheiro));
            try {
                repositorioAreaActividadeInMemory = (RepositorioAreaActividadeInMemory) in.readObject();
                
                return repositorioAreaActividadeInMemory;
            } finally {
                in.close();
            }
        } catch (IOException | ClassNotFoundException ex) {
            return RepositorioAreaActividadeInMemory.getInstance();
        }
    }

   
}
