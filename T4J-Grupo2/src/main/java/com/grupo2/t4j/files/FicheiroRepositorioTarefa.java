/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.files;

import static com.grupo2.t4j.files.FicheiroRepositorioCategoria.NOME_FICHEIRO_SERIALIZAR;
import com.grupo2.t4j.repository.RepositorioColaborador;
import com.grupo2.t4j.repository.RepositorioTarefa;
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
public class FicheiroRepositorioTarefa {
    public static final String NOME_FICHEIRO_SERIALIZAR = "RepositorioTarefa.ltf";
    public FicheiroRepositorioTarefa() {
    }

    public boolean serializar(RepositorioTarefa repositorioTarefa) {
        return serializar(NOME_FICHEIRO_SERIALIZAR, repositorioTarefa);
    }

    public boolean serializar(String nomeFicheiro, RepositorioTarefa repositorioTarefa) {
        return serializar(new File(nomeFicheiro), repositorioTarefa);
    }

    public boolean serializar(File ficheiro, RepositorioTarefa repositorioTarefa) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(
                    new FileOutputStream(ficheiro));
            try {
                out.writeObject(repositorioTarefa);
                
                return true;
            } finally {
                out.close();
            }
        } catch (IOException ex) {
            return false;
        }
    }

    public RepositorioTarefa desserializar() {
        return desserializar(NOME_FICHEIRO_SERIALIZAR);
    }

    public RepositorioTarefa desserializar(String nomeFicheiro) {
        return desserializar(new File(nomeFicheiro));
    }

    public RepositorioTarefa desserializar(File ficheiro) {
        RepositorioTarefa repositorioTarefa;
        try {
            ObjectInputStream in = new ObjectInputStream(
                    new FileInputStream(ficheiro));
            try {
                repositorioTarefa = (RepositorioTarefa) in.readObject();
                
                return repositorioTarefa;
            } finally {
                in.close();
            }
        } catch (IOException | ClassNotFoundException ex) {
            return RepositorioTarefa.getInstance();
        }
    }
}
