/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.files;

import com.grupo2.t4j.persistence.database.RepositorioOrganizacaoDatabase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.SQLException;

/**
 *
 * @author acris
 */
public class FicheiroRepositorioOrganizacao {
    public static final String NOME_FICHEIRO_SERIALIZAR = "RepositorioOrganizacao.ltf";
    public FicheiroRepositorioOrganizacao() {
    }

    public boolean serializar(RepositorioOrganizacaoDatabase repositorioOrganizacao) {
        return serializar(NOME_FICHEIRO_SERIALIZAR, repositorioOrganizacao);
    }

    public boolean serializar(String nomeFicheiro, RepositorioOrganizacaoDatabase repositorioOrganizacao) {
        return serializar(new File(nomeFicheiro), repositorioOrganizacao);
    }

    public boolean serializar(File ficheiro, RepositorioOrganizacaoDatabase repositorioOrganizacao) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(
                    new FileOutputStream(ficheiro));
            try {
                out.writeObject(repositorioOrganizacao);
                
                return true;
            } finally {
                out.close();
            }
        } catch (IOException ex) {
            return false;
        }
    }

    public RepositorioOrganizacaoDatabase desserializar() throws SQLException {
        return desserializar(NOME_FICHEIRO_SERIALIZAR);
    }

    public RepositorioOrganizacaoDatabase desserializar(String nomeFicheiro) throws SQLException {
        return desserializar(new File(nomeFicheiro));
    }

    public RepositorioOrganizacaoDatabase desserializar(File ficheiro) throws SQLException {
        RepositorioOrganizacaoDatabase repositorioOrganizacao;
        try {
            ObjectInputStream in = new ObjectInputStream(
                    new FileInputStream(ficheiro));
            try {
                repositorioOrganizacao = (RepositorioOrganizacaoDatabase) in.readObject();
                
                return repositorioOrganizacao;
            } finally {
                in.close();
            }
        } catch (IOException | ClassNotFoundException ex) {
            return RepositorioOrganizacaoDatabase.getInstance();
        }
    }
}
