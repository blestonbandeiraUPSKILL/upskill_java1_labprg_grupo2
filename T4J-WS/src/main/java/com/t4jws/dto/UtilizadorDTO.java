package com.t4jws.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;



@JsonPropertyOrder({"nome", "email", "password", "rolename"})
@JsonRootName("utilizador")

public class UtilizadorDTO {

    @JsonProperty("nome")
    private String nome;

    @JsonProperty("email")
    private EmailDTO email;

    @JsonProperty("password")
    private PasswordDTO passwordDTO;

    @JsonProperty("rolename")
    private String rolename;

    public UtilizadorDTO() {
    }

    public UtilizadorDTO(EmailDTO email, String nome, PasswordDTO passwordDTO) {
        this.nome = nome;
        setEmail(email);
        setPassword(passwordDTO);
    }


    public UtilizadorDTO(UtilizadorDTO utilizadorDTO) {
        this.email = utilizadorDTO.getEmail();
        this.nome = utilizadorDTO.getNome();
        this.passwordDTO = utilizadorDTO.getPassword();
        this.rolename = utilizadorDTO.getRolename();
    }

    public UtilizadorDTO(String emailUtilizador, String nome, String passwordDTO, String rolename) {
        setEmail(new EmailDTO(emailUtilizador));
        setNome(nome);
        setPassword(new PasswordDTO(passwordDTO));
        this.rolename = rolename;

    }

    public UtilizadorDTO(String nome, String email) {
        setNome(nome);
        setEmail(new EmailDTO(email));
    }

    public final void setNome(String nome){
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome é inválido!");
        }
        this.nome = nome;
    }

    public final void setEmail(EmailDTO email){
        this.email = email;
    }

    public final void setPassword(PasswordDTO passwordDTO){
        this.passwordDTO = passwordDTO;
    }

    public void setRolename(String rolename){
        this.rolename = rolename;
    }

    public String getNome() {
        return nome;
    }

    public EmailDTO getEmail() {
        return email;
    }

    public PasswordDTO getPassword() {
        return passwordDTO;
    }

    public String getRolename() {
        return rolename;
    }
}
