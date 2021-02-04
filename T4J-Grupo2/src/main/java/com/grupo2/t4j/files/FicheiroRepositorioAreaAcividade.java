/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.files;

import static com.grupo2.t4j.files.FicheiroRepositorioCategoria.NOME_FICHEIRO_SERIALIZAR;
import com.grupo2.t4j.repository.RepositorioAreaActividade;
import com.grupo2.t4j.repository.RepositorioCategoria;
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
public class FicheiroRepositorioAreaAcividade {
    public static final String NOME_FICHEIRO_SERIALIZAR = "RepositorioAreaActividade.ltf";
    public FicheiroRepositorioAreaAcividade() {
    }

    public boolean serializar(RepositorioAreaActividade repositorioAreaActividade) {
        return serializar(NOME_FICHEIRO_SERIALIZAR, repositorioAreaActividade);
    }

    public boolean serializar(String nomeFicheiro, RepositorioAreaActividade repositorioAreaActividade) {
        return serializar(new File(nomeFicheiro), repositorioAreaActividade);
    }

    public boolean serializar(File ficheiro, RepositorioAreaActividade repositorioAreaActividade) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(
                    new FileOutputStream(ficheiro));
            try {
                out.writeObject(repositorioAreaActividade);
                
                return true;
            } finally {
                out.close();
            }
        } catch (IOException ex) {
            return false;
        }
    }

    public RepositorioAreaActividade desserializar() {
        return desserializar(NOME_FICHEIRO_SERIALIZAR);
    }

    public RepositorioAreaActividade desserializar(String nomeFicheiro) {
        return desserializar(new File(nomeFicheiro));
    }

    public RepositorioAreaActividade desserializar(File ficheiro) {
        RepositorioAreaActividade repositorioAreaActividade;
        try {
            ObjectInputStream in = new ObjectInputStream(
                    new FileInputStream(ficheiro));
            try {
                repositorioAreaActividade = (RepositorioAreaActividade) in.readObject();
                
                return repositorioAreaActividade;
            } finally {
                in.close();
            }
        } catch (IOException | ClassNotFoundException ex) {
            return RepositorioAreaActividade.getInstance();
        }
    }

   
}
