package com.grupo2.t4j.persistence;

import com.grupo2.t4j.model.*;

import java.util.ArrayList;

public interface RepositorioOrganizacao {

    void save(String nif, String nome, Website website,
              String telefone, Email emailOrganizacao,
              Email emailGestor, String arruamento,
              String numeroPorta, String localidade,
              String codigoPostal, String nomeGestor,
              Password password, Rolename rolename,
              String telefoneGestor, String funcaoGestor);

    Organizacao findByNif(String nif);

    public ArrayList<Organizacao> getAll();

    //update, delete


}
