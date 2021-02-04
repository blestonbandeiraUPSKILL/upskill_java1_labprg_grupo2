package com.grupo2.t4j.repository;

import java.io.Serializable;

public class RepositorioTarefa implements Serializable{
    private static RepositorioTarefa instance;
    
    
    public static RepositorioTarefa getInstance(){
        if (instance == null){
            instance = new RepositorioTarefa();
        }
        return instance;
    }
    
}
