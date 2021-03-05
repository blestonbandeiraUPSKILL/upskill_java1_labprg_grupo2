/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.domain;

import com.grupo2.t4j.exception.WebsiteInvalidoException;
import org.apache.commons.validator.UrlValidator;

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
            this.website = String.valueOf(website);
        }
        else{
            throw new WebsiteInvalidoException("O endereço do website é inválido!");
        }
    }
    
    public String getWebsiteText(){
        return website;
    }
    
    public boolean eURL(String url) {
        UrlValidator urlValidator = new UrlValidator();
        return urlValidator.isValid(url);

    }
}
