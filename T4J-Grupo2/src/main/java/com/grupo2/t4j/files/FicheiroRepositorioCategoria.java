/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.files;

import com.grupo2.t4j.repository.RepositorioCategoria;
import com.grupo2.t4j.repository.RepositorioCompetenciaTecnica;
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

    public boolean serializar(RepositorioCategoria repositorioCategoria) {
        return serializar(NOME_FICHEIRO_SERIALIZAR, repositorioCategoria);
    }

    public boolean serializar(String nomeFicheiro, RepositorioCategoria repositorioCategoria) {
        return serializar(new File(nomeFicheiro), repositorioCategoria);
    }

    public boolean serializar(File ficheiro, RepositorioCategoria repositorioCategoria) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(
                    new FileOutputStream(ficheiro));
            try {
                out.writeObject(repositorioCategoria);
                
                return true;
            } finally {
                out.close();
            }
        } catch (IOException ex) {
            return false;
        }
    }

    public RepositorioCategoria desserializar() {
        return desserializar(NOME_FICHEIRO_SERIALIZAR);
    }

    public RepositorioCategoria desserializar(String nomeFicheiro) {
        return desserializar(new File(nomeFicheiro));
    }

    public RepositorioCategoria desserializar(File ficheiro) {
        RepositorioCategoria repositorioCategoria;
        try {
            ObjectInputStream in = new ObjectInputStream(
                    new FileInputStream(ficheiro));
            try {
                repositorioCategoria = (RepositorioCategoria) in.readObject();
                
                return repositorioCategoria;
            } finally {
                in.close();
            }
        } catch (IOException | ClassNotFoundException ex) {
            return RepositorioCategoria.getInstance();
        }
    }

   
}
