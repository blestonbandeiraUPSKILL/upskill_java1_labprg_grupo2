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

public class EnderecoPostal {

    private String arruamento;
    private String numeroPorta;
    private String localidade;
    private String codigoPostal;
    

    public EnderecoPostal(String arruamento, String numeroPorta, String localidade, String codigoPostal) {
        setArruamento(arruamento);
        setPorta(numeroPorta);
        setLocalidade(localidade);
        setCodigoPostal(codigoPostal);        
    }
  
    public EnderecoPostal(EnderecoPostal endereco){
        setArruamento(endereco.arruamento);
        setPorta(endereco.numeroPorta);
        setLocalidade(endereco.localidade);
        setCodigoPostal(endereco.codigoPostal); 
    }

    public String getArruamento() {
        return arruamento;
    }

    public String getPorta() {
        return numeroPorta;
    }

    public String getLocalidade() {
        return localidade;
    }
    
    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setArruamento(String arruamento) {
        if (arruamento == null || arruamento.trim().isEmpty()) {
            throw new NomeInvalidoException("Arruamento é inválido!");
        }
        this.arruamento = arruamento;
    }
    
    public void setPorta(String numeroPorta) {
        if (numeroPorta == null || numeroPorta.trim().isEmpty()) {
            throw new NomeInvalidoException("Número de porta é inválido!");
        }
        this.numeroPorta = numeroPorta;
    }

    public void setLocalidade(String localidade) {
        if (localidade == null || localidade.trim().isEmpty()) {
            throw new NomeInvalidoException("Localidade é inválida!");
        }
        this.localidade = localidade;
    }
    
    public void setCodigoPostal(String codigoPostal) {
        if (eCPValido(codigoPostal)){
            this.codigoPostal = codigoPostal;
        }
        else{
            throw new NomeInvalidoException("Código Postal é inválido!");        
        }
    }
    
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
