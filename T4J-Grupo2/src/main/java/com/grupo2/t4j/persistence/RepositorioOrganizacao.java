package com.grupo2.t4j.persistence;

import com.grupo2.t4j.model.*;

import java.sql.SQLException;
import java.util.ArrayList;

public interface RepositorioOrganizacao {

    boolean save (String nif, String nome, String website, String telefone,
                  String emailOrganizacao, String emailGestor, String arruamento, String numeroPorta, String localidade, String codPostal,
              String nomeGestor, String pass, String telefoneGestor, String funcao) throws SQLException;

    boolean save(Organizacao organizacao);

    Organizacao findByNif(String nif) throws SQLException;

    public ArrayList<Organizacao> getAll();

    //update, delete


}
