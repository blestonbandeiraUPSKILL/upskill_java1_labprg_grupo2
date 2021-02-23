/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.files;

/**
 *
 * @author CAD
 */

import com.grupo2.t4j.persistence.inmemory.RepositorioReconhecimentoGPInMemory;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class FicheiroRepositorioReconhecimentoGP {
    
    public static final String NOME_FICHEIRO_SERIALIZAR = "RepositorioReconhecimentoGP.ltf";
    
    public FicheiroRepositorioReconhecimentoGP() {
    }

    public boolean serializar(RepositorioReconhecimentoGPInMemory repositorioReconhecimentoGPInMemory) {
        return serializar(NOME_FICHEIRO_SERIALIZAR, repositorioReconhecimentoGPInMemory);
    }

    public boolean serializar(String nomeFicheiro, RepositorioReconhecimentoGPInMemory repositorioReconhecimentoGPInMemory) {
        return serializar(new File(nomeFicheiro), repositorioReconhecimentoGPInMemory);
    }

    public boolean serializar(File ficheiro, RepositorioReconhecimentoGPInMemory repositorioReconhecimentoGPInMemory) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(
                    new FileOutputStream(ficheiro));
            try {
                out.writeObject(repositorioReconhecimentoGPInMemory);
                
                return true;
            } finally {
                out.close();
            }
        } catch (IOException ex) {
            return false;
        }
    }

    public RepositorioReconhecimentoGPInMemory desserializar() {
        return desserializar(NOME_FICHEIRO_SERIALIZAR);
    }

    public RepositorioReconhecimentoGPInMemory desserializar(String nomeFicheiro) {
        return desserializar(new File(nomeFicheiro));
    }

    public RepositorioReconhecimentoGPInMemory desserializar(File ficheiro) {
        RepositorioReconhecimentoGPInMemory repositorioReconhecimentoGPInMemory;
        try {
            ObjectInputStream in = new ObjectInputStream(
                    new FileInputStream(ficheiro));
            try {
                repositorioReconhecimentoGPInMemory = (RepositorioReconhecimentoGPInMemory) in.readObject();
                
                return repositorioReconhecimentoGPInMemory;
            } finally {
                in.close();
            }
        } catch (IOException | ClassNotFoundException ex) {
            return RepositorioReconhecimentoGPInMemory.getInstance();
        }
    }
}
