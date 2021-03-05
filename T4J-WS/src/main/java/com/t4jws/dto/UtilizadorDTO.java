package com.t4jws.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;



public class UtilizadorDTO {

    private String nome;

    private EmailDTO emailDTO;

    private PasswordDTO passwordDTO;

    private RolenameDTO rolenameDTO;

    public UtilizadorDTO() {
    }

    public UtilizadorDTO(EmailDTO emailDTO, String nome, PasswordDTO passwordDTO) {
        this.nome = nome;
        setEmailDTO(emailDTO);
        setPasswordDTO(passwordDTO);
    }


    public UtilizadorDTO(UtilizadorDTO utilizadorDTO) {
        this.emailDTO = utilizadorDTO.getEmailDTO();
        this.nome = utilizadorDTO.getNome();
        this.passwordDTO = utilizadorDTO.getPasswordDTO();
        this.rolenameDTO = utilizadorDTO.getRolenameDTO();
    }

    public UtilizadorDTO(String emailUtilizador, String nome, String passwordDTO, RolenameDTO rolenameDTO) {
        setEmailDTO(new EmailDTO(emailUtilizador));
        setNome(nome);
        setPasswordDTO(new PasswordDTO(passwordDTO));
        setRolenameDTO(rolenameDTO);

    }

    public UtilizadorDTO(String nome, String email) {
        setNome(nome);
        setEmailDTO(new EmailDTO(email));
    }

    public final void setNome(String nome){
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome é inválido!");
        }
        this.nome = nome;
    }

    public final void setEmailDTO(EmailDTO email){
        this.emailDTO = email;
    }

    public final void setPasswordDTO(PasswordDTO passwordDTO){
        this.passwordDTO = passwordDTO;
    }

    public void setRolenameDTO(RolenameDTO rolenameDTO){
        this.rolenameDTO = rolenameDTO;
    }

    public String getNome() {
        return nome;
    }

    public EmailDTO getEmailDTO() {
        return emailDTO;
    }

    public PasswordDTO getPasswordDTO() {
        return passwordDTO;
    }

    public RolenameDTO getRolenameDTO() {
        return rolenameDTO;
    }
}
