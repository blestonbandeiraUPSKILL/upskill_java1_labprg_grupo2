package com.grupo2.t4j.persistence;


import com.grupo2.t4j.model.EnderecoPostal;

import java.sql.SQLException;
import java.util.List;

public interface RepositorioEnderecoPostal {

    void save(String codigoEnderecoPostal, String arruamento, String numeroPorta, String localidade, String codigoPostal);

    boolean save(EnderecoPostal enderecoPostal) throws SQLException;

    List<EnderecoPostal> getAll();

    EnderecoPostal findByCodigo(String codigo);


    //update, delete
}
