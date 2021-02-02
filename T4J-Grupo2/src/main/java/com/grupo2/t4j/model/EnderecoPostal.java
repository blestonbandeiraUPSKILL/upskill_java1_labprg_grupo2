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

    private String arruamento;
    private String numeroPorta;
    private String localidade;
    private String codigoPostal;
    

    public EnderecoPostal(String arruamento, String numeroPorta, String localidade, String codigoPostal) {
        setArruamento(arruamento);
        setPorta(numeroPorta);
        setLocalidade(localidade);
        setCodigoPostal(codigoPostal);        
    }
    
    public EnderecoPostal(EnderecoPostal endereco){
        setArruamento(endereco.arruamento);
        setPorta(endereco.numeroPorta);
        setLocalidade(endereco.localidade);
        setCodigoPostal(endereco.codigoPostal); 
    }

    public String getArruamento() {
        return arruamento;
    }

    public String getPorta() {
        return numeroPorta;
    }

    public String getLocalidade() {
        return localidade;
    }
    
    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setArruamento(String arruamento) {
        this.arruamento = arruamento;
    }
    
    public void setPorta(String numeroPorta) {
        this.numeroPorta = numeroPorta;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }
    
    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }
}
