package com.grupo2.t4j.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.grupo2.t4j.domain.Email;
import com.grupo2.t4j.domain.EnderecoPostal;
import com.grupo2.t4j.domain.Password;
import com.grupo2.t4j.exception.NifInvalidoException;

public class FreelancerDTO extends UtilizadorDTO{

    private String nif;
    private String telefone;
    private int idEnderecoPostal;
    private EnderecoPostalDTO enderecoPostalDTO;

    public FreelancerDTO(){
    }

    public FreelancerDTO(Email email, String nome, Password password,
                         String nif, String telefone, int idEnderecoPostal){
        super(email, nome, password);
        this.nif = nif;
        this.telefone = telefone;
        this.idEnderecoPostal = idEnderecoPostal;
    }

    public String getNif(){
        return nif;
    }

    public String getTelefone(){
        return telefone;
    }

    public int getIdEnderecoPostal(){
        return idEnderecoPostal;
    }

    public EnderecoPostalDTO getEnderecoPostalDTO() {
        return enderecoPostalDTO;
    }

    @Override
    public String toString() {
        return String.format("Nome: %-20s |Email: %-20s |NIF: %-10s", super.getNome(),
                super.getEmail().getEmailText(), nif);
    }
}

