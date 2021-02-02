/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.model;

/**
 *
 * @author acris
 */
public class EnderecoPostal {

    private String enderecoLocal;
    private String enderecoPostal;
    private String localidade;

    public EnderecoPostal(String enderecoLocal, String enderecoPostal, String localidade) {
        this.enderecoLocal = enderecoLocal;
        this.enderecoPostal = enderecoPostal;
        this.localidade = localidade;
    }

    public String getEnderecoLocal() {
        return enderecoLocal;
    }

    public String getEnderecoPostal() {
        return enderecoPostal;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setEnderecoLocal(String enderecoLocal) {
        this.enderecoLocal = enderecoLocal;
    }

    public void setEnderecoPostal(String enderecoPostal) {
        this.enderecoPostal = enderecoPostal;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }
}
