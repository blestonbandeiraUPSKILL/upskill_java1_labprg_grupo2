/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.model;
/**
 *
 * @author CAD
 */
import java.io.Serializable;
import java.util.Calendar;
import com.grupo2.t4j.exception.*;

/**
 *
 * @author Geral
 */
public class Anuncio implements Serializable {
    
    /**
     * O id do Anúncio
     */
    private String idAnuncio;
    
    /**
     * Data de início da publicitação do anúncio
     */
    private Data dtInicioPublicitacao;
    
    /**
     * Data do fim da publicitação do anúncio
    */
    private  Data dtFimPublicitacao;
    
    /**
     * Data de início da candidatura do anúncio
     */
    private Data dtInicioCandidatura;
    
    /**
     * Data do fim da candidatura do anúncio
     */
    private  Data dtFimCandidatura;
    
    /**
     * Data de início do processo de seriação do anúncio
     */
    private Data dtInicioSeriacao;
    
    /**
     * Data do fim do processo de seriação do anúncio
     */
    private  Data dtFimSeriacao;
    
    /**
     * A data atual no formato data
     */
    Calendar cal = Calendar.getInstance();
    Data hoje = new Data(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(Calendar.DAY_OF_MONTH));

    /**
     *
     * @param idAnuncio
     * @param dtInicioPublicitacao
     * @param dtFimPublicitacao
     * @param dtInicioCandidatura
     * @param dtFimCandidatura
     * @param dtInicioSeriacao
     * @param dtFimSeriacao
     */
    public Anuncio(String idAnuncio,Data dtInicioPublicitacao, Data dtFimPublicitacao, Data 
            dtInicioCandidatura, Data dtFimCandidatura, Data dtInicioSeriacao,
            Data dtFimSeriacao) {
        setIdAnuncio(idAnuncio);
        setDtInicioPub(dtInicioPublicitacao);
        setDtFimPub(dtFimPublicitacao);
        setDtInicioCand(dtInicioCandidatura);
        setDtFimCand(dtFimCandidatura);
        setDtInicioSeriacao(dtInicioSeriacao);
        setDtFimSeriacao(dtFimSeriacao);        
    }
           
    /**
     * Construtor da classe Anuncio
     * @param anuncio é do tipo da classe Anúncio
     */
    public Anuncio (Anuncio anuncio){
        setIdAnuncio(anuncio.idAnuncio);
        setDtInicioPub(anuncio.dtInicioPublicitacao);
        setDtFimPub(anuncio.dtFimPublicitacao);
        setDtInicioCand(anuncio.dtInicioCandidatura);
        setDtFimCand(anuncio.dtFimCandidatura);
        setDtInicioSeriacao(anuncio.dtInicioSeriacao);
        setDtFimSeriacao(anuncio.dtFimSeriacao);  
    }
  
    /**
     *
     * @param idAnuncio
     */
    public void setIdAnuncio(String idAnuncio){
        if (idAnuncio == null || idAnuncio.trim().isEmpty()) {
            throw new IllegalArgumentException("Id do anúncio é inválido!");
        }
        this.idAnuncio = idAnuncio;
    }

    /**
     *
     * @param dtInicioPublicitacao
     */
    public void setDtInicioPub(Data dtInicioPublicitacao) {
        if(dtInicioPublicitacao.compareTo(hoje) >= 0){
           this.dtInicioPublicitacao = dtInicioPublicitacao;
        }
        else{
           throw new DataInvalidaException("A data de início de publicitação deve "
                   + "ser maior ou igual à data atual.");
        }        
    }
    
    /**
     *
     * @param dtFimPublicitacao
     */
    public void setDtFimPub(Data dtFimPublicitacao) {
        if(dtFimPublicitacao.isMaior(dtInicioPublicitacao)){
           this.dtFimPublicitacao = dtFimPublicitacao;
        }
        else{
           throw new DataInvalidaException("A data do fim da publicitação deve ser "
                   + "maior que a data de início da publicitação.");
        }       
    }
    
    /**
     *
     * @param dtInicioCandidatura
     */
    public void setDtInicioCand(Data dtInicioCandidatura) {
        if((dtInicioCandidatura.compareTo(dtInicioPublicitacao) >=0) &&
                (dtInicioCandidatura.compareTo(dtFimPublicitacao) <=0)){
           this.dtInicioCandidatura = dtInicioCandidatura;
        }
        else{
           throw new DataInvalidaException("A data do início da candidatura deve"
                   + "estar contida dentro do período de publicitação.");
        }
    }
    
    /**
     *
     * @param dtFimCandidatura
     */
    public void setDtFimCand(Data dtFimCandidatura) {
        if((dtFimCandidatura.compareTo(dtInicioCandidatura) >=0) &&
                (dtFimCandidatura.compareTo(dtFimPublicitacao) <=0)){
           this.dtFimCandidatura = dtFimCandidatura;
        }
        else{
           throw new DataInvalidaException("A data do fim da candidatura deve"
                   + " ser maior do que a data de início do processo de candidatura, e"
                   + " deve estar contida dentro do período de publicitação.");
        }      
    }
    
    /**
     *
     * @param dtInicioSeriacao
     */
    public void setDtInicioSeriacao(Data dtInicioSeriacao) {
        if((dtInicioSeriacao.compareTo(dtInicioPublicitacao) >=0) &&
                (dtInicioSeriacao.compareTo(dtFimPublicitacao) <=0) &&
               (dtInicioSeriacao.isMaior(dtFimCandidatura))){
            this.dtInicioSeriacao = dtInicioSeriacao;
        }
        else{
           throw new DataInvalidaException("A data do início da seriação deve"
                   + "estar contida dentro do período de publicitação e começar"
                   + "após o término do processo de candidaturas.");
        }      
    }
    
    /**
     *
     * @param dtFimSeriacao
     */
    public void setDtFimSeriacao(Data dtFimSeriacao) {
        if((dtFimSeriacao.compareTo(dtInicioSeriacao) >=0) &&
                (dtFimSeriacao.compareTo(dtFimPublicitacao) <=0)){
            this.dtFimSeriacao = dtFimSeriacao;
        }
        else{
           throw new DataInvalidaException("A data do fim da seriação deve"
                   + "estar contida dentro do período de publicitação ser maior"
                   + " ou igual à data de início da seriação.");
        }
        
    }
    
    /**
     *
     * @return
     */
    public String getIdAnuncio(){
        return idAnuncio;
    }

    /**
     *
     * @return
     */
    public Data getDtInicioPub() {
       return dtInicioPublicitacao;
    }
    
    /**
     *
     * @return
     */
    public Data getDtFimPub() {
       return dtFimPublicitacao;
    }
    
    /**
     *
     * @return
     */
    public Data getDtInicioCand() {
       return dtInicioCandidatura;
    }
    
    /**
     *
     * @return
     */
    public Data getDtFimCand() {
       return dtFimCandidatura;
    }
    
    /**
     *
     * @return
     */
    public Data getDtInicioSeriacao() {
       return dtInicioSeriacao;
    }
    
    /**
     *
     * @return
     */
    public Data getDtFimSeriacao() {
       return dtFimSeriacao; 
    }
    
    /**
     * Representação textual da classe Anuncio
     * @return 
     */   
    @Override
    public String toString(){
        return String.format("Anúncio Id: %s  %nInício publicitação: %s"
                + "%nFim publicitação: %s %nInício candidatura: %s"
                + "%nFim candidatura: %s %nInício seriação: %s"
                + "%nFim seriação: %s", idAnuncio, dtInicioPublicitacao.toAnoMesDiaString(),
                dtFimPublicitacao.toAnoMesDiaString(), dtInicioCandidatura.toAnoMesDiaString(),
                dtFimCandidatura.toAnoMesDiaString(), dtInicioSeriacao.toAnoMesDiaString(),
                dtFimSeriacao.toAnoMesDiaString());
    }
}
