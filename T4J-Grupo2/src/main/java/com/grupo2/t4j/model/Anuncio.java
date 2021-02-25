package com.grupo2.t4j.model;

import java.io.Serializable;
import java.util.Calendar;
import com.grupo2.t4j.exception.*;

public class Anuncio implements Serializable {
    
    /**
     * O id do Anúncio
     */
    private String idAnuncio;
    
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
     * O id do tipo de regimento aplicavel ao anuncio
     */
    private String idTipoRegimento;
    
    /**
     * A data atual no formato da classe Data
     */
    private Calendar cal = Calendar.getInstance();
    private Data hoje = new Data(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),
            cal.get(Calendar.DAY_OF_MONTH));

    /**
     * Construtor completo da classe Anuncio
     * @param idAnuncio - o id do Anúncio
     * @param dtInicioPublicitacao - data de início da publicitação do anúncio
     * @param dtFimPublicitacao - data do fim da publicitação do anúncio
     * @param dtInicioCandidatura - data de início da candidatura do anúncio
     * @param dtFimCandidatura - data do fim da candidatura do anúncio
     * @param dtInicioSeriacao - data de início do processo de seriação do anúncio
     * @param dtFimSeriacao - data do fim do processo de seriação do anúncio
     */
    /*public Anuncio(String idAnuncio,Data dtInicioPublicitacao, Data dtFimPublicitacao, Data 
            dtInicioCandidatura, Data dtFimCandidatura, Data dtInicioSeriacao,
            Data dtFimSeriacao) {
        setIdAnuncio(idAnuncio);
        setDtInicioPub(dtInicioPublicitacao);
        setDtFimPub(dtFimPublicitacao);
        setDtInicioCand(dtInicioCandidatura);
        setDtFimCand(dtFimCandidatura);
        setDtInicioSeriacao(dtInicioSeriacao);
        setDtFimSeriacao(dtFimSeriacao);        
    }*/
    
    public Anuncio(){
    }
    
    /**
     * Construtor completo da classe Anuncio
     * @param idTarefa - o id da Tarefa a que se refere o Anúncio
     * @param dtInicioPublicitacao - data de início da publicitação do anúncio
     * @param dtFimPublicitacao - data do fim da publicitação do anúncio
     * @param dtInicioCandidatura - data de início da candidatura do anúncio
     * @param dtFimCandidatura - data do fim da candidatura do anúncio
     * @param dtInicioSeriacao - data de início do processo de seriação do anúncio
     * @param dtFimSeriacao - data do fim do processo de seriação do anúncio
     */
    public Anuncio(String referenciaTarefa, String nifOrganizacao, Data dtInicioPublicitacao, Data dtFimPublicitacao, Data 
            dtInicioCandidatura, Data dtFimCandidatura, Data dtInicioSeriacao,
            Data dtFimSeriacao, String idTipoRegimento) {
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
    
    /*public Anuncio(String idAnuncio,String referenciaTarefa, String nifOrganizacao,Data dtInicioPublicitacao, Data dtFimPublicitacao, Data 
            dtInicioCandidatura, Data dtFimCandidatura, Data dtInicioSeriacao,
            Data dtFimSeriacao) {
        setIdAnuncio(idAnuncio);
        setReferenciaTarefa(referenciaTarefa);
        setNifOrganizacao(nifOrganizacao);
        setDtInicioPub(dtInicioPublicitacao);
        setDtFimPub(dtFimPublicitacao);
        setDtInicioCand(dtInicioCandidatura);
        setDtFimCand(dtFimCandidatura);
        setDtInicioSeriacao(dtInicioSeriacao);
        setDtFimSeriacao(dtFimSeriacao);        
    }*/
    
    /**
     * Construtor completo da classe Anuncio com as datas em formato texto
     * @param idAnuncio - o id do Anúncio
     * @param txtDtInicioPublicitacao - data de início da publicitação do anúncio em formato texto
     * @param txtDtFimPublicitacao - data do fim da publicitação do anúncio em formato texto
     * @param txtDtInicioCandidatura - data de início da candidatura do anúncio em formato texto
     * @param txtDtFimCandidatura - data do fim da candidatura do anúncio em formato texto
     * @param txtDtInicioSeriacao - data de início do processo de seriação do anúncio em formato texto
     * @param txtDtFimSeriacao - data do fim do processo de seriação do anúncio em formato texto
     */
    /*public Anuncio(String idAnuncio,String txtDtInicioPublicitacao, String txtDtFimPublicitacao, 
            String txtDtInicioCandidatura, String txtDtFimCandidatura, String txtDtInicioSeriacao,
            String txtDtFimSeriacao) {
        setIdAnuncio(idAnuncio);
        setDtInicioPub(eFormaData(txtDtInicioPublicitacao));
        setDtFimPub(eFormaData(txtDtFimPublicitacao));
        setDtInicioCand(eFormaData(txtDtInicioCandidatura));
        setDtFimCand(eFormaData(txtDtFimCandidatura));
        setDtInicioSeriacao(eFormaData(txtDtInicioSeriacao));
        setDtFimSeriacao(eFormaData(txtDtFimSeriacao));        
    }*/
           
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
    }
  
    /**
     * Define o id do Anuncio 
     * @param idAnuncio - o id do Anúncio
     */
    public void setIdAnuncio(String idAnuncio){
        if (idAnuncio == null || idAnuncio.trim().isEmpty()) {
            throw new IllegalArgumentException("Id do anúncio é inválido!");
        }
        this.idAnuncio = idAnuncio;
    }

    /**
     * Define a data de início da publicitação do anúncio
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
     * Define a data do fim da publicitação do anúncio
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
     * Define a data de início da candidatura do anúncio
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
     * Define a data do fim da candidatura do anúncio
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
     * Define a data de início do processo de seriação do anúncio
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
     * Define a data do fim do processo de seriação do anúncio
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
     * Devolve o id do Anuncio 
     * @return idAnuncio
     */
    public String getIdAnuncio(){
        return idAnuncio;
    }

    /**
     * Devolve a data de início da publicitação do anúncio
     * @return
     */
    public Data getDtInicioPub() {
       return dtInicioPublicitacao;
    }
    
    /**
     * Devolve a data do fim da publicitação do anúncio
     * @return dtFimPublicitacao
     */
    public Data getDtFimPub() {
       return dtFimPublicitacao;
    }
    
    /**
     * Devolve a data de início da candidatura do anúncio
     * @return dtInicioCandidatura
     */
    public Data getDtInicioCand() {
       return dtInicioCandidatura;
    }
    
    /**
     * Devolve a data do fim da candidatura do anúncio
     * @return
     */
    public Data getDtFimCand() {
       return dtFimCandidatura;
    }
    
    /**
     * Devolve a data de início do processo de seriação do anúncio
     * @return dtInicioSeriacao
     */
    public Data getDtInicioSeriacao() {
       return dtInicioSeriacao;
    }
    
    /**
     * Devolve a data do fim do processo de seriação do anúncio
     * @return dtFimSeriacao
     */
    public Data getDtFimSeriacao() {
       return dtFimSeriacao; 
    }

    public String getReferenciaTarefa() {
        return referenciaTarefa;
    }

    public void setReferenciaTarefa(String referenciaTarefa) {
        this.referenciaTarefa = referenciaTarefa;
    }

    public String getNifOrganizacao() {
        return nifOrganizacao;
    }

    public void setNifOrganizacao(String nifOrganizacao) {
        this.nifOrganizacao = nifOrganizacao;
    }

    public String getIdTipoRegimento() {
        return idTipoRegimento;
    }

    public void setIdTipoRegimento(String idTipoRegimento) {
        this.idTipoRegimento = idTipoRegimento;
    }
    

 /*   
    public TipoStatusAnuncio getStatusAnuncio(Data dataAtual) throws AnuncioInvalidoException{
        if(dataAtual.isMaior(this.dtFimPublicitacao)){

            return TipoStatusAnuncio.EXPIRADOS;
        }
        else{
            if(dataAtual.compareTo(this.dtInicioSeriacao) >= 0 && dataAtual.compareTo(this.dtFimSeriacao) <= 0){
                return TipoStatusAnuncio.PERIODO_DE_SERIACAO;
            }
            if(dataAtual.compareTo(this.dtInicioCandidatura) >= 0 && dataAtual.compareTo(this.dtFimCandidatura) <= 0){
                return TipoStatusAnuncio.PERIODO_DE_CANDIDATURA;
            }
        }        
        throw new AnuncioInvalidoException ("O anúncio é inválido!");
    }*/
    
    /**
     * Verifica se uma data inserida como texto tem formato válido
     * @param dataTexto - data em formato aaaa/mm/dd
     * @return a dataTexto em formato da classe Data
     */
    public Data eFormaData(String dataTexto){
        try{
            int dia = Integer.parseInt(dataTexto.substring(8,10));
            int mes = Integer.parseInt(dataTexto.substring(5,7));
            int ano = Integer.parseInt(dataTexto.substring(0,4));
            return new Data(ano, mes, dia);
        }
        catch(IllegalArgumentException iae){
            throw new DataInvalidaException("A data informada é inválida!");
        }
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
                + " |Fim seriação: %-12s", idAnuncio, dtInicioPublicitacao.toAnoMesDiaString(),
                dtFimPublicitacao.toAnoMesDiaString(), dtInicioCandidatura.toAnoMesDiaString(),
                dtFimCandidatura.toAnoMesDiaString(), dtInicioSeriacao.toAnoMesDiaString(),
                dtFimSeriacao.toAnoMesDiaString());
    }
    
    /**
     * Representação textual da classe Anuncio
     * @return o id do anúncio e as datas de: início e fim da publicitação, início
     * e fim do período de candidatura e do início e do fim do período de seriação
     */   
    public String toStringCompleto(){
        return String.format("ID: %s  %nInício publicitação: %s"
                + "%nFim publicitação: %s %nInício candidatura: %s"
                + "%nFim candidatura: %s %nInício seriação: %s"
                + "%nFim seriação: %s", idAnuncio, dtInicioPublicitacao.toAnoMesDiaString(),
                dtFimPublicitacao.toAnoMesDiaString(), dtInicioCandidatura.toAnoMesDiaString(),
                dtFimCandidatura.toAnoMesDiaString(), dtInicioSeriacao.toAnoMesDiaString(),
                dtFimSeriacao.toAnoMesDiaString());
    }
}
