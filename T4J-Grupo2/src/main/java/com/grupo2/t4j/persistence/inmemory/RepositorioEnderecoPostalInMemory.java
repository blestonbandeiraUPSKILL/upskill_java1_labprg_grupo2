package com.grupo2.t4j.persistence.inmemory;

import com.grupo2.t4j.exception.EnderecoPostalDuplicadoException;
import com.grupo2.t4j.model.EnderecoPostal;
import com.grupo2.t4j.persistence.RepositorioEnderecoPostal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RepositorioEnderecoPostalInMemory implements Serializable, RepositorioEnderecoPostal {

    /**
     * Define uma instância estática do Repositório em que estão registados todos
     * os Endereços Postais da plataforma
     */
    private static RepositorioEnderecoPostalInMemory repositorioenderecoPostalInMemory;

    /**
     * Define o atributo da classe RepositorioEnderecoPostal como uma lista de
     * tipos da classe EnderecoPostal
     */
    private List<EnderecoPostal> listaEnderecoPostal;

    /**
     * Inicializa o Repositório de Endereços Postais
     */
    RepositorioEnderecoPostalInMemory() {
        listaEnderecoPostal = new ArrayList<>();
    }

    /**
     * Devolve uma instância estática do Repositório de Endereços Postais
     *
     * @return RepositorioEnderecoPostal
     */
    public static RepositorioEnderecoPostalInMemory getInstance() {
        if (repositorioenderecoPostalInMemory == null) {
            repositorioenderecoPostalInMemory = new RepositorioEnderecoPostalInMemory();
        }
        return repositorioenderecoPostalInMemory;
    }

    @Override
    public void save(int codigoEnderecoPostal, String arruamento, String numeroPorta, String localidade, String codigoPostal) {
        EnderecoPostal ep = findById(codigoEnderecoPostal);
        if (ep == null) {
            listaEnderecoPostal.add(ep);
        } else {
            throw new EnderecoPostalDuplicadoException(codigoEnderecoPostal + ": Endereço Postal já registado!");
        }
    }

    @Override
    public boolean save(EnderecoPostal enderecoPostal) {
        EnderecoPostal ep = findById(enderecoPostal.getIdEnderecoPostal());
        if (ep == null) {
            return listaEnderecoPostal.add(enderecoPostal);
        } else {
            throw new EnderecoPostalDuplicadoException(ep.getCodigoPostal() + ": Endereço Postal já registado!");
        }

    }

    @Override
    public List<EnderecoPostal> getAll() {
        return listaEnderecoPostal;
    }

    @Override
    public EnderecoPostal findById(int codigo) {
        for (int i = 0; i < this.listaEnderecoPostal.size(); i++) {
            EnderecoPostal enderecoPostal = this.listaEnderecoPostal.get(i);
            if (enderecoPostal.getIdEnderecoPostal() == codigo) {
                return enderecoPostal;
            }
        }
        return null;
    }

}
