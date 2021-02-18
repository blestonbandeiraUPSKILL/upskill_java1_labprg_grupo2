package com.grupo2.t4j.persistence;

import com.grupo2.t4j.model.AreaActividade;

import java.util.List;

public interface RepositorioEnderecoPostal {

    void save();

    boolean save(AreaActividade areaActividade);

    List<AreaActividade> getAll();

    AreaActividade findByCodigo(String codigo);


    //update, delete
}
