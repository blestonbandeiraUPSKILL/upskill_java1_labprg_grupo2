package com.grupo2.t4j.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.grupo2.t4j.exception.NifInvalidoException;

@JsonPropertyOrder ({"nif", "telefone", "idEnderecoPostal", "enderecoPostal"})

public class FreelancerDTO extends UtilizadorDTO{

    @JacksonXmlProperty(localName = "nif")
    private String nif;

    @JacksonXmlProperty(localName = "telefone")
    private String telefone;

    @JacksonXmlProperty(localName = "idEnderecoPostal")
    private int idEnderecoPostal;

    @JacksonXmlProperty(localName = "enderecoPostal")
    private EnderecoPostalDTO enderecoPostalDTO;

    public FreelancerDTO(){
    }

    public FreelancerDTO(EmailDTO emailDTO, String nome, PasswordDTO password,
                      String nif, String telefone, int idEnderecoPostal){
        super(emailDTO, nome, password);
        setNif(nif);
        setTelefone(telefone);
        this.idEnderecoPostal = idEnderecoPostal;
    }

    public FreelancerDTO(FreelancerDTO freelancer){
        super(freelancer.getEmail(), freelancer.getNome(), freelancer.getPassword());
        setNif(freelancer.nif);
        setTelefone(freelancer.telefone);
        this.idEnderecoPostal = freelancer.idEnderecoPostal;
    }

    public FreelancerDTO(String email, String nome, String nif, String telefone, String password, String arruamento, String numeroPorta, String localidade, String codigoPostal) {
        setEmail(new EmailDTO(email));
        setTelefone(telefone);
        setNif(nif);
        setNome(nome);
        setEnderecoPostal(new EnderecoPostalDTO(arruamento, numeroPorta, localidade, codigoPostal));
        setPassword(new PasswordDTO(password));
    }

    public FreelancerDTO(String email, String nome, String telefone, String nif, String arruamento, String numeroPorta, String localidade, String codigoPostal) {
        setEmail(new EmailDTO(email));
        setNome(nome);
        setTelefone(telefone);
        setNif(nif);
        setEnderecoPostal(new EnderecoPostalDTO(arruamento, numeroPorta, localidade, codigoPostal));
    }

    public FreelancerDTO(String email, String nome, String telefone, String nif, int idEnderecoPostal) {
        setEmail(new EmailDTO(email));
        setNome(nome);
        setTelefone(telefone);
        setNif(nif);
        setIdEnderecoPostal(idEnderecoPostal);
    }

    public void setNif(String nif)throws NifInvalidoException {
        long numNif = Long.parseLong(nif);
        if (numNif >= 100000000 && numNif <= 999999999) {
            this.nif = nif;
        } else {
            throw new NifInvalidoException(nif + ": NIF inválido!");
        }
    }

    public void setTelefone(String telefone) {
        int numero = Integer.parseInt(telefone);
        if (numero < 100000000 || numero > 999999999) {
            throw new IllegalArgumentException("Número é inválido!");
        }
        this.telefone = telefone;
    }

    public void setEnderecoPostal(EnderecoPostalDTO enderecoPostalDTO) {
        if(enderecoPostalDTO != null) {
            this.enderecoPostalDTO = enderecoPostalDTO;
        }
    }

    public void setIdEnderecoPostal(int idEnderecoPostal) {
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
}

