/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.files;

import com.grupo2.t4j.repository.RepositorioCompetenciaTecnica;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 *
 * @author acris
 */
public class FicheiroRepositorioCompetenciaTecnica {
    public static final String NOME_FICHEIRO_SERIALIZAR = "RepositorioCompTecnicas.ltf";

    public FicheiroRepositorioCompetenciaTecnica() {
    }

    public boolean serializar(RepositorioCompetenciaTecnica repositorioCompTec) {
        return serializar(NOME_FICHEIRO_SERIALIZAR, repositorioCompTec);
    }

    public boolean serializar(String nomeFicheiro, RepositorioCompetenciaTecnica repositorioCompTec) {
        return serializar(new File(nomeFicheiro), repositorioCompTec);
    }

    public boolean serializar(File ficheiro, RepositorioCompetenciaTecnica repositorioCompTec) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(
                    new FileOutputStream(ficheiro));
            try {
                out.writeObject(repositorioCompTec);
                
                return true;
            } finally {
                out.close();
            }
        } catch (IOException ex) {
            return false;
        }
    }

    public RepositorioCompetenciaTecnica desserializar() {
        return desserializar(NOME_FICHEIRO_SERIALIZAR);
    }

    public RepositorioCompetenciaTecnica desserializar(String nomeFicheiro) {
        return desserializar(new File(nomeFicheiro));
    }

    public RepositorioCompetenciaTecnica desserializar(File ficheiro) {
        RepositorioCompetenciaTecnica repositorioCompTec;
        try {
            ObjectInputStream in = new ObjectInputStream(
                    new FileInputStream(ficheiro));
            try {
                repositorioCompTec = (RepositorioCompetenciaTecnica) in.readObject();
                
                return repositorioCompTec;
            } finally {
                in.close();
            }
        } catch (IOException | ClassNotFoundException ex) {
            return RepositorioCompetenciaTecnica.getInstance();
        }
    }

    ////////////////Código para guardar ficheiro de texto, caso necessário (Com erros)/////////////////////////////
    /*public boolean exportarTexto(File ficheiro, RepositorioCompetenciaTecnica repositorioCompTec) {
        try {
            PrintWriter out = new PrintWriter(ficheiro);
            String[] repositorioCompTecParaFicheiro = repositorioCompTec.getInstance().getListaComoArray();

            try {
                for (int i = 0; i < repositorioCompTecParaFicheiro.length - 1; i++) {
                    out.print(repositorioCompTecParaFicheiro[i] + "\n");
                }
                out.print(repositorioCompTecParaFicheiro[repositorioCompTecParaFicheiro.length - 1]);

                return true;
            } finally {
                out.close();
            }
        } catch (IOException ex) {
            return false;
        }
    }*/

    /*public RepositorioCompetenciaTecnica importarTexto(File ficheiro) {
        try {
            RepositorioCompetenciaTecnica lista = RepositorioCompetenciaTecnica.getInstance();
            Scanner in = new Scanner(ficheiro);

            try {
                while (in.hasNextLine()) {
                    String linha = in.nextLine();
                    String []dadosCompetenciaTecnica = CompetenciaTecnica.getCompetenciaComoArray(linha);

                    lista.addCompetenciaTecnica(dadosCompetenciaTecnica[1], Integer.parseInt(dadosCompetenciaTecnica[0]));
                }

                return lista;
            } finally {
                in.close();
            }
        } catch (IOException ex) {
            return RepositorioCompetenciaTecnica.getInstance();
        }
    }*/
}
