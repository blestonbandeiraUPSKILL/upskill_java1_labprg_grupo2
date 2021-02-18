/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.files;

import com.grupo2.t4j.persistence.inmemory.RepositorioCategoriaTarefaInMemory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author acris
 */
public class FicheiroRepositorioCategoria {
    public static final String NOME_FICHEIRO_SERIALIZAR = "RepositorioCategoria.ltf";

    public FicheiroRepositorioCategoria() {
    }

    public boolean serializar(RepositorioCategoriaTarefaInMemory repositorioCategoriaTarefaInMemory) {
        return serializar(NOME_FICHEIRO_SERIALIZAR, repositorioCategoriaTarefaInMemory);
    }

    public boolean serializar(String nomeFicheiro, RepositorioCategoriaTarefaInMemory repositorioCategoriaTarefaInMemory) {
        return serializar(new File(nomeFicheiro), repositorioCategoriaTarefaInMemory);
    }

    public boolean serializar(File ficheiro, RepositorioCategoriaTarefaInMemory repositorioCategoriaTarefaInMemory) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(
                    new FileOutputStream(ficheiro));
            try {
                out.writeObject(repositorioCategoriaTarefaInMemory);
                
                return true;
            } finally {
                out.close();
            }
        } catch (IOException ex) {
            return false;
        }
    }

    public RepositorioCategoriaTarefaInMemory desserializar() {
        return desserializar(NOME_FICHEIRO_SERIALIZAR);
    }

    public RepositorioCategoriaTarefaInMemory desserializar(String nomeFicheiro) {
        return desserializar(new File(nomeFicheiro));
    }

    public RepositorioCategoriaTarefaInMemory desserializar(File ficheiro) {
        RepositorioCategoriaTarefaInMemory repositorioCategoriaTarefaInMemory;
        try {
            ObjectInputStream in = new ObjectInputStream(
                    new FileInputStream(ficheiro));
            try {
                repositorioCategoriaTarefaInMemory = (RepositorioCategoriaTarefaInMemory) in.readObject();
                
                return repositorioCategoriaTarefaInMemory;
            } finally {
                in.close();
            }
        } catch (IOException | ClassNotFoundException ex) {
            return RepositorioCategoriaTarefaInMemory.getInstance();
        }
    }

   
}
