/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.files;

/**
 *
 * @author acris
 */
public class FicheiroRepositorioTarefa {
    /*public static final String NOME_FICHEIRO_SERIALIZAR = "RepositorioTarefa.ltf";
    public FicheiroRepositorioTarefa() {
    }

    public boolean serializar(RepositorioTarefaInMemory repositorioTarefaInMemory) {
        return serializar(NOME_FICHEIRO_SERIALIZAR, repositorioTarefaInMemory);
    }

    public boolean serializar(String nomeFicheiro, RepositorioTarefaInMemory repositorioTarefaInMemory) {
        return serializar(new File(nomeFicheiro), repositorioTarefaInMemory);
    }

    public boolean serializar(File ficheiro, RepositorioTarefaInMemory repositorioTarefaInMemory) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(
                    new FileOutputStream(ficheiro));
            try {
                out.writeObject(repositorioTarefaInMemory);

                return true;
            } finally {
                out.close();
            }
        } catch (IOException ex) {
            return false;
        }
    }

    public RepositorioTarefaInMemory desserializar() {
        return desserializar(NOME_FICHEIRO_SERIALIZAR);
    }

    public RepositorioTarefaInMemory desserializar(String nomeFicheiro) {
        return desserializar(new File(nomeFicheiro));
    }

    public RepositorioTarefaInMemory desserializar(File ficheiro) {
        RepositorioTarefaInMemory repositorioTarefaInMemory;
        try {
            ObjectInputStream in = new ObjectInputStream(
                    new FileInputStream(ficheiro));
            try {
                repositorioTarefaInMemory = (RepositorioTarefaInMemory) in.readObject();

                return repositorioTarefaInMemory;
            } finally {
                in.close();
            }
        } catch (IOException | ClassNotFoundException ex) {
            return RepositorioTarefaInMemory.getInstance();
        }
    }*/
}
