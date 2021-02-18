/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.grupo2.t4j.persistence.inmemory;
/**
 *
 * @author CAD
 */

import com.grupo2.t4j.exception.UtilizadorDuplicadoException;
import com.grupo2.t4j.model.Email;
import com.grupo2.t4j.model.Password;
import com.grupo2.t4j.model.Rolename;
import com.grupo2.t4j.model.Utilizador;
import com.grupo2.t4j.persistence.RepositorioUtilizador;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RepositorioUtilizadorInMemory implements Serializable, RepositorioUtilizador {

     /**
     * Define uma instância estática do Repositório em que estão registados todos
     * os Utilizadores da plataforma
     */
    public static RepositorioUtilizadorInMemory repositorioUtilizadorInMemory;
    
    /**
     * Define o atributo da classe RepositorioUtilizador como uma lista de
     * tipos da classe Utilizador
     */
    private List<Utilizador> listaUtilizadores;
    
    /**
     * Inicializa o Repositório de Utilizadores
     */
    RepositorioUtilizadorInMemory(){
        listaUtilizadores = new ArrayList<>();
    }

    /**
     * Devolve uma instância estática do Repositório de Utilizadores
     * @return RepositorioUtilizador
     */
    public static RepositorioUtilizadorInMemory getInstance() {
        if(repositorioUtilizadorInMemory == null) {
            repositorioUtilizadorInMemory = new RepositorioUtilizadorInMemory();
        }
        return repositorioUtilizadorInMemory;
    }

    @Override
    public void save(Email email, String nome, Password password, Rolename rolename) throws UtilizadorDuplicadoException {
        Utilizador u = findByEmail(email.getEmailText());
        if (u == null) {
            Utilizador utilizador = new Utilizador(email, nome, password, rolename);
            this.listaUtilizadores.add(utilizador);

        } else {
            throw new UtilizadorDuplicadoException(u.getEmail().getEmailText()
                    + ": Utilizador já registado!");
        }
    }

    @Override
    public void save(Utilizador utilizador) {
        Utilizador u = findByEmail(utilizador.getEmail().getEmailText());
        if (u == null) {
            Utilizador user = new Utilizador(utilizador);
            this.listaUtilizadores.add(user);

        } else {
            throw new UtilizadorDuplicadoException(u.getEmail().getEmailText()
                    + ": Utilizador já registado!");
        }
    }

    @Override
    public Utilizador findByEmail(String emailUt) {
        for (int i = 0; i < this.listaUtilizadores.size(); i++) {
            Utilizador utilizador = this.listaUtilizadores.get(i);
            if (utilizador.getEmail().getEmailText().equals(emailUt)) {
                return utilizador;
            }
        }
        return null;
    }

    @Override
    public ArrayList<Utilizador> getAll() {
        return new ArrayList<Utilizador>(listaUtilizadores);
    }
}

