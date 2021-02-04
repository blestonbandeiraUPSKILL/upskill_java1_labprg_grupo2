/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.model;

import org.apache.commons.validator.UrlValidator;

import static org.apache.commons.validator.UrlValidator.ALLOW_ALL_SCHEMES;
import static org.apache.commons.validator.UrlValidator.NO_FRAGMENTS;

/**
 *
 * @author CAD
 */
public class Website {
    
    private String website;
    
    public Website (String website){
        setWebsite(website);
    }
    
    public Website (Website website){
        setWebsite(website.website);
    }
    
    public void setWebsite(String website){
        if(eURL(website)){
            this.website = website;
        }
        else{
            throw new IllegalArgumentException("O endereço do website é inválido!");
        }
    }
    
    // Dica de: https://qastack.com.br/programming/2230676/how-to-check-for-a-valid-url-in-java
    
    public boolean eURL(String url) {
        UrlValidator urlValidator = new UrlValidator();
        return urlValidator.isValid(url);

    }
}
