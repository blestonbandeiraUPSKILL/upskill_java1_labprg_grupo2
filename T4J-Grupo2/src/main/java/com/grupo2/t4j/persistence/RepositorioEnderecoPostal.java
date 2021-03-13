package com.grupo2.t4j.persistence;


import com.grupo2.t4j.domain.EnderecoPostal;

import java.sql.SQLException;
import java.util.List;

public interface RepositorioEnderecoPostal {

    void save(int codigoEnderecoPostal, String arruamento, String numeroPorta, String localidade, String codigoPostal);

    boolean save(EnderecoPostal enderecoPostal) throws SQLException;

    List<EnderecoPostal> getAll() throws SQLException;

    EnderecoPostal findById(int codigo) throws SQLException;

}
