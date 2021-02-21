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

import com.grupo2.t4j.exception.HabilitacaoAcademicaDuplicadaException;
import com.grupo2.t4j.model.*;
import com.grupo2.t4j.persistence.RepositorioHabilitacaoAcademica;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RepositorioHabilitacaoAcademicaInMemory  implements Serializable, RepositorioHabilitacaoAcademica{
    
    /**
     * Define uma instância do Repositório em que estão registados todas
     * as Habilitações Acadêmicas de freelancers registadas na plataforma
     */
    private static RepositorioHabilitacaoAcademicaInMemory repositorioHabilitacaoAcademicaInMemory;
    
    /**
     * Define o atributo da classe RepositorioHabilitacaoAcademica como uma lista de
     * tipos da classe HabilitacaoAcademica
     */
    private List<HabilitacaoAcademica> listaHabilitacoesAcademicas;
    
    /**
     * Inicializa o Repositório de Habilitações Acadêmicas de uma plataforma
     *
     */
    public RepositorioHabilitacaoAcademicaInMemory(){
        listaHabilitacoesAcademicas = new ArrayList<>();
    }
    
    /**
     * Garante que existe apenas um repositorio para as Habilitações Acadêmicas
     * @return repositório das Habilitações Acadêmicas na memória
     */
    public static RepositorioHabilitacaoAcademicaInMemory getInstance(){
        if (repositorioHabilitacaoAcademicaInMemory == null){
            repositorioHabilitacaoAcademicaInMemory = new RepositorioHabilitacaoAcademicaInMemory();
        }
        return repositorioHabilitacaoAcademicaInMemory;
    }
    
    @Override
    public void save(String idHabilitacao, String grau, String designacaoCurso,
           String nomeInstituicao, double mediaCurso) throws HabilitacaoAcademicaDuplicadaException {
        HabilitacaoAcademica ha = findById(idHabilitacao);
        if (ha == null) {
            HabilitacaoAcademica habilitacao = new HabilitacaoAcademica(idHabilitacao,
                    grau, designacaoCurso, nomeInstituicao, mediaCurso);
            this.listaHabilitacoesAcademicas.add(habilitacao);
        } else {
            throw new HabilitacaoAcademicaDuplicadaException(idHabilitacao + ": "
                    + "Habilitação Acadêmica já registada!");
        }
    }

    @Override
    public boolean save(HabilitacaoAcademica habilitacaoAcademica) throws HabilitacaoAcademicaDuplicadaException {
        HabilitacaoAcademica ha = findById(habilitacaoAcademica.getIdHabilitacao());
        if (ha == null) {
            HabilitacaoAcademica habilitacao = new HabilitacaoAcademica(habilitacaoAcademica);
            this.listaHabilitacoesAcademicas.add(habilitacao);
        } else {
            throw new HabilitacaoAcademicaDuplicadaException(habilitacaoAcademica.getIdHabilitacao() + ": "
                    + "Habilitação Acadêmica já registada!");
        }
        return false;
    }

    @Override
    public HabilitacaoAcademica findById(String idHabilitacao) {
        for (int i = 0; i < this.listaHabilitacoesAcademicas.size(); i++) {
            HabilitacaoAcademica habilitacao = this.listaHabilitacoesAcademicas.get(i);
            if (habilitacao.getIdHabilitacao().equals(idHabilitacao)) {
                return habilitacao;
            }
        }
        return null;
    }

    @Override
    public ArrayList<HabilitacaoAcademica> getAll() {
        return new ArrayList<HabilitacaoAcademica>(listaHabilitacoesAcademicas);
    }
        
    public int adicionarListaHabilitacao(RepositorioHabilitacaoAcademicaInMemory outraListaHabilitacao) {
        int totalHabilitacoesAdicionadas = 0;

        for (HabilitacaoAcademica habilitacao : outraListaHabilitacao.listaHabilitacoesAcademicas) {
            boolean habilitacaoAdicionada = save(habilitacao);
            if (habilitacaoAdicionada) {
                totalHabilitacoesAdicionadas++;
            }
        }
        return totalHabilitacoesAdicionadas;
    }
}
