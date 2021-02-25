package com.grupo2.t4j.model;

import java.io.Serializable;
import java.util.Calendar;
import com.grupo2.t4j.exception.*;

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


    
    public Anuncio(){
    }
    
    /**
     * Construtor completo da classe Anuncio
     * @param dtInicioPublicitacao - data de início da publicitação do anúncio
     * @param dtFimPublicitacao - data do fim da publicitação do anúncio
     * @param dtInicioCandidatura - data de início da candidatura do anúncio
     * @param dtFimCandidatura - data do fim da candidatura do anúncio
     * @param dtInicioSeriacao - data de início do processo de seriação do anúncio
     * @param dtFimSeriacao - data do fim do processo de seriação do anúncio
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

    public Anuncio(int idAnuncio, String referenciaTarefa, String nifOrganizacao, String dataInicioPublicitacao,
                   String dataFimPublicitacao, String dataInicioCandidatura, String dataFimCandidatura, String dataInicioSeriacao, String dataFimSeriacao) {
        setIdAnuncio(idAnuncio);
        setReferenciaTarefa(referenciaTarefa);
        setNifOrganizacao(nifOrganizacao);
        setDtInicioPub(dataInicioPublicitacao);
        setDtFimPub(dataFimPublicitacao);
        setDtInicioCand(dataInicioCandidatura);
        setDtFimCand(dataFimCandidatura);
        setDtInicioSeriacao(dataInicioSeriacao);
        setDtFimSeriacao(dataFimSeriacao);
    }

    public Anuncio(String referencia, String nifOrganizacao, String dataInicioPublicitacao, String dataFimPublicitacao, String dataInicioCandidatura,
                   String dataFimCandidatura, String dataInicioSeriacao, String dataFimSeriacao, int idTipoRegimento) {
        setReferenciaTarefa(referencia);
        setNifOrganizacao(nifOrganizacao);
        setDtInicioPub(dataInicioPublicitacao);
        setDtFimPub(dataFimPublicitacao);
        setDtInicioCand(dataInicioCandidatura);
        setDtFimCand(dataFimCandidatura);
        setDtInicioSeriacao(dataInicioSeriacao);
        setDtFimSeriacao(dataFimSeriacao);
        setIdTipoRegimento(idTipoRegimento);
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
     * Define a data de início da publicitação do anúncio
     * @param dtInicioPublicitacao
     */
    public void setDtInicioPub(String dtInicioPublicitacao) {
       /* if(dtInicioPublicitacao.compareTo(hoje) >= 0){*/
           this.dtInicioPublicitacao = dtInicioPublicitacao;
       /* }
        else{
           throw new DataInvalidaException("A data de início de publicitação deve "
                   + "ser maior ou igual à data atual.");
        }      */
    }
    
    /**
     * Define a data do fim da publicitação do anúncio
     * @param dtFimPublicitacao
     */
    public void setDtFimPub(String dtFimPublicitacao) {
       /* if(dtFimPublicitacao.isMaior(dtInicioPublicitacao)){*/
           this.dtFimPublicitacao = dtFimPublicitacao;
       /* }
        else{
           throw new DataInvalidaException("A data do fim da publicitação deve ser "
                   + "maior que a data de início da publicitação.");
        }      */
    }
    
    /**
     * Define a data de início da candidatura do anúncio
     * @param dtInicioCandidatura
     */
    public void setDtInicioCand(String dtInicioCandidatura) {
      /*  if((dtInicioCandidatura.compareTo(dtInicioPublicitacao) >=0) &&
                (dtInicioCandidatura.compareTo(dtFimPublicitacao) <=0)){*/
           this.dtInicioCandidatura = dtInicioCandidatura;
       /* }
        else{
           throw new DataInvalidaException("A data do início da candidatura deve"
                   + "estar contida dentro do período de publicitação.");
        }*/
    }
    
    /**
     * Define a data do fim da candidatura do anúncio
     * @param dtFimCandidatura
     */
    public void setDtFimCand(String dtFimCandidatura) {
       /* if((dtFimCandidatura.compareTo(dtInicioCandidatura) >=0) &&
                (dtFimCandidatura.compareTo(dtFimPublicitacao) <=0)){*/
           this.dtFimCandidatura = dtFimCandidatura;
        /*}
        else{
           throw new DataInvalidaException("A data do fim da candidatura deve"
                   + " ser maior do que a data de início do processo de candidatura, e"
                   + " deve estar contida dentro do período de publicitação.");
        }      */
    }
    
    /**
     * Define a data de início do processo de seriação do anúncio
     * @param dtInicioSeriacao
     */
    public void setDtInicioSeriacao(String dtInicioSeriacao) {
       /* if((dtInicioSeriacao.compareTo(dtInicioPublicitacao) >=0) &&
                (dtInicioSeriacao.compareTo(dtFimPublicitacao) <=0) &&
               (dtInicioSeriacao.isMaior(dtFimCandidatura))){*/
            this.dtInicioSeriacao = dtInicioSeriacao;
        /*}
        else{
           throw new DataInvalidaException("A data do início da seriação deve"
                   + "estar contida dentro do período de publicitação e começar"
                   + "após o término do processo de candidaturas.");
        } */
    }
    
    /**
     * Define a data do fim do processo de seriação do anúncio
     * @param dtFimSeriacao
     */
    public void setDtFimSeriacao(String dtFimSeriacao) {
        /*if((dtFimSeriacao.compareTo(dtInicioSeriacao) >=0) &&
                (dtFimSeriacao.compareTo(dtFimPublicitacao) <=0)){*/
            this.dtFimSeriacao = dtFimSeriacao;
        /*}
        else{
           throw new DataInvalidaException("A data do fim da seriação deve"
                   + "estar contida dentro do período de publicitação ser maior"
                   + " ou igual à data de início da seriação.");
        }*/
        
    }
    
    /**
     * Devolve o id do Anuncio 
     * @return idAnuncio
     */
    public int getIdAnuncio(){
        return idAnuncio;
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

    public int getIdTipoRegimento() {
        return idTipoRegimento;
    }

    public void setIdTipoRegimento(int idTipoRegimento) {
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
                + " |Fim seriação: %-12s", idAnuncio, dtInicioPublicitacao,
                dtFimPublicitacao, dtInicioCandidatura,
                dtFimCandidatura, dtInicioSeriacao,
                dtFimSeriacao);
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
                + "%nFim seriação: %s", idAnuncio, dtInicioPublicitacao,
                dtFimPublicitacao, dtInicioCandidatura,
                dtFimCandidatura, dtInicioSeriacao,
                dtFimSeriacao);
    }
}
