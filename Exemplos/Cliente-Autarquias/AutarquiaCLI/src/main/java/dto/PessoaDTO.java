/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 *
 * @author blest
 */

@JsonPropertyOrder({"nif", "nome", "nascimento"})
@JacksonXmlRootElement(localName = "pessoa")

public class PessoaDTO {

    @JacksonXmlProperty(localName = "nif")
    private long nif;

    @JacksonXmlProperty(localName = "nome")
    private String nome;

    @JacksonXmlProperty(localName = "nascimento")
    private DataDTO nascimento;

    public PessoaDTO() {
    }

    public PessoaDTO(long nif, String nome, DataDTO nascimento) {
        this.nif = nif;
        this.nome = nome;
        this.nascimento = nascimento;
    }

    public long getNif() {
        return nif;
    }

    public String getNome() {
        return nome;
    }

    public DataDTO getNascimento() {
        return nascimento;
    }

    public void setNif(long nif) {
        this.nif = nif;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setNascimento(DataDTO nascimento) {
        this.nascimento = nascimento;
    }
}
