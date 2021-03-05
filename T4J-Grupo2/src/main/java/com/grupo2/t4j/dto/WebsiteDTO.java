package com.grupo2.t4j.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.grupo2.t4j.exception.WebsiteInvalidoException;
import org.apache.commons.validator.UrlValidator;

@JsonPropertyOrder({"website"})

public class WebsiteDTO {

    @JacksonXmlProperty(localName = "website")
    private String website;

    public WebsiteDTO (String website){
        setWebsite(website);
    }

    public WebsiteDTO (WebsiteDTO website){
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
