package com.grupo2.t4j.persistence.inmemory;

import com.grupo2.t4j.model.AreaActividade;
import com.grupo2.t4j.persistence.RepositorioEnderecoPostal;

import java.util.List;

public class RepositorioEnderecoPostalInMemory implements RepositorioEnderecoPostal {
    @Override
    public void save() {

    }

    @Override
    public boolean save(AreaActividade areaActividade) {
        return false;
    }

    @Override
    public List<AreaActividade> getAll() {
        return null;
    }

    @Override
    public AreaActividade findByCodigo(String codigo) {
        return null;
    }
}
