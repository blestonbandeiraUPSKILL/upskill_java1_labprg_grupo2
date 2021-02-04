/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.files;

import static com.grupo2.t4j.files.FicheiroRepositorioCategoria.NOME_FICHEIRO_SERIALIZAR;
import com.grupo2.t4j.repository.RepositorioAdministrativo;
import com.grupo2.t4j.repository.RepositorioColaborador;
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
public class FicheiroRepositorioColaborador {
    public static final String NOME_FICHEIRO_SERIALIZAR = "RepositorioColaborador.ltf";
     public FicheiroRepositorioColaborador() {
    }

    public boolean serializar(RepositorioColaborador repositorioColaborador) {
        return serializar(NOME_FICHEIRO_SERIALIZAR, repositorioColaborador);
    }

    public boolean serializar(String nomeFicheiro, RepositorioColaborador repositorioColaborador) {
        return serializar(new File(nomeFicheiro), repositorioColaborador);
    }

    public boolean serializar(File ficheiro, RepositorioColaborador repositorioColaborador) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(
                    new FileOutputStream(ficheiro));
            try {
                out.writeObject(repositorioColaborador);
                
                return true;
            } finally {
                out.close();
            }
        } catch (IOException ex) {
            return false;
        }
    }

    public RepositorioColaborador desserializar() {
        return desserializar(NOME_FICHEIRO_SERIALIZAR);
    }

    public RepositorioColaborador desserializar(String nomeFicheiro) {
        return desserializar(new File(nomeFicheiro));
    }

    public RepositorioColaborador desserializar(File ficheiro) {
        RepositorioColaborador repositorioColaborador;
        try {
            ObjectInputStream in = new ObjectInputStream(
                    new FileInputStream(ficheiro));
            try {
                repositorioColaborador = (RepositorioColaborador) in.readObject();
                
                return repositorioColaborador;
            } finally {
                in.close();
            }
        } catch (IOException | ClassNotFoundException ex) {
            return RepositorioColaborador.getInstance();
        }
    }
}
