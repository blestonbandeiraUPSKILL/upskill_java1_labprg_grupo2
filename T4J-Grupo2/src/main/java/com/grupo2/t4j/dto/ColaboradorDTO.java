package com.grupo2.t4j.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.grupo2.t4j.model.Email;
import com.grupo2.t4j.model.Password;

@JsonPropertyOrder({"funcao", "telefone"})
@JacksonXmlRootElement(localName = "colaborador")

public class ColaboradorDTO  extends UtilizadorDTO{

    @JacksonXmlProperty(localName = "funcao")
    private String funcao;

    @JacksonXmlProperty(localName = "telefone")
    private String telefone;

    public ColaboradorDTO(){
    }

    public ColaboradorDTO(String nome, Email email, Password password, String funcao, String telefone) {
        super(nome, email, password);
        setFuncao(funcao);
        setTelefone(telefone);
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

    public String getFuncao() {
        return funcao;
    }

    public String getTelefone() {
        return telefone;
    }
}
