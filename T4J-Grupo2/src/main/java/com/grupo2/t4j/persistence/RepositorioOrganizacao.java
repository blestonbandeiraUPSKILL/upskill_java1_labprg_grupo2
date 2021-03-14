package com.grupo2.t4j.persistence;

import java.sql.SQLException;

public interface RepositorioOrganizacao {

    boolean save (String arruamento, String numeroPorta, String localidade, String codigoPostal,
                  String nif, String nome, String website, String telefone, String emailOrganizacao,
                  String emailGestor, String funcaoGestor, String telefoneGestor) throws SQLException;

}
