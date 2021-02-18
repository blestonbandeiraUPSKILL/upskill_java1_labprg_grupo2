package com.grupo2.t4j.persistence.database;

import com.grupo2.t4j.model.EnderecoPostal;
import com.grupo2.t4j.persistence.RepositorioEnderecoPostal;

import java.util.List;

public class RepositorioEnderecoPostalDatabase implements RepositorioEnderecoPostal {
    @Override
    public void save(String codigoEnderecoPostal, String arruamento, String numeroPorta, String localidade, String codigoPostal) {

    }

    @Override
    public boolean save(EnderecoPostal enderecoPostal) {
        return false;
    }

    @Override
    public List<EnderecoPostal> getAll() {
        return null;
    }

    @Override
    public EnderecoPostal findByCodigo(String codigo) {
        return null;
    }
}
