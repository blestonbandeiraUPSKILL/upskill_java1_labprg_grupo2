package com.grupo2.t4j.repository;

public class RepositorioTarefa {
    private static RepositorioTarefa instance;
    
    
    public static RepositorioTarefa getInstance(){
        if (instance == null){
            instance = new RepositorioTarefa();
        }
        return instance;
    }
    
}
