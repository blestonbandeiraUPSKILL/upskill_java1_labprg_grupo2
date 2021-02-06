/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.files;

import com.grupo2.t4j.repository.RepositorioCategoriaTarefa;

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

    public boolean serializar(RepositorioCategoriaTarefa repositorioCategoriaTarefa) {
        return serializar(NOME_FICHEIRO_SERIALIZAR, repositorioCategoriaTarefa);
    }

    public boolean serializar(String nomeFicheiro, RepositorioCategoriaTarefa repositorioCategoriaTarefa) {
        return serializar(new File(nomeFicheiro), repositorioCategoriaTarefa);
    }

    public boolean serializar(File ficheiro, RepositorioCategoriaTarefa repositorioCategoriaTarefa) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(
                    new FileOutputStream(ficheiro));
            try {
                out.writeObject(repositorioCategoriaTarefa);
                
                return true;
            } finally {
                out.close();
            }
        } catch (IOException ex) {
            return false;
        }
    }

    public RepositorioCategoriaTarefa desserializar() {
        return desserializar(NOME_FICHEIRO_SERIALIZAR);
    }

    public RepositorioCategoriaTarefa desserializar(String nomeFicheiro) {
        return desserializar(new File(nomeFicheiro));
    }

    public RepositorioCategoriaTarefa desserializar(File ficheiro) {
        RepositorioCategoriaTarefa repositorioCategoriaTarefa;
        try {
            ObjectInputStream in = new ObjectInputStream(
                    new FileInputStream(ficheiro));
            try {
                repositorioCategoriaTarefa = (RepositorioCategoriaTarefa) in.readObject();
                
                return repositorioCategoriaTarefa;
            } finally {
                in.close();
            }
        } catch (IOException | ClassNotFoundException ex) {
            return RepositorioCategoriaTarefa.getInstance();
        }
    }

   
}
