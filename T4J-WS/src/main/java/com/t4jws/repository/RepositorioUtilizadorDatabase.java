package com.t4jws.repository;


import com.t4jws.exception.UtilizadorDuplicadoException;
import com.t4jws.model.Email;
import com.t4jws.model.Password;
import com.t4jws.model.Utilizador;

import java.util.ArrayList;

public class RepositorioUtilizadorDatabase {

    /**
     * Define uma instância estática do Repositório em que estão
     * registados todos os Utilizadores
     */
    private static RepositorioUtilizadorDatabase repositorioUtilizadorDatabase;

    /**
     * Inicializa o Repositório de Utilizadores
     */
    RepositorioUtilizadorDatabase(){    }

    /**
     * Devolve uma instância estática do Repositório de Utilizadores
     *
     * @return RepositorioUtilizadorDatabase
     */
    public static RepositorioUtilizadorDatabase getInstance(){
        if(repositorioUtilizadorDatabase == null) {
            repositorioUtilizadorDatabase = new RepositorioUtilizadorDatabase();
        }
        return repositorioUtilizadorDatabase;
    }
    


    public void save(Email email, String nome, Password password) throws UtilizadorDuplicadoException {
        
    }


    public boolean save(Utilizador utilizador) {
        return false;
    }

    public Utilizador findByEmail(String emailUt) {
        return null;
    }


    public ArrayList<Utilizador> getAll() {
        return null;
    }
}
