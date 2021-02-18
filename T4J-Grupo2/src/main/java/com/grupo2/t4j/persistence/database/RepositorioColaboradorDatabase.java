package com.grupo2.t4j.persistence.database;

import com.grupo2.t4j.exception.ColaboradorDuplicadoException;
import com.grupo2.t4j.model.Colaborador;
import com.grupo2.t4j.model.Email;
import com.grupo2.t4j.model.Rolename;
import com.grupo2.t4j.persistence.RepositorioColaborador;

import java.util.ArrayList;

public class RepositorioColaboradorDatabase implements RepositorioColaborador {

    /**
     * Define uma instância estática do Repositório em que estão
     * registados todos os Colaboradores
     */
    private static RepositorioColaboradorDatabase repositorioColaboradorDatabase;

    /**
     * Inicializa o Repositório de Colaboradores
     */
    RepositorioColaboradorDatabase(){    }

    /**
     * Devolve uma instância estática do Repositório de Colaboradores
     *
     * @return RepositorioColaboradorDatabase
     */
    public static RepositorioColaboradorDatabase getInstance(){
        if(repositorioColaboradorDatabase == null) {
            repositorioColaboradorDatabase = new RepositorioColaboradorDatabase();
        }
        return repositorioColaboradorDatabase;
    }

    @Override
    public void save(Email email, String nome, String funcao, String telefone, Rolename rolename) throws ColaboradorDuplicadoException {

    }

    @Override
    public boolean save(Colaborador colaborador) {
        return false;
    }

    @Override
    public Colaborador findByEmail(String emailCol) {
        return null;
    }

    @Override
    public ArrayList<Colaborador> getAll() {
        return null;
    }
}
