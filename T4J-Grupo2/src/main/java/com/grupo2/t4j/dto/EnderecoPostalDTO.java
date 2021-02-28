package com.grupo2.t4j.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.grupo2.t4j.exception.NomeInvalidoException;
import com.grupo2.t4j.model.EnderecoPostal;

@JsonPropertyOrder({"idEnderecoPostal", "arruamento", "numeroPorta", "localidade", "codigoPostal"})

public class EnderecoPostalDTO {

    @JacksonXmlProperty(localName = "idEnderecoPostal")
    private int idEnderecoPostal;

    @JacksonXmlProperty(localName = "arruamento")
    private String arruamento;

    @JacksonXmlProperty(localName = "numeroPorta")
    private String numeroPorta;

    @JacksonXmlProperty(localName = "localidade")
    private String localidade;

    @JacksonXmlProperty(localName = "codigoPostal")
    private String codigoPostal;

    public EnderecoPostalDTO() {
    }

    public EnderecoPostalDTO(int codigoEnderecoPostal, String arruamento, String numeroPorta, String localidade, String codigoPostal) {
        setIdEnderecoPostal(codigoEnderecoPostal);
        setArruamento(arruamento);
        setPorta(numeroPorta);
        setLocalidade(localidade);
        setCodigoPostal(codigoPostal);
    }

    public EnderecoPostalDTO(EnderecoPostalDTO enderecoPostalDTO){
        setIdEnderecoPostal(enderecoPostalDTO.idEnderecoPostal);
        setArruamento(enderecoPostalDTO.arruamento);
        setPorta(enderecoPostalDTO.numeroPorta);
        setLocalidade(enderecoPostalDTO.localidade);
        setCodigoPostal(enderecoPostalDTO.codigoPostal);
    }

    public EnderecoPostalDTO(String arruamento, String numeroPorta, String localidade, String codigoPostal) {
        setArruamento(arruamento);
        setPorta(numeroPorta);
        setLocalidade(localidade);
        setCodigoPostal(codigoPostal);
    }

    public void setIdEnderecoPostal(int idEnderecoPostal) {
        if (idEnderecoPostal < 0 ) {
            throw new NomeInvalidoException("O código do Endereço Postal é invalido");
        }
        this.idEnderecoPostal = idEnderecoPostal;
    }

    public void setArruamento(String arruamento) {
        if (arruamento == null || arruamento.trim().isEmpty()) {
            throw new NomeInvalidoException("Arruamento é inválido!");
        }
        this.arruamento = arruamento;
    }

    public void setPorta(String numeroPorta) {
        if (numeroPorta == null || numeroPorta.trim().isEmpty()) {
            throw new NomeInvalidoException("Número de porta é inválido!");
        }
        this.numeroPorta = numeroPorta;
    }

    public void setLocalidade(String localidade) {
        if (localidade == null || localidade.trim().isEmpty()) {
            throw new NomeInvalidoException("Localidade é inválida!");
        }
        this.localidade = localidade;
    }

    public void setCodigoPostal(String codigoPostal) {
        if (eCPValido(codigoPostal)){
            this.codigoPostal = codigoPostal;
        }
        else{
            throw new NomeInvalidoException("Código Postal é inválido!");
        }
    }

    public int getIdEnderecoPostal() {
        return idEnderecoPostal;
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

    public static boolean eCPValido(String codigoPostal){
        char [] cp = codigoPostal.toCharArray();
        boolean eValidoP1 = false;
        boolean eValidoP2 = false;
        if(codigoPostal.length() == 8 && (cp[4] == '-')){
            for(int i = 0; i < 4; i++){
                if(Character.isDigit(cp[i])){
                    eValidoP1 = true;
                }
            }
            for(int i = 5; i < 8; i++){
                if(Character.isDigit(cp[i])){
                    eValidoP2 = true;
                }
            }
        }
        return (eValidoP1 && eValidoP2);
    }
}
