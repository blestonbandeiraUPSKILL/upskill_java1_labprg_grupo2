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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class FicheiroRepositorioAnuncio {
    
   /* public static final String NOME_FICHEIRO_SERIALIZAR = "RepositorioAnuncio.ltf";

    public FicheiroRepositorioAnuncio() {
    }

    public boolean serializar(RepositorioAnuncioInMemory repositorioAnuncioInMemory) {
        return serializar(NOME_FICHEIRO_SERIALIZAR, repositorioAnuncioInMemory);
    }

    public boolean serializar(String nomeFicheiro, RepositorioAnuncioInMemory repositorioAnuncioInMemory) {
        return serializar(new File(nomeFicheiro), repositorioAnuncioInMemory);
    }

    public boolean serializar(File ficheiro, RepositorioAnuncioInMemory repositorioAnuncioInMemory) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(
                    new FileOutputStream(ficheiro));
            try {
                out.writeObject(repositorioAnuncioInMemory);
                
                return true;
            } finally {
                out.close();
            }
        } catch (IOException ex) {
            return false;
        }
    }

    public RepositorioAnuncioInMemory desserializar() {
        return desserializar(NOME_FICHEIRO_SERIALIZAR);
    }

    public RepositorioAnuncioInMemory desserializar(String nomeFicheiro) {
        return desserializar(new File(nomeFicheiro));
    }

    public RepositorioAnuncioInMemory desserializar(File ficheiro) {
        RepositorioAnuncioInMemory repositorioAnuncioInMemory;
        try {
            ObjectInputStream in = new ObjectInputStream(
                    new FileInputStream(ficheiro));
            try {
                repositorioAnuncioInMemory = (RepositorioAnuncioInMemory) in.readObject();
                
                return repositorioAnuncioInMemory;
            } finally {
                in.close();
            }
        } catch (IOException | ClassNotFoundException ex) {
            return RepositorioAnuncioInMemory.getInstance();
        }
    }*/
}
