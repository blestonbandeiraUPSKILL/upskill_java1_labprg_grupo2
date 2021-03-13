package com.grupo2.t4j.controller;

import com.grupo2.t4j.api.UsersAPI;
import com.grupo2.t4j.domain.AlgoritmoGeradorPasswords;
import com.grupo2.t4j.domain.Colaborador;
import com.grupo2.t4j.domain.Email;
import com.grupo2.t4j.domain.Password;
import com.grupo2.t4j.persistence.FabricaRepositorios;
import com.grupo2.t4j.persistence.RepositorioColaborador;
import com.grupo2.t4j.persistence.database.FabricaRepositoriosDatabase;

import java.sql.SQLException;
import java.util.List;

public class RegistarColaboradorController {

    private FabricaRepositorios fabricaRepositorios = new FabricaRepositoriosDatabase();
    private RepositorioColaborador repositorioColaborador = fabricaRepositorios.getRepositorioColaborador();

    private AlgoritmoGeradorPasswords algoritmoGeradorPasswords;

    /**
     * Registo do Colaborador boolean
     *
     * @param nome as nome do colaborador
     * @param funcao as função do colaborador
     * @param telefone as telefone do colaborador
     * @param email as email do colaborador
     * @param nifOrganizacao as NIF da organização
     * @return boolean
     */
    public boolean registarColaborador(String email, String nome, String funcao, String telefone, String nifOrganizacao) throws SQLException {

        AlgoritmoGeradorPasswords algoritmoGeradorPasswords = new AlgoritmoGeradorPasswords();
        Password password = new Password(algoritmoGeradorPasswords.geraPassword());

        Colaborador colaborador = new Colaborador(new Email(email), nome , password, funcao, telefone, nifOrganizacao);
        registarColaboradorComoUtilizador(colaborador);

        return repositorioColaborador.save(colaborador);
    }

    /**
     * Devolve uma lista de todos os colaboradores de uma organizacao
     * @param nifOrganizacacao
     * @return
     * @throws SQLException 
     */
    public List<Colaborador> getAll(String nifOrganizacacao) throws SQLException {
        return repositorioColaborador.getAll(nifOrganizacacao);
    }

    /**
     * Devolve o nif da organizacao do colaborador logado
     * @param email
     * @return
     * @throws SQLException 
     */
    public String getNifOrganizacao(String email) throws SQLException {
        return repositorioColaborador.getNifOrganizacao(email);
    }

    /**
     * Devolve um colaborador a partir do seu email
     * @param email
     * @return
     * @throws SQLException 
     */
    public Colaborador findByEmail(String email) throws SQLException {
        return repositorioColaborador.findByEmail(email);
    }

    /**
     * Devolve a password do colaborador
     * @param email
     * @return
     * @throws SQLException 
     */
    public Password findPassword(String email) throws SQLException {
        return repositorioColaborador.findPassword(email);
    }


    ///////API
    
    /**
     * Regista um gestor como utilizador da aplicacao
     * @param colaborador
     * @return
     * @throws SQLException 
     */
    public boolean registarGestorComoUtilizador(Colaborador colaborador) throws SQLException {
        String nome = colaborador.getNome();
        Email email = colaborador.getEmail();
        Password password = colaborador.getPassword();

        return UsersAPI.getInstance().registerUserWithRoles(email, nome, password, "gestor");
    }

    /**
     * Regista um colaborador como utilizador da aplicacao
     * @param colaborador
     * @return
     * @throws SQLException 
     */
    public boolean registarColaboradorComoUtilizador(Colaborador colaborador) throws SQLException {
        String nome = colaborador.getNome();
        Email email = colaborador.getEmail();
        Password password = colaborador.getPassword();

        return UsersAPI.getInstance().registerUserWithRoles(email, nome, password, "colaborador");
    }



}
