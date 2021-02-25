/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.files;

import com.grupo2.t4j.persistence.inmemory.RepositorioUtilizadorInMemory;

import java.io.*;

/**
 *
 * @author acris
 */
public class FicheiroRepositorioUtilizador {
    public static final String NOME_FICHEIRO_SERIALIZAR = "RepositorioUtilizador.ltf";
    public FicheiroRepositorioUtilizador() {
    }

    public boolean serializar(RepositorioUtilizadorInMemory repositorioUtilizadorInMemory) {
        return serializar(NOME_FICHEIRO_SERIALIZAR, repositorioUtilizadorInMemory);
    }

    public boolean serializar(String nomeFicheiro, RepositorioUtilizadorInMemory repositorioUtilizadorInMemory) {
        return serializar(new File(nomeFicheiro), repositorioUtilizadorInMemory);
    }

    public boolean serializar(File ficheiro, RepositorioUtilizadorInMemory repositorioUtilizadorInMemory) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(
                    new FileOutputStream(ficheiro));
            try {
                out.writeObject(repositorioUtilizadorInMemory);
                
                return true;
            } finally {
                out.close();
            }
        } catch (IOException ex) {
            return false;
        }
    }

    public RepositorioUtilizadorInMemory desserializar() {
        return desserializar(NOME_FICHEIRO_SERIALIZAR);
    }

    public RepositorioUtilizadorInMemory desserializar(String nomeFicheiro) {
        return desserializar(new File(nomeFicheiro));
    }

    public RepositorioUtilizadorInMemory desserializar(File ficheiro) {
        RepositorioUtilizadorInMemory repositorioUtilizadorInMemory;
        try {
            ObjectInputStream in = new ObjectInputStream(
                    new FileInputStream(ficheiro));
            try {
                repositorioUtilizadorInMemory = (RepositorioUtilizadorInMemory) in.readObject();
                
                return repositorioUtilizadorInMemory;
            } finally {
                in.close();
            }
        } catch (IOException | ClassNotFoundException ex) {
            return RepositorioUtilizadorInMemory.getInstance();
        }
    }
}
