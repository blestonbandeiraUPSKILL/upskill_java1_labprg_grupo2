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

    private String arruamento;
    private String numeroPorta;
    private String localidade;
    private String codigoPostal;
    
    /**
     *
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
    }
  
    /**
     *
     * @param endereco
     */
    public EnderecoPostal(EnderecoPostal endereco){
        setArruamento(endereco.arruamento);
        setPorta(endereco.numeroPorta);
        setLocalidade(endereco.localidade);
        setCodigoPostal(endereco.codigoPostal); 
    }

    /**
     *
     * @return
     */
    public String getArruamento() {
        return arruamento;
    }

    /**
     *
     * @return
     */
    public String getPorta() {
        return numeroPorta;
    }

    /**
     *
     * @return
     */
    public String getLocalidade() {
        return localidade;
    }
    
    /**
     *
     * @return
     */
    public String getCodigoPostal() {
        return codigoPostal;
    }

    /**
     *
     * @param arruamento
     */
    public void setArruamento(String arruamento) {
        if (arruamento == null || arruamento.trim().isEmpty()) {
            throw new NomeInvalidoException("Arruamento é inválido!");
        }
        this.arruamento = arruamento;
    }
    
    /**
     *
     * @param numeroPorta
     */
    public void setPorta(String numeroPorta) {
        if (numeroPorta == null || numeroPorta.trim().isEmpty()) {
            throw new NomeInvalidoException("Número de porta é inválido!");
        }
        this.numeroPorta = numeroPorta;
    }

    /**
     *
     * @param localidade
     */
    public void setLocalidade(String localidade) {
        if (localidade == null || localidade.trim().isEmpty()) {
            throw new NomeInvalidoException("Localidade é inválida!");
        }
        this.localidade = localidade;
    }
    
    /**
     *
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
    
    @Override
    public String toString(){
        return String.format("Endereço: %nArruamento: %s"
                + " %nNúmero de Porta: %s %nLocalidade: %s %nCódigo Postal: %s", arruamento, 
                numeroPorta,localidade, codigoPostal);
    }
    
    /**
     *
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
