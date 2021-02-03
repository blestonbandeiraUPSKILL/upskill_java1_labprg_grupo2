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

public class RepositorioColaborador {
    
    private static RepositorioColaborador repositorioColaborador;

    private List<Colaborador> listaColaboradores;
    
    private RepositorioColaborador(){
        listaColaboradores = new ArrayList<>();
    }
    
    public void addColaborador(Colaborador colaborador) throws ColaboradorDuplicadoException {
        Colaborador c = getColaboradorByEmail(colaborador.getEmail());
        if (c == null) {
            this.listaColaboradores.add(colaborador);
        } else {
            throw new ColaboradorDuplicadoException(c.getEmail() + ": Colaborador já registado!");
        }
    }
    
    public void addColaborador(String nome, Email email, Password password, String funcao, String telefone) throws ColaboradorDuplicadoException {
        Colaborador c = getColaboradorByEmail(email);
        if (c == null) {
            Colaborador colaborador = new Colaborador(nome, email, password, funcao, telefone);
            this.listaColaboradores.add(colaborador);
        } else {
            throw new ColaboradorDuplicadoException(c.getEmail() + ": Colaborador já registado!");
        }
    }
    
    public void addColaborador(String nome, String emailCol, Password password, String funcao, String telefone) throws ColaboradorDuplicadoException {
        Email email = new Email(emailCol);
        Colaborador c = getColaboradorByEmail(email);
        if (c == null) {
            Colaborador colaborador = new Colaborador(nome, email, password, funcao, telefone);
            this.listaColaboradores.add(colaborador);
        } else {
            throw new ColaboradorDuplicadoException(c.getEmail() + ": Colaborador já registado!");
        }
    }
    
    public void addColaborador(String nome, String emailCol, String passCol, String funcao, String telefone) throws ColaboradorDuplicadoException {
        Email email = new Email(emailCol);
        Colaborador c = getColaboradorByEmail(email);
        if (c == null) {
            Colaborador colaborador = new Colaborador(nome, emailCol, passCol, funcao, telefone);
            this.listaColaboradores.add(colaborador);
        } else {
            throw new ColaboradorDuplicadoException(c.getEmail() + ": Colaborador já registado!");
        }
    }
    
    private Colaborador getColaboradorByEmail(Email email) {
        Colaborador colaborador = null;
        for (int i = 0; i < this.listaColaboradores.size(); i++) {
            colaborador = this.listaColaboradores.get(i);
            if (colaborador.getEmail().equals(email)) {
                Colaborador copia = new Colaborador(colaborador);
                return copia;
            }
        }
        return null;
    }
        
    public static RepositorioColaborador getInstance(){
        if (repositorioColaborador == null){
            repositorioColaborador = new RepositorioColaborador();
        }
        return repositorioColaborador;
    }    
}
