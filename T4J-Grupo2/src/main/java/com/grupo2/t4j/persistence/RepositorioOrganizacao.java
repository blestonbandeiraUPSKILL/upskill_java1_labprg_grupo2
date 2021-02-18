package com.grupo2.t4j.persistence;

import com.grupo2.t4j.model.*;

import java.sql.SQLException;
import java.util.ArrayList;

public interface RepositorioOrganizacao {

    void save(String nif, String nome, Website website,
              String telefone, Email emailOrganizacao,
              EnderecoPostal enderecoPostal, Colaborador gestor) throws SQLException;

    boolean save(Organizacao organizacao);

    Organizacao findByNif(String nif) throws SQLException;

    public ArrayList<Organizacao> getAll();

    //update, delete


}
