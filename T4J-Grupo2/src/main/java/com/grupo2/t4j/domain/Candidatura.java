package com.grupo2.t4j.domain;

import com.grupo2.t4j.exception.IdInvalidoException;

import java.io.Serializable;

public class Candidatura implements Serializable {
    
    /**
     * O id da Candidatura
     */
    private int idCandidatura;
    
    /**
     * O id do Anúncio a que se refere a Candidatura
     */
    private int idAnuncio;
    
    /**
     * O email do Freelancer que se candidatou ao anúncio
     */
    private String emailFreelancer;
    
    /**
     * A data da Candidatura
     */
    private String dataCandidatura;
    
    /**
     * A data de edição mais recente da Candidatura
     */
    private String dataEdicaoCandidatura;
    
    /**
     * O valor pretendido pelo Freelancer na sua candidatura a uma dada Tarefa
     */
    private double valorPretendido;
    
    /**
     * O número de dias que o Freelancer indica para a realização de uma dada Tarefa
     * a que se candidatou
     */
    private int numeroDias;
    
    /**
     * O texto de apresentação do Freelancer para sua candidatura a uma dada Tarefa
     */
    private String txtApresentacao;
    
    /**
     * O texto de motivação do Freelancer para sua candidatura a uma dada Tarefa
     */
    private String txtMotivacao;


    /**
     * Construtor vazio da classe Candidatura
     */
    public Candidatura (){
        
    }
    
    /**
     * O construtor completo da classe Candidatura
     * @param idCandidatura - o id da Candidatura
     * @param valorPretendido - o valor pretendido pelo Freelancer na sua candidatura a uma dada Tarefa
     * @param numeroDias - número de dias que o Freelancer indica para a realização de uma dada Tarefa
     * a que se candidatou
     * @param txtApresentacao - o texto de apresentação do Freelancer para sua candidatura a uma dada Tarefa
     * @param txtMotivacao - o texto de motivação do Freelancer para sua candidatura a uma dada Tarefa
     * @param idAnuncio - o id do Anúncio a que se refere a candidatura
     * @param emailFreelancer - o email do Freelancer em formato String
     * @param dataCandidatura - a data de Candidatura (sempre a data atual do calendário)
     * @param dataEdicaoCandidatura -  a data da edição da Candidatura (sempre a data atual do calendário)
     */
    public Candidatura(int idCandidatura, double valorPretendido, int numeroDias, 
            String txtApresentacao, String txtMotivacao, int idAnuncio, String emailFreelancer,
             String dataCandidatura, String dataEdicaoCandidatura){
        setIdCandidatura(idCandidatura);
        setValor(valorPretendido);
        setDias(numeroDias);
        setApresentacao(txtApresentacao);
        setMotivacao(txtMotivacao);
        setIdAnuncio(idAnuncio);
        setEmailFreelancer(emailFreelancer);
        setData(dataCandidatura);
        setDataEdicao(dataEdicaoCandidatura);
    }
    
    /**
     * O construtor da classe Candidatura sem data de edição da candidatura
     * (a data de edição da candidatura assume a mesma data da criação da candidatura)
     * @param idCandidatura - o id da Candidatura
     * @param valorPretendido - o valor pretendido pelo Freelancer na sua candidatura a uma dada Tarefa
     * @param numeroDias - número de dias que o Freelancer indica para a realização de uma dada Tarefa
     * a que se candidatou
     * @param txtApresentacao - o texto de apresentação do Freelancer para sua candidatura a uma dada Tarefa
     * @param txtMotivacao - o texto de motivação do Freelancer para sua candidatura a uma dada Tarefa
     * @param idAnuncio - o id do Anúncio a que se refere a candidatura
     * @param emailFreelancer - o email do Freelancer em formato String
     * @param dataCandidatura - a data de Candidatura (sempre a data atual do calendário)
     */
    public Candidatura(int idCandidatura, double valorPretendido, int numeroDias, 
            String txtApresentacao, String txtMotivacao, int idAnuncio, String emailFreelancer,
             String dataCandidatura){
        setIdCandidatura(idCandidatura);
        setValor(valorPretendido);
        setDias(numeroDias);
        setApresentacao(txtApresentacao);
        setMotivacao(txtMotivacao);
        setIdAnuncio(idAnuncio);
        setEmailFreelancer(emailFreelancer);
        setData(dataCandidatura);
        setDataEdicao(dataCandidatura);
    }
    
    /**
     * Construtor da classe Candidatura com texto de Apresentação e de Motivação 
     * com valores por omissão e sem a atribuição de datas (que é automática na BD)
     * @param idCandidatura - o id da Candidatura
     * @param valorPretendido - o valor pretendido pelo Freelancer na sua candidatura a uma dada Tarefa
     * @param numeroDias - número de dias que o Freelancer indica para a realização de uma dada Tarefa
     * a que se candidatou
     * @param idAnuncio - o id do Anúncio a que se refere a candidatura
     * @param emailFreelancer - o email do Freelancer em formato String     
     */
    public Candidatura(int idCandidatura, double valorPretendido, int numeroDias, 
            int idAnuncio, String emailFreelancer){
        setIdCandidatura(idCandidatura);
        setValor(valorPretendido);
        setDias(numeroDias);
        setApresentacao(" ");
        setMotivacao(" ");
        setIdAnuncio(idAnuncio);
        setEmailFreelancer(emailFreelancer);      
    }
    
    /**
     * Construtor da classe Candidatura 
     * @param candidatura é do tipo da classe Candidatura
     */
    public Candidatura(Candidatura candidatura){
        setIdCandidatura(candidatura.idCandidatura);
        setValor(candidatura.valorPretendido);
        setDias(candidatura.numeroDias);
        setApresentacao(candidatura.txtApresentacao);
        setMotivacao(candidatura.txtMotivacao);
        setIdAnuncio(candidatura.idAnuncio);
        setEmailFreelancer(candidatura.emailFreelancer);
        setData(candidatura.dataCandidatura);
        setDataEdicao(candidatura.dataEdicaoCandidatura);
        
    }
    
    /**
     * Construtor da classe Candidatura sem a atribuição de datas (que é automática 
     * na BD) e sem o idCandidatura que é criado automaticamente na BD
     * @param valorPretendido
     * @param numeroDias
     * @param txtApresentacao
     * @param txtMotivacao
     * @param idAnuncio
     * @param emailFreelanceR 
     */
    public Candidatura(Double valorPretendido, int numeroDias, String 
            txtApresentacao, String txtMotivacao, int idAnuncio, String emailFreelanceR){
        setValor(valorPretendido);
        setDias(numeroDias);
        setApresentacao(txtApresentacao);
        setMotivacao(txtMotivacao);
        setIdAnuncio(idAnuncio);
        setEmailFreelancer(emailFreelancer);        
    }
    
    /**
     * Define o id do Anúncio
     * @param idAnuncio 
     */
    public void setIdAnuncio(int idAnuncio) {
        if (idAnuncio > 0) {
            this.idAnuncio = idAnuncio;
        }
        else {
            throw new IdInvalidoException("O id não é válido.");
        }
    }

    /**
     * Define o id da Candidatura
     * @param idCandidatura - o id da Candidatura
     */
    public void setIdCandidatura(int idCandidatura){
        if (idCandidatura < 0 ) {
            throw new IdInvalidoException("Id da Candidatura é inválido!");
        }
        this.idCandidatura = idCandidatura;
    }
    
    /**
     * Define o email do Freelancer que se candidatou ao anúncio
     * @param emailFreelancer - o email do Freelancer em formato String
     */
    public void setEmailFreelancer(String emailFreelancer){
        this.emailFreelancer = emailFreelancer;
    }

    /**
     * Define a data de Candidatura como sendo sempre a data atual do calendário  
     */
    public void setData(String dataCandidatura){
        this.dataCandidatura = dataCandidatura;
    }
    
    /**
     * Define a data de edição da Candidatura como sendo sempre a data atual do calendário 
     * Caso a candidatura não tenha sido editada, a data de edição é igual à 
     * data de criação da candidatura.
     */
    public void setDataEdicao(String dataEdicaoCandidatura){
        this.dataEdicaoCandidatura = dataEdicaoCandidatura;
    }
    
    /**
     * Define o valor pretendido pelo Freelancer para realização da Tarefa à qual
     * se candidatou em euros
     * @param valorPretendido - o valor pretendido pelo serviço, em euros
     */
    public void setValor(double valorPretendido){
        if (valorPretendido > 0) {
            this.valorPretendido = valorPretendido;
        }
        else{
            throw new IllegalArgumentException("O valor prentendido informado não"
                    + "é válido!");
        }
    }
    
    /**
     * Define o número de dias que o Freelancer indica para a realização de uma dada Tarefa
     * a que se candidatou
     * @param numeroDias
     */
    public void setDias(int numeroDias){
        if(numeroDias > 0){
            this.numeroDias = numeroDias;
        }
        else{
            throw new IllegalArgumentException("O número de dias informado não"
                    + "é válido!");
        }
    }
    
    /**
     * Define o texto de apresentação do Freelancer para sua candidatura a uma dada Tarefa
     * @param txtApresentacao
     */
    public void setApresentacao(String txtApresentacao){
        this.txtApresentacao = txtApresentacao;
    }
    
    /**
     * Define o texto de motivação do Freelancer para sua candidatura a uma dada Tarefa
     * @param txtMotivacao
     */
    public void setMotivacao(String txtMotivacao){
        this.txtMotivacao = txtMotivacao;
    }
    
    /**
     * Devolve o id da Candidatura
     * @return idCandidatura
     */
    public int getIdCandidatura(){
        return idCandidatura;
    }

    public int getIdAnuncio() {
        return idAnuncio;
    }

    /**
     * Devolve o email do Freelancer que se candidatou ao anúncio
     * @return emailFreelancer
     */
    public String getEmailFreelancer(){
        return emailFreelancer;
    }
    
    /**
     * Devolve a data de Candidatura 
     */
    public String getDataCandidatura(){
        return dataCandidatura;
    }
    
    /**
     * Devolve a data da edição da Candidatura 
     * Caso a candidatura não tenha sido editada, a data de edição é igual à 
     * data de criação da candidatura.
     * @return dataEdicaoCandidatura
     */
    public String getDataEdicaoCandidatura(){
        return dataEdicaoCandidatura;
    }
    
    /**
     * Devolve o valor pretendido pelo Freelancer para realização da Tarefa à qual
     * se candidatou em euros
     * @return valorPretendido
     */
    public double getValorPretendido(){
        return valorPretendido;
    }
    
    /**
     * Devolve o número de dias que o Freelancer indica para a realização de uma dada Tarefa
     * a que se candidatou
     * @return numeroDias
     */
    public int getNumeroDias(){
        return numeroDias;
    }
    
    /**
     * Devolve o texto de apresentação do Freelancer para sua candidatura a uma dada Tarefa
     * @return
     */
    public String getApresentacao(){
        return txtApresentacao;
    }
    
    /**
     * Devolve o texto de motivação do Freelancer para sua candidatura a uma dada Tarefa
     * @return
     */
    public String getMotivacao(){
        return txtMotivacao;
    }
    
    /**
     * Representação textual da classe Candidatura em formato de exibição
     * @return o id da candidatura, o email do Freelancer candidato, a data da 
     * candidatura em formato texto, o valor pretendido pelo Freelancer para a 
     * realização da Tarefa, o número de dias para a realização da Tarefa,
     * o texto de apresentação da Candidatura e o texto de Motivação para a 
     * Candidatura
     */   
    @Override
    public String toString(){
        return String.format("ID2: %-12s " +
                        "|Freelancer: %-20s " +
                        "|Data Candidatura: %-12s" +
                        "|Valor pretendido: %.2f euros " +
                        "|Número de dias: %-8d" +
                        "|Apresentação: %-50s " +
                        "|Motivação: %-50s",
                idCandidatura,
                emailFreelancer,
                dataCandidatura,
                valorPretendido,
                numeroDias,
                txtApresentacao,
                txtMotivacao);
    }
    
    /**
     * Representação textual da classe Candidatura em formato de exibição simplificado
     * @return o id do Anúncio, o id da Candidatura e o email do Freelancer
     */
    public String toStringIdEmail(){
        return String.format("IDAnuncio: %-10s |IDCand: %-10s |Freelancer: %-20s ", 
                idAnuncio, idCandidatura, emailFreelancer);
    }
    
    /**
     * Representação textual da classe Candidatura Completa
     * @return o id da candidatura, o email do Freelancer candidato, a data da 
     * candidatura em formato texto, o valor pretendido pelo Freelancer para a 
     * realização da Tarefa, o número de dias para a realização da Tarefa,
     * o texto de apresentação da Candidatura e o texto de Motivação para a 
     * Candidatura
     */   
    public String toStringCompleto(){
        return String.format("ID: %s  %nEmail do Freelancer: %s %nData Candidatura: %s"
                + "%nValor pretendido: %f.2  euros %nNúmero de dias: %"
                + "%nApresentação: %s %nMotivação: %s", idCandidatura, emailFreelancer, 
                dataCandidatura, valorPretendido,
                numeroDias, txtApresentacao, txtMotivacao);
    }    
}
