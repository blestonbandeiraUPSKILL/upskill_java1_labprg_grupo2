package com.grupo2.t4j.controller;

import com.grupo2.t4j.api.UsersAPI;
import com.grupo2.t4j.files.FicheiroRepositorioColaborador;
import com.grupo2.t4j.model.AlgoritmoGeradorPasswords;
import com.grupo2.t4j.model.Colaborador;
import com.grupo2.t4j.model.Email;
import com.grupo2.t4j.model.Password;
import com.grupo2.t4j.persistence.FabricaRepositorios;
import com.grupo2.t4j.persistence.RepositorioColaborador;
import com.grupo2.t4j.persistence.RepositorioUtilizador;
import com.grupo2.t4j.persistence.database.FabricaRepositoriosDatabase;
import com.grupo2.t4j.persistence.inmemory.RepositorioColaboradorInMemory;

import java.io.File;
import java.sql.SQLException;
import java.util.List;

public class RegistarColaboradorController {

    //private FabricaRepositorios fabricaRepositorios = new FabricaRepositoriosInMemory();
    private FabricaRepositorios fabricaRepositorios = new FabricaRepositoriosDatabase();
    private RepositorioColaborador repositorioColaborador = fabricaRepositorios.getRepositorioColaborador();
    private RepositorioUtilizador repositorioUtilizador = fabricaRepositorios.getRepositorioUtilizador();

    private AlgoritmoGeradorPasswords algoritmoGeradorPasswords;
    private RepositorioColaboradorInMemory repositorioColaboradorInMemory;
    private FicheiroRepositorioColaborador ficheiroC;

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

    public List<Colaborador> getAll() throws SQLException {
        return repositorioColaborador.getAll();
    }

    public String getNifOrganizacao(String email) throws SQLException {
        return repositorioColaborador.getNifOrganizacao(email);
    }

    public Colaborador findByEmail(String email) throws SQLException {
        return repositorioColaborador.findByEmail(email);
    }

    public Password findPassword(String email) throws SQLException {
        return repositorioColaborador.findPassword(email);
    }


    ///////API
    public boolean registarGestorComoUtilizador(Colaborador colaborador) throws SQLException {
        String nome = colaborador.getNome();
        Email email = colaborador.getEmail();
        Password password = colaborador.getPassword();

        return UsersAPI.getInstance().registerUserWithRoles(email, nome, password, "gestor");
    }

    public boolean registarColaboradorComoUtilizador(Colaborador colaborador) throws SQLException {
        String nome = colaborador.getNome();
        Email email = colaborador.getEmail();
        Password password = colaborador.getPassword();

        return UsersAPI.getInstance().registerUserWithRoles(email, nome, password, "colaborador");
    }


    //////FICHEIROS////////
    public RegistarColaboradorController() {
        ficheiroC = new FicheiroRepositorioColaborador();
        
        desserializar();
    }
    public boolean serializar() {
        return ficheiroC.serializar(repositorioColaboradorInMemory);
    }

    public boolean serializar(File ficheiroExportar) {
        return ficheiroC.serializar(ficheiroExportar, repositorioColaboradorInMemory);
    }

    public void desserializar() {
        repositorioColaboradorInMemory = ficheiroC.desserializar();
    }

    public int desserializar(File ficheiroImportar) {
        RepositorioColaboradorInMemory listaColaboradorImportada = ficheiroC.desserializar(ficheiroImportar);

        return repositorioColaboradorInMemory.adicionarListaColaborador(listaColaboradorImportada);
    }


}
