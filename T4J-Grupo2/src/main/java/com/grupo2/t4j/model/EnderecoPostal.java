/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.model;

/**
 *
 * @author CAD
 */

import  com.grupo2.t4j.exception.*;

/**
 *
 * @author Geral
 */
public class EnderecoPostal {

    /**
     * O identificador do Endereço Postal
     */
    private String idEnderecoPostal = "EndPostal - ";

    private int contador = 1;

    /**
     * O arruamento do endereço postal
     */
    private String arruamento;
    
    /**
     * O número da porta do endereço postal
     */
    private String numeroPorta;
    
    /**
     * A localidade do endereço postal
     */
    private String localidade;
    
    /**
     * O código postal 
     */
    private String codigoPostal;
    
    /**
     * Construtor completo de um endereço postal
     * @param arruamento
     * @param numeroPorta
     * @param localidade
     * @param codigoPostal
     */
    public EnderecoPostal(String arruamento, String numeroPorta, String localidade, String codigoPostal) {
        setArruamento(arruamento);
        setPorta(numeroPorta);
        setLocalidade(localidade);
        setCodigoPostal(codigoPostal);
        setIdEnderecoPostal(idEnderecoPostal);
    }
  
    /**
     * Construtor de um endereço postal que recebe como parâmetro outro endereço postal
     * @param endereco
     */
    public EnderecoPostal(EnderecoPostal endereco){
        setArruamento(endereco.arruamento);
        setPorta(endereco.numeroPorta);
        setLocalidade(endereco.localidade);
        setCodigoPostal(endereco.codigoPostal);
        setIdEnderecoPostal(idEnderecoPostal);
    }

    /**
     * Devolve o arruamento
     * @return arruamento
     */
    public String getArruamento() {
        return arruamento;
    }

    /**
     * Devolve a porta
     * @return numeroPorta
     */
    public String getPorta() {
        return numeroPorta;
    }

    /**
     * Devolve a localidade
     * @return localidade
     */
    public String getLocalidade() {
        return localidade;
    }
    
    /**
     * Devolve o código postal
     * @return codigoPostal
     */
    public String getCodigoPostal() {
        return codigoPostal;
    }

    public String getIdEnderecoPostal() {
        return idEnderecoPostal;
    }

    /**
     * Define o arruamento
     * @param arruamento
     */
    public void setArruamento(String arruamento) {
        if (arruamento == null || arruamento.trim().isEmpty()) {
            throw new NomeInvalidoException("Arruamento é inválido!");
        }
        this.arruamento = arruamento;
    }
    
    /**
     * Define o número da porta
     * @param numeroPorta
     */
    public void setPorta(String numeroPorta) {
        if (numeroPorta == null || numeroPorta.trim().isEmpty()) {
            throw new NomeInvalidoException("Número de porta é inválido!");
        }
        this.numeroPorta = numeroPorta;
    }

    /**
     * Define a localidade
     * @param localidade
     */
    public void setLocalidade(String localidade) {
        if (localidade == null || localidade.trim().isEmpty()) {
            throw new NomeInvalidoException("Localidade é inválida!");
        }
        this.localidade = localidade;
    }
    
    /** 
     * Define o código postal
     * @param codigoPostal
     */
    public void setCodigoPostal(String codigoPostal) {
        if (eCPValido(codigoPostal)){
            this.codigoPostal = codigoPostal;
        }
        else{
            throw new NomeInvalidoException("Código Postal é inválido!");        
        }
    }

    public void setIdEnderecoPostal(String idEnderecoPostal) {
        contador++;
        this.idEnderecoPostal = idEnderecoPostal + contador;
    }

    /**
     * Representação textual da classe EnderecoPostal
     * @return arruamento, número de porta, localidade e código postal
     */
    @Override
    public String toString(){
        return String.format("Arruamento: %s"
                + " %nNúmero de Porta: %s %nLocalidade: %s %nCódigo Postal: %s", arruamento, 
                numeroPorta,localidade, codigoPostal);
    }
    
    /**
     * Verifica o formato do código postal
     * @param codigoPostal
     * @return
     */
    public static boolean eCPValido(String codigoPostal){
        boolean eValido = false;
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
/* Regex: https://gist.github.com/jamesbar2/1c677c22df8f21e869cca7e439fc3f5b 
    if (codigoPostal != null && codigoPostal.length() > 0) {
        String expressao = "^\\d{4}[- ]{0,1}\\d{3}$";
        Pattern pattern = Pattern.compile(expressao);
        Matcher matcher = pattern.matcher(codigoPostal);
        if (matcher.matches()) {
            eValido = true;
        }
    }
*/
