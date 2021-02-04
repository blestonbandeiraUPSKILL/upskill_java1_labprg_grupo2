/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.files;

import com.grupo2.t4j.repository.RepositorioTarefa;
import com.grupo2.t4j.repository.RepositorioUtilizador;
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
public class FicheiroRepositorioUtilizador {
    public static final String NOME_FICHEIRO_SERIALIZAR = "RepositorioUtilizador.ltf";
    public FicheiroRepositorioUtilizador() {
    }

    public boolean serializar(RepositorioUtilizador repositorioUtilizador) {
        return serializar(NOME_FICHEIRO_SERIALIZAR, repositorioUtilizador);
    }

    public boolean serializar(String nomeFicheiro, RepositorioUtilizador repositorioUtilizador) {
        return serializar(new File(nomeFicheiro), repositorioUtilizador);
    }

    public boolean serializar(File ficheiro, RepositorioUtilizador repositorioUtilizador) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(
                    new FileOutputStream(ficheiro));
            try {
                out.writeObject(repositorioUtilizador);
                
                return true;
            } finally {
                out.close();
            }
        } catch (IOException ex) {
            return false;
        }
    }

    public RepositorioUtilizador desserializar() {
        return desserializar(NOME_FICHEIRO_SERIALIZAR);
    }

    public RepositorioUtilizador desserializar(String nomeFicheiro) {
        return desserializar(new File(nomeFicheiro));
    }

    public RepositorioUtilizador desserializar(File ficheiro) {
        RepositorioUtilizador repositorioUtilizador;
        try {
            ObjectInputStream in = new ObjectInputStream(
                    new FileInputStream(ficheiro));
            try {
                repositorioUtilizador = (RepositorioUtilizador) in.readObject();
                
                return repositorioUtilizador;
            } finally {
                in.close();
            }
        } catch (IOException | ClassNotFoundException ex) {
            return RepositorioUtilizador.getInstance();
        }
    }
}
