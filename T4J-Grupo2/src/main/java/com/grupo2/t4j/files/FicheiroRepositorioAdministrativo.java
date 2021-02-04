/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.files;

import static com.grupo2.t4j.files.FicheiroRepositorioCategoria.NOME_FICHEIRO_SERIALIZAR;
import com.grupo2.t4j.repository.RepositorioAdministrativo;
import com.grupo2.t4j.repository.RepositorioAreaActividade;
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
public class FicheiroRepositorioAdministrativo {
    public static final String NOME_FICHEIRO_SERIALIZAR = "RepositorioAdministrativo.ltf";
    public FicheiroRepositorioAdministrativo() {
    }

    public boolean serializar(RepositorioAdministrativo repositorioAdministrativo) {
        return serializar(NOME_FICHEIRO_SERIALIZAR, repositorioAdministrativo);
    }

    public boolean serializar(String nomeFicheiro, RepositorioAdministrativo repositorioAdministrativo) {
        return serializar(new File(nomeFicheiro), repositorioAdministrativo);
    }

    public boolean serializar(File ficheiro, RepositorioAdministrativo repositorioAdministrativo) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(
                    new FileOutputStream(ficheiro));
            try {
                out.writeObject(repositorioAdministrativo);
                
                return true;
            } finally {
                out.close();
            }
        } catch (IOException ex) {
            return false;
        }
    }

    public RepositorioAdministrativo desserializar() {
        return desserializar(NOME_FICHEIRO_SERIALIZAR);
    }

    public RepositorioAdministrativo desserializar(String nomeFicheiro) {
        return desserializar(new File(nomeFicheiro));
    }

    public RepositorioAdministrativo desserializar(File ficheiro) {
        RepositorioAdministrativo repositorioAdministrativo;
        try {
            ObjectInputStream in = new ObjectInputStream(
                    new FileInputStream(ficheiro));
            try {
                repositorioAdministrativo = (RepositorioAdministrativo) in.readObject();
                
                return repositorioAdministrativo;
            } finally {
                in.close();
            }
        } catch (IOException | ClassNotFoundException ex) {
            return RepositorioAdministrativo.getInstance();
        }
    }

}
