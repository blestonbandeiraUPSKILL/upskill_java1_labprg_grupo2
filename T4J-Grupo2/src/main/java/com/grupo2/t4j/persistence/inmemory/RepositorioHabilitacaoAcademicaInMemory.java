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
import com.grupo2.t4j.domain.HabilitacaoAcademica;
import com.grupo2.t4j.persistence.RepositorioHabilitacaoAcademica;

import java.io.Serializable;
import java.sql.SQLException;
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
    
    private int cont = 0;
    
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
    public boolean save(String grau, String designacaoCurso,
           String nomeInstituicao, double mediaCurso, String emailFreelancer) throws HabilitacaoAcademicaDuplicadaException {
        /*HabilitacaoAcademica ha1 = findById(idHabilitacao);*/
        HabilitacaoAcademica ha2 = findByGrauDesigInst(grau, designacaoCurso,
                nomeInstituicao, emailFreelancer);
        if (ha2 == null) {
            HabilitacaoAcademica habilitacao = new HabilitacaoAcademica(cont,
                    grau, designacaoCurso, nomeInstituicao, mediaCurso, emailFreelancer);
            this.listaHabilitacoesAcademicas.add(habilitacao);
            contador();
            return true;
        } else {
            throw new HabilitacaoAcademicaDuplicadaException("Habilitação Acadêmica já registada!");
        }
    }

    @Override
    public boolean save(HabilitacaoAcademica habilitacaoAcademica, String emailFreelancer) throws HabilitacaoAcademicaDuplicadaException, SQLException {
        return false;
    }


    public boolean save(HabilitacaoAcademica habilitacaoAcademica) throws HabilitacaoAcademicaDuplicadaException {
        //HabilitacaoAcademica ha1 = findById(habilitacaoAcademica.getIdHabilitacao());
        HabilitacaoAcademica ha2 = findByGrauDesigInst(habilitacaoAcademica.getGrau(),
                habilitacaoAcademica.getDesignacaoCurso(), habilitacaoAcademica.getNomeInstituicao(),
                habilitacaoAcademica.getEmailFreelancer());
        if (ha2 == null) {
            HabilitacaoAcademica habilitacao = new HabilitacaoAcademica(habilitacaoAcademica);
            this.listaHabilitacoesAcademicas.add(habilitacao);
            contador();
            return true;
        } else {
            throw new HabilitacaoAcademicaDuplicadaException("Habilitação Acadêmica já registada!");
        }        
    }

    @Override
    public HabilitacaoAcademica findById(int idHabilitacao) {
        for (int i = 0; i < this.listaHabilitacoesAcademicas.size(); i++) {
            HabilitacaoAcademica habilitacao = this.listaHabilitacoesAcademicas.get(i);
            if (habilitacao.getIdHabilitacao() == idHabilitacao) {
                return habilitacao;
            }
        }
        return null;
    }

    public HabilitacaoAcademica findByGrauDesigInst(String grau, String designacaoCurso, String nomeInstituicao) throws SQLException {
        return null;
    }

    @Override
    public ArrayList<HabilitacaoAcademica> getAll(String emailFreelancer) {
        ArrayList<HabilitacaoAcademica> listaHabilitacoesUmFreelancer = new ArrayList<>();
        for(int i = 0; i < listaHabilitacoesAcademicas.size();i++){
            HabilitacaoAcademica habilitacao = this.listaHabilitacoesAcademicas.get(i);
            if (habilitacao.getEmailFreelancer().equals(emailFreelancer)){
                listaHabilitacoesUmFreelancer.add(habilitacao);
            }
        }
        return listaHabilitacoesUmFreelancer;
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
    

    public HabilitacaoAcademica findByGrauDesigInst(String grau, String designacaoCurso,
                                                String nomeInstituicao, String emailFreelancer){
       /* for (int i = 0; i < this.listaHabilitacoesAcademicas.size(); i++) {
            HabilitacaoAcademica habilitacao = this.listaHabilitacoesAcademicas.get(i);
            if (habilitacao.getEmailFreelancer().equals(emailFreelancer) && habilitacao.getGrau().equals(grau)){
                if(habilitacao.getDesignacaoCurso().equals(designacaoCurso) && 
                   habilitacao.getNomeInstituicao().equals(nomeInstituicao)) {
                    return habilitacao;
                }
            }
        }*/
        return null;
    }
    
    public int contador(){
        return cont++;        
    }
}
