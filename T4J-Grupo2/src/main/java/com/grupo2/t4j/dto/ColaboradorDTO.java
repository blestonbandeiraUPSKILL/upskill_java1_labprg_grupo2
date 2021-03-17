package com.grupo2.t4j.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.grupo2.t4j.domain.Email;
import com.grupo2.t4j.domain.Password;

public class ColaboradorDTO  extends UtilizadorDTO{

    private String funcao;
    private String telefone;
    private String nifOrganizacao;

    public ColaboradorDTO(){
    }

    public ColaboradorDTO(Email email, String nome, Password password, String funcao, String telefone, String nifOrganizacao) {
        super(email, nome, password);
        this.funcao = funcao;
        this.telefone = telefone;
        this.nifOrganizacao = nifOrganizacao;
    }

    public String getFuncao() {
        return funcao;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getNifOrganizacao() {
        return nifOrganizacao;
    }
}
