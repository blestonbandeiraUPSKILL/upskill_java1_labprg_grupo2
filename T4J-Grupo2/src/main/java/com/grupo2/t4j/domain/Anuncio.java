package com.grupo2.t4j.domain;

import com.grupo2.t4j.exception.DataInvalidaException;

import java.io.Serializable;
import java.util.Calendar;

public class Anuncio implements Serializable {
    
    /**
     * O id do Anúncio
     */
    private int idAnuncio;
    
    /**
     * A referencia da tarefa a que se refere o anúncio
     */
    private String referenciaTarefa;
    
    /**
     * O nif da organização que requer a tarefa
     */
    private String nifOrganizacao;
    
    /**
     * Data de início da publicitação do anúncio
     */
    private String dtInicioPublicitacao;
    
    /**
     * Data do fim da publicitação do anúncio
    */
    private  String dtFimPublicitacao;
    
    /**
     * Data de início da candidatura do anúncio
     */
    private String dtInicioCandidatura;
    
    /**
     * Data do fim da candidatura do anúncio
     */
    private  String dtFimCandidatura;
    
    /**
     * Data de início do processo de seriação do anúncio
     */
    private String dtInicioSeriacao;
    
    /**
     * Data do fim do processo de seriação do anúncio
     */
    private  String dtFimSeriacao;
    
    /**
     * O id do tipo de regimento aplicavel ao anuncio
     */
    private int idTipoRegimento;
    
    /**
     * A data atual no formato da classe Data
     */
    private Calendar cal = Calendar.getInstance();
    private Data hoje = new Data(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),
            cal.get(Calendar.DAY_OF_MONTH));


    /**
     * O construtor vazio da Classe Anúncio
     */
    public Anuncio(){
    }
    
    /**
     * Construtor completo da classe Anuncio
     * @param idAnuncio - o id do Anúncio 
     * @param referenciaTarefa - referência da tarefa a que se relaciona o anúncio
     * @param nifOrganizacao - o nif da organização que requer a tarefa
     * @param dtInicioPublicitacao - data de início da publicitação do anúncio
     * @param dtFimPublicitacao - data do fim da publicitação do anúncio
     * @param dtInicioCandidatura - data de início da candidatura do anúncio
     * @param dtFimCandidatura - data do fim da candidatura do anúncio
     * @param dtInicioSeriacao - data de início do processo de seriação do anúncio
     * @param dtFimSeriacao - data do fim do processo de seriação do anúncio
     * @param idTipoRegimento - O id do tipo de regimento aplicável ao anúncio
     */
    public Anuncio(int idAnuncio, String referenciaTarefa, String nifOrganizacao, String dtInicioPublicitacao, String dtFimPublicitacao, String
            dtInicioCandidatura, String dtFimCandidatura, String dtInicioSeriacao,
                   String dtFimSeriacao, int idTipoRegimento) {
        this.idAnuncio = idAnuncio;
        setReferenciaTarefa(referenciaTarefa);
        setNifOrganizacao(nifOrganizacao);
        setDtInicioPub(dtInicioPublicitacao);
        setDtFimPub(dtFimPublicitacao);
        setDtInicioCand(dtInicioCandidatura);
        setDtFimCand(dtFimCandidatura);
        setDtInicioSeriacao(dtInicioSeriacao);
        setDtFimSeriacao(dtFimSeriacao); 
        setIdTipoRegimento(idTipoRegimento);
    }
              
    /**
     * Construtor completo da classe Anuncio sem o id do Anúncio que será atribuiído na BD
     * @param referenciaTarefa - referência da tarefa a que se relaciona o anúncio
     * @param nifOrganizacao - o nif da organização que requer a tarefa
     * @param dtInicioPublicitacao - data de início da publicitação do anúncio
     * @param dtFimPublicitacao - data do fim da publicitação do anúncio
     * @param dtInicioCandidatura - data de início da candidatura do anúncio
     * @param dtFimCandidatura - data do fim da candidatura do anúncio
     * @param dtInicioSeriacao - data de início do processo de seriação do anúncio
     * @param dtFimSeriacao - data do fim do processo de seriação do anúncio
     * @param idTipoRegimento - O id do tipo de regimento aplicável ao anúncio
     */
    public Anuncio(String referenciaTarefa, String nifOrganizacao, String dtInicioPublicitacao, String dtFimPublicitacao, String
            dtInicioCandidatura, String dtFimCandidatura, String dtInicioSeriacao,
                   String dtFimSeriacao, int idTipoRegimento) {
        setReferenciaTarefa(referenciaTarefa);
        setNifOrganizacao(nifOrganizacao);
        setDtInicioPub(dtInicioPublicitacao);
        setDtFimPub(dtFimPublicitacao);
        setDtInicioCand(dtInicioCandidatura);
        setDtFimCand(dtFimCandidatura);
        setDtInicioSeriacao(dtInicioSeriacao);
        setDtFimSeriacao(dtFimSeriacao); 
        setIdTipoRegimento(idTipoRegimento);
    }
    
    /**
     * Construtor da classe Anuncio
     * @param anuncio é do tipo da classe Anúncio
     */
    public Anuncio (Anuncio anuncio){
        setIdAnuncio(anuncio.idAnuncio);
        setReferenciaTarefa(anuncio.referenciaTarefa);
        setNifOrganizacao(anuncio.nifOrganizacao);
        setDtInicioPub(anuncio.dtInicioPublicitacao);
        setDtFimPub(anuncio.dtFimPublicitacao);
        setDtInicioCand(anuncio.dtInicioCandidatura);
        setDtFimCand(anuncio.dtFimCandidatura);
        setDtInicioSeriacao(anuncio.dtInicioSeriacao);
        setDtFimSeriacao(anuncio.dtFimSeriacao);
        setIdTipoRegimento(anuncio.idTipoRegimento);
    }

    /**
     * Define o id do Anuncio 
     * @param idAnuncio - o id do Anúncio
     */
    public void setIdAnuncio(int idAnuncio){
        if (idAnuncio < 0 ) {
            throw new IllegalArgumentException("Id do anúncio é inválido!");
        }
        this.idAnuncio = idAnuncio;
    }
    
    /**
     * Define a referência da tarefa a qual se relaciona o anúncio
     * @param referenciaTarefa 
     */
    public void setReferenciaTarefa(String referenciaTarefa) {
        this.referenciaTarefa = referenciaTarefa;
    }
    
    /**
     * Define o nif da organização que requer a tarefa
     * @param nifOrganizacao 
     */
    public void setNifOrganizacao(String nifOrganizacao) {
        this.nifOrganizacao = nifOrganizacao;
    }
    
    /**
     * Define a data de início da publicitação do anúncio
     * @param dtInicioPublicitacao
     */
    public void setDtInicioPub(String dtInicioPublicitacao) {
        if(dtInicioPublicitacao == null || dtInicioPublicitacao.trim().isEmpty()){
            throw new DataInvalidaException("A data de início de publicitação não é válida.");
        }
        this.dtInicioPublicitacao = dtInicioPublicitacao;
    }
    
    /**
     * Define a data do fim da publicitação do anúncio
     * @param dtFimPublicitacao
     */
    public void setDtFimPub(String dtFimPublicitacao) {
        if(dtFimPublicitacao == null || dtFimPublicitacao.trim().isEmpty()) {
            throw new DataInvalidaException("A data de fim de publicitação não é válida.");
        }
        this.dtFimPublicitacao = dtFimPublicitacao;
    }
    
    /**
     * Define a data de início da candidatura do anúncio
     * @param dtInicioCandidatura
     */
    public void setDtInicioCand(String dtInicioCandidatura) {
        if(dtInicioCandidatura == null || dtInicioCandidatura.trim().isEmpty()){
            throw new DataInvalidaException("A data de início de candidatura não é válida.");
        }
        this.dtInicioCandidatura = dtInicioCandidatura;
    }
    
    /**
     * Define a data do fim da candidatura do anúncio
     * @param dtFimCandidatura
     */
    public void setDtFimCand(String dtFimCandidatura) {
        if(dtFimCandidatura == null || dtFimCandidatura.trim().isEmpty()){
            throw new DataInvalidaException("A data de fim de candidatura não é válida.");
        }
        this.dtFimCandidatura = dtFimCandidatura;
    }
    
    /**
     * Define a data de início do processo de seriação do anúncio
     * @param dtInicioSeriacao
     */
    public void setDtInicioSeriacao(String dtInicioSeriacao) {
        if(dtInicioSeriacao == null || dtInicioSeriacao.trim().isEmpty()){
            throw new DataInvalidaException("A data de início de seriação não é válida.");
        }
        this.dtInicioSeriacao = dtInicioSeriacao;
    }
    
    /**
     * Define a data do fim do processo de seriação do anúncio
     * @param dtFimSeriacao
     */
    public void setDtFimSeriacao(String dtFimSeriacao) {
        if(dtFimSeriacao == null || dtFimSeriacao.trim().isEmpty()){
            throw new DataInvalidaException("A data de fim de seriação não é válida.");
        }
        this.dtFimSeriacao = dtFimSeriacao;
        
    }
    
   
    public void setIdTipoRegimento(int idTipoRegimento) {
        this.idTipoRegimento = idTipoRegimento;
    }
    
    /**
     * Devolve o id do Anuncio 
     * @return idAnuncio
     */
    public int getIdAnuncio(){
        return idAnuncio;
    }

     /**
     * Devolve a referência da tarefa a qual se relaciona o anúncio
     * @return referenciaTarefa
     */
    public String getReferenciaTarefa() {
        return referenciaTarefa;
    }

    /**
     * Devolve o nif da organização que requer a tarefa
     * @param nifOrganizacao 
     */
    public String getNifOrganizacao() {
        return nifOrganizacao;
    }
    
    /**
     * Devolve a data de início da publicitação do anúncio
     * @return
     */
    public String getDtInicioPub() {
       return dtInicioPublicitacao;
    }
    
    /**
     * Devolve a data do fim da publicitação do anúncio
     * @return dtFimPublicitacao
     */
    public String getDtFimPub() {
       return dtFimPublicitacao;
    }
    
    /**
     * Devolve a data de início da candidatura do anúncio
     * @return dtInicioCandidatura
     */
    public String getDtInicioCand() {
       return dtInicioCandidatura;
    }
    
    /**
     * Devolve a data do fim da candidatura do anúncio
     * @return
     */
    public String getDtFimCand() {
       return dtFimCandidatura;
    }
    
    /**
     * Devolve a data de início do processo de seriação do anúncio
     * @return dtInicioSeriacao
     */
    public String getDtInicioSeriacao() {
       return dtInicioSeriacao;
    }
    
    /**
     * Devolve a data do fim do processo de seriação do anúncio
     * @return dtFimSeriacao
     */
    public String getDtFimSeriacao() {
       return dtFimSeriacao; 
    }
 
    /**
     * Devolve o id do tipo de regimento aplicável ao anúncio
     * @param idTipoRegimento 
     */
    public int getIdTipoRegimento() {
        return idTipoRegimento;
    }
    
    /**
     * Representação textual da classe Anuncio em formato de exibição
     * @return o id do anúncio e as datas de: início e fim da publicitação, início
     * e fim do período de candidatura e do início e do fim do período de seriação
     */   
    @Override
    public String toString(){
        return String.format("ID: %-12s |Início publicitação: %-12s"
                + " |Fim publicitação: %-12s |Início candidatura: %-12s"
                + " |Fim candidatura: %-12s |Início seriação: %-12s"
                + " |Fim seriação: %-12s"
                + " |Tipo de Regimento: %s", idAnuncio, dtInicioPublicitacao,
                dtFimPublicitacao, dtInicioCandidatura,
                dtFimCandidatura, dtInicioSeriacao,
                dtFimSeriacao, idTipoRegimento);
    }
    
    /**
     * Representação textual da classe Anuncio
     * @return o id do anúncio e as datas de: início e fim da publicitação, início
     * e fim do período de candidatura e do início e do fim do período de seriação
     */   
    public String toStringCompleto(){
        return String.format("ID Anúncio: %s%n"
                        + "Referencia Tarefa : %s%n"
                        + "Nif Organização: %s%n"
                        + "Início publicitação: %s%n"
                        + "Fim publicitação: %s %n"
                        + "Início candidatura: %s"
                + "%nFim candidatura: %s %nInício seriação: %s"
                + "%nFim seriação: %s"
                + "%nTipo de Regimento: %d",
                idAnuncio, referenciaTarefa,
                nifOrganizacao, dtInicioPublicitacao,
                dtFimPublicitacao, dtInicioCandidatura,
                dtFimCandidatura, dtInicioSeriacao,
                dtFimSeriacao, idTipoRegimento);
    }
}
