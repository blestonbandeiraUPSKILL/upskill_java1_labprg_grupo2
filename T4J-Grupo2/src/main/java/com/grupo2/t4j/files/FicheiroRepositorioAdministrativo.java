/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.files;

import com.grupo2.t4j.persistence.inmemory.RepositorioAdministrativoInMemory;

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

    public boolean serializar(RepositorioAdministrativoInMemory repositorioAdministrativoInMemory) {
        return serializar(NOME_FICHEIRO_SERIALIZAR, repositorioAdministrativoInMemory);
    }

    public boolean serializar(String nomeFicheiro, RepositorioAdministrativoInMemory repositorioAdministrativoInMemory) {
        return serializar(new File(nomeFicheiro), repositorioAdministrativoInMemory);
    }

    public boolean serializar(File ficheiro, RepositorioAdministrativoInMemory repositorioAdministrativoInMemory) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(
                    new FileOutputStream(ficheiro));
            try {
                out.writeObject(repositorioAdministrativoInMemory);
                
                return true;
            } finally {
                out.close();
            }
        } catch (IOException ex) {
            return false;
        }
    }

    public RepositorioAdministrativoInMemory desserializar() {
        return desserializar(NOME_FICHEIRO_SERIALIZAR);
    }

    public RepositorioAdministrativoInMemory desserializar(String nomeFicheiro) {
        return desserializar(new File(nomeFicheiro));
    }

    public RepositorioAdministrativoInMemory desserializar(File ficheiro) {
        RepositorioAdministrativoInMemory repositorioAdministrativoInMemory;
        try {
            ObjectInputStream in = new ObjectInputStream(
                    new FileInputStream(ficheiro));
            try {
                repositorioAdministrativoInMemory = (RepositorioAdministrativoInMemory) in.readObject();
                
                return repositorioAdministrativoInMemory;
            } finally {
                in.close();
            }
        } catch (IOException | ClassNotFoundException ex) {
            return RepositorioAdministrativoInMemory.getInstance();
        }
    }

}
