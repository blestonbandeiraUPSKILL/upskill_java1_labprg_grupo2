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

import com.grupo2.t4j.persistence.inmemory.RepositorioHabilitacaoAcademicaInMemory;

import java.io.*;

public class FicheiroRepositorioHabilitacaoAcademica {
    
    public static final String NOME_FICHEIRO_SERIALIZAR = "RepositorioabilitacaoAcademica.ltf";
    
    public FicheiroRepositorioHabilitacaoAcademica() {
    }

    public boolean serializar(RepositorioHabilitacaoAcademicaInMemory repositorioHabilitacaoAcademicaInMemory) {
        return serializar(NOME_FICHEIRO_SERIALIZAR, repositorioHabilitacaoAcademicaInMemory);
    }

    public boolean serializar(String nomeFicheiro, RepositorioHabilitacaoAcademicaInMemory repositorioHabilitacaoAcademicaInMemory) {
        return serializar(new File(nomeFicheiro), repositorioHabilitacaoAcademicaInMemory);
    }

    public boolean serializar(File ficheiro, RepositorioHabilitacaoAcademicaInMemory repositorioHabilitacaoAcademicaInMemory) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(
                    new FileOutputStream(ficheiro));
            try {
                out.writeObject(repositorioHabilitacaoAcademicaInMemory);
                
                return true;
            } finally {
                out.close();
            }
        } catch (IOException ex) {
            return false;
        }
    }

    public RepositorioHabilitacaoAcademicaInMemory desserializar() {
        return desserializar(NOME_FICHEIRO_SERIALIZAR);
    }

    public RepositorioHabilitacaoAcademicaInMemory desserializar(String nomeFicheiro) {
        return desserializar(new File(nomeFicheiro));
    }

    public RepositorioHabilitacaoAcademicaInMemory desserializar(File ficheiro) {
        RepositorioHabilitacaoAcademicaInMemory repositorioHabilitacaoAcademicaInMemory;
        try {
            ObjectInputStream in = new ObjectInputStream(
                    new FileInputStream(ficheiro));
            try {
                repositorioHabilitacaoAcademicaInMemory = (RepositorioHabilitacaoAcademicaInMemory) in.readObject();
                
                return repositorioHabilitacaoAcademicaInMemory;
            } finally {
                in.close();
            }
        } catch (IOException | ClassNotFoundException ex) {
            return RepositorioHabilitacaoAcademicaInMemory.getInstance();
        }
    }
}
