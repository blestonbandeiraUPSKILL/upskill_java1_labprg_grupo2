/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.files;

import com.grupo2.t4j.persistence.inmemory.RepositorioColaboradorInMemory;

import java.io.*;

/**
 *
 * @author acris
 */
public class FicheiroRepositorioColaborador {
    public static final String NOME_FICHEIRO_SERIALIZAR = "RepositorioColaborador.ltf";
     public FicheiroRepositorioColaborador() {
    }

    public boolean serializar(RepositorioColaboradorInMemory repositorioColaboradorInMemory) {
        return serializar(NOME_FICHEIRO_SERIALIZAR, repositorioColaboradorInMemory);
    }

    public boolean serializar(String nomeFicheiro, RepositorioColaboradorInMemory repositorioColaboradorInMemory) {
        return serializar(new File(nomeFicheiro), repositorioColaboradorInMemory);
    }

    public boolean serializar(File ficheiro, RepositorioColaboradorInMemory repositorioColaboradorInMemory) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(
                    new FileOutputStream(ficheiro));
            try {
                out.writeObject(repositorioColaboradorInMemory);
                
                return true;
            } finally {
                out.close();
            }
        } catch (IOException ex) {
            return false;
        }
    }

    public RepositorioColaboradorInMemory desserializar() {
        return desserializar(NOME_FICHEIRO_SERIALIZAR);
    }

    public RepositorioColaboradorInMemory desserializar(String nomeFicheiro) {
        return desserializar(new File(nomeFicheiro));
    }

    public RepositorioColaboradorInMemory desserializar(File ficheiro) {
        RepositorioColaboradorInMemory repositorioColaboradorInMemory;
        try {
            ObjectInputStream in = new ObjectInputStream(
                    new FileInputStream(ficheiro));
            try {
                repositorioColaboradorInMemory = (RepositorioColaboradorInMemory) in.readObject();
                
                return repositorioColaboradorInMemory;
            } finally {
                in.close();
            }
        } catch (IOException | ClassNotFoundException ex) {
            return RepositorioColaboradorInMemory.getInstance();
        }
    }
}
