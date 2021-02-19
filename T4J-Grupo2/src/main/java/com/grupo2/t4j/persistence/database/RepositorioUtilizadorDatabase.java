package com.grupo2.t4j.persistence.database;

import com.grupo2.t4j.exception.UtilizadorDuplicadoException;
import com.grupo2.t4j.model.Email;
import com.grupo2.t4j.model.Password;
import com.grupo2.t4j.model.Rolename;
import com.grupo2.t4j.model.Utilizador;
import com.grupo2.t4j.persistence.RepositorioUtilizador;

import java.util.ArrayList;

public class RepositorioUtilizadorDatabase implements RepositorioUtilizador {

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
    

    @Override
    public void save(Email email, String nome, Password password) throws UtilizadorDuplicadoException {
        
    }

    @Override
    public boolean save(Utilizador utilizador) {
        return false;
    }

    @Override
    public Utilizador findByEmail(String emailUt) {
        return null;
    }

    @Override
    public ArrayList<Utilizador> getAll() {
        return null;
    }
}
