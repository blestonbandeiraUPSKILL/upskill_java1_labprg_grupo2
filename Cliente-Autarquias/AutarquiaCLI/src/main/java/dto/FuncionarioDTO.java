/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

/**
 * Esta vai corresponder à classe Colaborador
 */

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import model.Data;

/**
 *
 * @author blest
 */

@JsonPropertyOrder({"numeroFuncionario", "cargo"})
@JacksonXmlRootElement(localName = "funcionario")

public class FuncionarioDTO extends PessoaDTO{

    @JacksonXmlProperty(localName = "numero")
    private int numeroFuncionario;

    @JacksonXmlProperty(localName = "cargo")
    private String cargo;

    public FuncionarioDTO() {
    }

    public FuncionarioDTO(long nif, String nome, DataDTO nascimento, int numeroFuncionario, String cargo) {
        super(nif, nome, nascimento);
        this.numeroFuncionario = numeroFuncionario;
        this.cargo = cargo;
    }

    public int getNumeroFuncionario() {
        return numeroFuncionario;
    }

    public String getCargo() {
        return cargo;
    }

    public void setNumeroFuncionario(int numeroFuncionario) {
        this.numeroFuncionario = numeroFuncionario;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
}
