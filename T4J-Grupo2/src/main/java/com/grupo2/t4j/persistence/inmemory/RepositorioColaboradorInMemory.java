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

import com.grupo2.t4j.exception.ColaboradorDuplicadoException;
import com.grupo2.t4j.model.Colaborador;
import com.grupo2.t4j.model.Email;
import com.grupo2.t4j.model.Rolename;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RepositorioColaboradorInMemory implements Serializable{
    
    /**
     * Define uma instância estática do Repositório em que estão registados todos
     * os Colaboradores da plataforma
     */
    private static RepositorioColaboradorInMemory repositorioColaboradorInMemory;
    
    /**
     * Define o atributo da classe RepositorioColaborador como uma lista de
     * tipos da classe Colaborador
     */
    private List<Colaborador> listaColaboradores;
    
    /**
     * Inicializa o Repositório de Colaboradores
     */
    private RepositorioColaboradorInMemory(){
        listaColaboradores = new ArrayList<>();
    }
    
    /**
     * Adiciona um Colaborador à lista de Colaboradores
     * @param colaborador do tipo da classe Colaborador
     * @throws ColaboradorDuplicadoException
     */
    public boolean addColaborador(Colaborador colaborador) throws ColaboradorDuplicadoException {
        Colaborador c = getColaboradorByEmail(colaborador.getEmail().getEmailText());
        if (c == null) {
            this.listaColaboradores.add(colaborador);
            return true;
        } else {
            throw new ColaboradorDuplicadoException(c.getEmail().getEmailText()
                    + ": Colaborador já registado!");
        }
    }
    
    /**
     * Adiciona um Colaborador à lista de Colaboradores
     * @param nome o nome do Colaborador
     * @param email o email do Colaborador em formato da classe Email
     * @param funcao a função do Colaborador da organização
     * @param telefone o telefone do Colaborador da organização
     * @throws ColaboradorDuplicadoException
     */
    public boolean addColaborador(Email email, String nome,  String funcao,
            String telefone, Rolename rolename) throws ColaboradorDuplicadoException {
        Colaborador c = getColaboradorByEmail(email.getEmailText());
        if (c == null) {
            Colaborador colaborador = new Colaborador(email, nome, funcao,
                    telefone, rolename);
            this.listaColaboradores.add(colaborador);
            return true;
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
    public boolean addColaborador(String emailCol, String nome, String passCol, String funcao,
            String telefone, Rolename rolename) throws ColaboradorDuplicadoException {
        Colaborador c = getColaboradorByEmail(emailCol);
        if (c == null) {
            Colaborador colaborador = new Colaborador(emailCol, nome, passCol, funcao,
                    telefone, rolename);
            this.listaColaboradores.add(colaborador);
            return true;
        } else {
            throw new ColaboradorDuplicadoException(c.getEmail().getEmailText()
                    + ": Colaborador já registado!");
        }
    }
    
    /**
     * Adiciona um Colaborador à lista de Colaboradores
     * @param nome o nome do Colaborador
     * @param emailCol o email do Colaborador em formato String
     * @param funcao a função do Colaborador da organização
     * @param telefone o telefone do Colaborador da organização
     * @throws ColaboradorDuplicadoException
     */
    public boolean addColaborador(String emailCol, String nome, String funcao,
            String telefone, Rolename rolename) throws ColaboradorDuplicadoException {
        Colaborador c = getColaboradorByEmail(emailCol);
        if (c == null) {
            Colaborador colaborador = new Colaborador(new Email(emailCol), nome, funcao,
                    telefone, rolename);
            this.listaColaboradores.add(colaborador);
            return true;
        } else {
            throw new ColaboradorDuplicadoException(c.getEmail().getEmailText()
                    + ": Colaborador já registado!");
        }
    }
    
   /* *//**
     * Adiciona um Colaborador à lista de Colaboradores com
     * Rolename de COLABORADOR por omissão
     *
     * @param nome o nome do Colaborador
     * @param emailCol o email do Colaborador em formato String
     * @param funcao a função do Colaborador da organização
     * @param telefone o telefone do Colaborador da organização
     * @throws ColaboradorDuplicadoException
     *//*
    public boolean addColaborador(String emailCol, String nome, String funcao,
            String telefone) throws ColaboradorDuplicadoException {
        Colaborador c = getColaboradorByEmail(emailCol);
        if (c == null) {
            Colaborador colaborador = new Colaborador(emailCol, nome, funcao,
                    telefone);
            this.listaColaboradores.add(colaborador);
            return true;
        } else {
            throw new ColaboradorDuplicadoException(c.getEmail().getEmailText()
                    + ": Colaborador já registado!");
        }
    }*/
    
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

    /*public boolean verificacaoAddColaborador(String nome, String emailCol, String funcao, String telefone){
        int tam = listaColaboradores.size();
        Colaborador ultimoColRegistado = listaColaboradores.get(tam-1);
        if(emailCol.equals(ultimoColRegistado.getEmail().getEmailText())){
            return true;
        }
        else{
            return false;
        }
    }*/
    
    /**
     * Devolve uma instância estática do Repositório de Colaboradores
     * @return RepositorioColaborador
     */
    public static RepositorioColaboradorInMemory getInstance(){
        if (repositorioColaboradorInMemory == null){
            repositorioColaboradorInMemory = new RepositorioColaboradorInMemory();
        }
        return repositorioColaboradorInMemory;
    }  
    
    /**
     * Atualiza a lista de Colaboradores
     * @param listaColaboradores
     */
    public void setListaColaboradores(List<Colaborador> listaColaboradores){
        this.listaColaboradores = listaColaboradores;
    }
    
    /**
     * Devolve a lista de Colaboradores
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
    
    public int adicionarListaColaborador(RepositorioColaboradorInMemory outraListaColaborador) {
        int totalColaboradoresAdicionados = 0;
        
        for (Colaborador colaborador : outraListaColaborador.listaColaboradores) {
            boolean colaboradorAdicionado = addColaborador(colaborador);
            if (colaboradorAdicionado) {
                totalColaboradoresAdicionados++;
            }
        }
        return totalColaboradoresAdicionados;
    }

    public Colaborador novoColaborador(Email email, String nome, String funcao, String telefone, Rolename rolename) {
        return new Colaborador(email, nome, funcao, telefone, Rolename.COLABORADOR);
    }
}
