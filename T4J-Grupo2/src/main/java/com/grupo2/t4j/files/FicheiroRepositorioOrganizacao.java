/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.files;

import static com.grupo2.t4j.files.FicheiroRepositorioCategoria.NOME_FICHEIRO_SERIALIZAR;
import com.grupo2.t4j.repository.RepositorioOrganizacao;
import com.grupo2.t4j.repository.RepositorioTarefa;
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

    public boolean serializar(RepositorioOrganizacao repositorioOrganizacao) {
        return serializar(NOME_FICHEIRO_SERIALIZAR, repositorioOrganizacao);
    }

    public boolean serializar(String nomeFicheiro, RepositorioOrganizacao repositorioOrganizacao) {
        return serializar(new File(nomeFicheiro), repositorioOrganizacao);
    }

    public boolean serializar(File ficheiro, RepositorioOrganizacao repositorioOrganizacao) {
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

    public RepositorioOrganizacao desserializar() throws SQLException {
        return desserializar(NOME_FICHEIRO_SERIALIZAR);
    }

    public RepositorioOrganizacao desserializar(String nomeFicheiro) throws SQLException {
        return desserializar(new File(nomeFicheiro));
    }

    public RepositorioOrganizacao desserializar(File ficheiro) throws SQLException {
        RepositorioOrganizacao repositorioOrganizacao;
        try {
            ObjectInputStream in = new ObjectInputStream(
                    new FileInputStream(ficheiro));
            try {
                repositorioOrganizacao = (RepositorioOrganizacao) in.readObject();
                
                return repositorioOrganizacao;
            } finally {
                in.close();
            }
        } catch (IOException | ClassNotFoundException ex) {
            return RepositorioOrganizacao.getInstance();
        }
    }
}
