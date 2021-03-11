package com.grupo2.t4j.persistence.database;

import com.grupo2.t4j.exception.AdministrativoDuplicadoException;
import com.grupo2.t4j.domain.Administrativo;
import com.grupo2.t4j.domain.Email;
import com.grupo2.t4j.domain.Password;
import com.grupo2.t4j.domain.Rolename;
import com.grupo2.t4j.persistence.RepositorioAdministrativo;

import java.util.ArrayList;

public class RepositorioAdministrativoDatabase implements RepositorioAdministrativo {

    /**
     * Define uma instância estática do Repositório em que estão registadas todos
     * os Administrativos da plataforma
     */
    private static RepositorioAdministrativoDatabase repositorioAdministrativoDatabase;

    /**
     * Inicializa o Repositório de Administrativos
     */
    RepositorioAdministrativoDatabase(){    }

    /**
     * Devolve uma instância estática do Repositório de Administrativos
     * @return RepositorioAdministrativoDatabase
     */
    public static RepositorioAdministrativoDatabase getInstance(){
        if(repositorioAdministrativoDatabase == null) {
            repositorioAdministrativoDatabase = new RepositorioAdministrativoDatabase();
        }
        return repositorioAdministrativoDatabase;
    }


    @Override
    public void save(Email email, String nome, Password password, Rolename rolename) throws AdministrativoDuplicadoException {

    }

    @Override
    public void save(Administrativo administrativo) throws AdministrativoDuplicadoException {

    }

    @Override
    public ArrayList<Administrativo> getAll() {
        return null;
    }

    @Override
    public Administrativo findByEmail(String email) {
        return null;
    }
}
