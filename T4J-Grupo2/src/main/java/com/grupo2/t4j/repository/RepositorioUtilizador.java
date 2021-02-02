/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.grupo2.t4j.repository;
/**
 *
 * @author CAD
 */

import com.grupo2.t4j.model.*;
import com.grupo2.t4j.exception.*;
import java.util.ArrayList;
import java.util.List;

public class RepositorioUtilizador {
    private static RepositorioUtilizador instance;
    private List<Utilizador> listaUtilizadores = new ArrayList<>();
    
    /*public static RepositorioUtilizador getInstance(){
        if (instance == null){
            instance = new RepositorioUtilizador();
        }
        return instance;
    }*/
    
    public RepositorioUtilizador(List<Utilizador> listaUtilizadores){
        this.listaUtilizadores = listaUtilizadores;
    }
    public void addUtilizador(Utilizador utilizador) throws UtilizadorDuplicadoException {
        Utilizador u = getUtilizadorByEmail(utilizador.getEmail());
        if (u == null) {
            this.listaUtilizadores.add(utilizador);
        } else {
            throw new UtilizadorDuplicadoException(u.getEmail() + ": Utilizador já registado");
        }
    }
    
    private Utilizador getUtilizadorByEmail(Email email) {
        Utilizador utilizador = null;
        for (int i = 0; i < this.listaUtilizadores.size(); i++) {
            utilizador = this.listaUtilizadores.get(i);
            if (utilizador.getEmail().equals(email)) {
                Utilizador copia = new Utilizador(utilizador);
                return copia;
            }
        }
        return null;
    }
}

