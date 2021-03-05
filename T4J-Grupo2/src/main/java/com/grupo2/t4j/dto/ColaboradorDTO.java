package com.grupo2.t4j.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JsonPropertyOrder({"funcao", "telefone", "nifOrganizacao"})
@JacksonXmlRootElement(localName = "colaborador")

public class ColaboradorDTO  extends UtilizadorDTO{

    @JacksonXmlProperty(localName = "funcao")
    private String funcao;

    @JacksonXmlProperty(localName = "telefone")
    private String telefone;

    @JacksonXmlProperty(localName = "nifOrganizacao")
    private String nifOrganizacao;

    public ColaboradorDTO(){
    }

    public ColaboradorDTO(EmailDTO email, String nome, PasswordDTO password, String funcao, String telefone) {
        super(email, nome, password);
        setFuncao(funcao);
        setTelefone(telefone);
    }

    public ColaboradorDTO(EmailDTO email, String nome, PasswordDTO password, String funcao, String telefone, String nifOrganizacao) {
        super(email, nome, password);
        setFuncao(funcao);
        setTelefone(telefone);
        setNifOrganizacao(nifOrganizacao);
    }

    public ColaboradorDTO(ColaboradorDTO colaboradorDTO) {
        super(colaboradorDTO.getEmail(), colaboradorDTO.getNome(), colaboradorDTO.getPassword());
        this.funcao = colaboradorDTO.getFuncao();
        this.telefone = colaboradorDTO.getTelefone();
    }

    public ColaboradorDTO(EmailDTO email, String funcao, String telefone, String nifOrganizacao) {
        setEmail(email);
        setFuncao(funcao);
        setTelefone(telefone);
        this.nifOrganizacao = nifOrganizacao;
    }

    public ColaboradorDTO(UtilizadorDTO utilizadorDTO, String funcao, String telefone) {
        super(utilizadorDTO);
        setFuncao(funcao);
        setTelefone(telefone);
    }

    public ColaboradorDTO(String email, String nome, String funcao, String telefone, String nifOrganizacao) {
        super(nome, email);
        setFuncao(funcao);
        setTelefone(telefone);
        setNifOrganizacao(nifOrganizacao);
    }

    public final void setFuncao(String funcao){
        if (funcao == null || funcao.trim().isEmpty()) {
            throw new IllegalArgumentException("Função é inválida!");
        }
        this.funcao = funcao;
    }

    public final void setTelefone(String telefone) {
        int numero = Integer.parseInt(telefone);
        if (numero < 100000000 || numero > 999999999) {
            throw new IllegalArgumentException("Número é inválido!");
        }
        this.telefone = telefone;
    }

    public void setNifOrganizacao(String nifOrganizacao) {
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
