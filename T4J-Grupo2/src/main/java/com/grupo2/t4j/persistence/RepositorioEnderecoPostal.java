package com.grupo2.t4j.persistence;


import com.grupo2.t4j.model.EnderecoPostal;

import java.util.List;

public interface RepositorioEnderecoPostal {

    void save(String arruamento, String numeroPorta, String localidade, String codigoPostal);

    boolean save(EnderecoPostal enderecoPostal);

    List<EnderecoPostal> getAll();

    EnderecoPostal findByCodigo(String codigo);


    //update, delete
}
