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
import com.grupo2.t4j.model.Password;
import com.grupo2.t4j.model.Rolename;
import com.grupo2.t4j.persistence.RepositorioColaborador;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RepositorioColaboradorInMemory implements Serializable, RepositorioColaborador {
    
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
    RepositorioColaboradorInMemory(){
        listaColaboradores = new ArrayList<>();
    }

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
     * Adiciona um Colaborador à lista de Colaboradores
     * @param colaborador do tipo da classe Colaborador
     * @throws ColaboradorDuplicadoException
     */
    public boolean addColaborador(Colaborador colaborador) throws ColaboradorDuplicadoException {
        Colaborador c = findByEmail(colaborador.getEmail().getEmailText());
        if (c == null) {
            this.listaColaboradores.add(colaborador);
            return true;
        } else {
            throw new ColaboradorDuplicadoException(c.getEmail().getEmailText()
                    + ": Colaborador já registado!");
        }
    }

    @Override
    public void save(Email email, String nome, Password password, String funcao, String telefone) throws ColaboradorDuplicadoException {
        Colaborador c = findByEmail(email.getEmailText());
        if (c == null) {
            Colaborador colaborador = new Colaborador(email, nome, password, funcao, telefone);
            this.listaColaboradores.add(colaborador);

        } else {
            throw new ColaboradorDuplicadoException(c.getEmail().getEmailText()
                    + ": Colaborador já registado!");
        }
    }

    @Override
    public boolean save(Colaborador colaborador) {
        Colaborador c = findByEmail(colaborador.getEmail().getEmailText());
        if (c == null) {
            Colaborador colab = new Colaborador(colaborador);
            this.listaColaboradores.add(colab);

        } else {
            throw new ColaboradorDuplicadoException(c.getEmail().getEmailText()
                    + ": Colaborador já registado!");
        }
        return false;
    }

    @Override
    public Colaborador findByEmail(String emailCol) {
        for (int i = 0; i < this.listaColaboradores.size(); i++) {
            Colaborador colaborador = this.listaColaboradores.get(i);
            if (colaborador.getEmail().getEmailText().equals(emailCol)) {
                return colaborador;
            }
        }
        return null;
    }

    @Override
    public ArrayList<Colaborador> getAll() {
        return new ArrayList<Colaborador>(listaColaboradores);
    }

    @Override
    public Password findPassword(String email) throws SQLException {
        return null;
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
}
