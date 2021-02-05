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
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RepositorioColaborador implements Serializable{
    
    /**
     * Define uma instância estática do Repositório em que estão registados todos
     * os Colaboradores da plataforma
     */
    private static RepositorioColaborador repositorioColaborador;
    
    /**
     * Define o atributo da classe RepositorioColaborador como uma lista de
     * tipos da classe Colaborador
     */
    private List<Colaborador> listaColaboradores;
    
    /**
     * Inicializa o Repositório de Colaboradores
     */
    private RepositorioColaborador(){
        listaColaboradores = new ArrayList<>();
    }
    
    /**
     * Adiciona um Colaborador à lista de Colaboradores
     * @param colaborador do tipo da classe Colaborador
     * @throws ColaboradorDuplicadoException
     */
    public void addColaborador(Colaborador colaborador) throws ColaboradorDuplicadoException {
        Colaborador c = getColaboradorByEmail(colaborador.getEmail().getEmailText());
        if (c == null) {
            this.listaColaboradores.add(colaborador);
        } else {
            throw new ColaboradorDuplicadoException(c.getEmail().getEmailText()
                    + ": Colaborador já registado!");
        }
    }
    
    /**
     * Adiciona um Colaborador à lista de Colaboradores
     * @param nome o nome do Colaborador
     * @param email o email do Colaborador em formato da classe Email
     * @param password a password do Colaborador em formato da classe Password
     * param funcao a função do Colaborador da organização
     * @param telefone o telefone do Colaborador da organização
     * @throws ColaboradorDuplicadoException
     */
    public void addColaborador(String nome, Email email, Password password, String funcao, String telefone) throws ColaboradorDuplicadoException {
        Colaborador c = getColaboradorByEmail(email.getEmailText());
        if (c == null) {
            Colaborador colaborador = new Colaborador(nome, email, password, funcao, telefone);
            this.listaColaboradores.add(colaborador);
        } else {
            throw new ColaboradorDuplicadoException(c.getEmail().getEmailText()
                    + ": Colaborador já registado!");
        }
    }
    
    /**
     * Adiciona um Colaborador à lista de Colaboradores
     * @param nome o nome do Colaborador
     * @param emailCol o email do Colaborador em formato String
     * @param password a password do Colaborador em formato da classe Password
     * @param funcao a função do Colaborador da organização
     * @param telefone o telefone do Colaborador da organização
     * @throws ColaboradorDuplicadoException
     */
    public void addColaborador(String nome, String emailCol, Password password, String funcao, String telefone) throws ColaboradorDuplicadoException {
        Colaborador c = getColaboradorByEmail(emailCol);
        if (c == null) {
            Colaborador colaborador = new Colaborador(nome, emailCol, password, funcao, telefone);
            this.listaColaboradores.add(colaborador);
        } else {
            throw new ColaboradorDuplicadoException(c.getEmail().getEmailText()
                    + ": Colaborador já registado!");
        }
    }
    
    /**
     * Adiciona um Colaborador à lista de Colaboradores
     * @param nome o nome do Colaborador
     * @param emailCol o email do Colaborador em formato String
     * @param passCol a password do Colaborador em formato String
     * @param funcao a função do Colaborador da organização
     * @param telefone o telefone do Colaborador da organização
     * @throws ColaboradorDuplicadoException
     */
    public void addColaborador(String nome, String emailCol, String passCol, String funcao, String telefone) throws ColaboradorDuplicadoException {
        Colaborador c = getColaboradorByEmail(emailCol);
        if (c == null) {
            Colaborador colaborador = new Colaborador(nome, emailCol, passCol, funcao, telefone);
            this.listaColaboradores.add(colaborador);
        } else {
            throw new ColaboradorDuplicadoException(c.getEmail().getEmailText() 
                    + ": Colaborador já registado!");
        }
    }
    
    /**
     * Devolve um Colaborador de acordo com o email registado
     * @param emailCol o email em String do Colaborador 
     * @return Colaborador registado
     */
    public Colaborador getColaboradorByEmail(String emailCol) {
        Colaborador colaborador = null;
        for (int i = 0; i < this.listaColaboradores.size(); i++) {
            colaborador = this.listaColaboradores.get(i);
            if (colaborador.getEmail().getEmailText().equals(emailCol)) {
                return colaborador;
            }
        }
        return null;
    }
        
    /**
     * Devolve uma instância estática do Repositório de Colaboradores
     * @return RepositorioColaborador
     */
    public static RepositorioColaborador getInstance(){
        if (repositorioColaborador == null){
            repositorioColaborador = new RepositorioColaborador();
        }
        return repositorioColaborador;
    }  
    
    /**
     * Atualiza a lista de Colaboradores
     * @param listaColaboradores
     */
    public void setListaColaboradores(List<Colaborador> listaColaboradores){
        this.listaColaboradores = listaColaboradores;
    }
    
    /**
     * Deevolve a lista de Colaboradores
     * @return
     */
    public ArrayList<Colaborador> getListaColaboradores(){
        return new ArrayList<Colaborador>(listaColaboradores);
    }
    
    /**
     * Informa se a lista de Colaboradores está ou não vazia
     * @return
     */
    public boolean isVazia() {
        return listaColaboradores.isEmpty();
    }
}
