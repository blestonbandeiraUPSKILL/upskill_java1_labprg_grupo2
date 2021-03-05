package com.grupo2.t4j.domain;


import com.grupo2.t4j.exception.DataInvalidaException;
import com.grupo2.t4j.exception.DiaInvalidoException;
import com.grupo2.t4j.exception.MesInvalidoException;

import java.time.LocalDate;
import java.util.Calendar;

public class Data implements Comparable<Data>{

    /**
     * O ano da data
     */
    private int ano;
    /**
     * O mes da data
     */
    private Mes mes;
    /**
     * O dia da data
     */
    private int dia;

    /**
     * O ano da data por omissao
     */
    private static final int ANO_POR_OMISSAO = 1;
    
    /**
     * O mes da data por omissao
     */
    private static final Mes MES_POR_OMISSAO = Mes.JANEIRO;
    
    /**
     * O dia da data por omissao
     */
    private static final int DIA_POR_OMISSAO = 1;

    /**
     * Construtor data por omissao
     */
    public Data() {
        this.ano = ANO_POR_OMISSAO;
        this.mes = MES_POR_OMISSAO;
        this.dia = DIA_POR_OMISSAO;
    }
    
    
/**
 * Construtor data apenas com ano
 * @param ano 
 */
    public Data(int ano) {
        this.ano = ano;
    }
/**
 * Construtor com data local
 * @param localDate 
 */
    public Data(LocalDate localDate) {
        this(localDate.getYear(), localDate.getMonth().getValue(), localDate.getDayOfMonth());
    }

    /**
     * Construtor completo data
     * @param ano
     * @param mes
     * @param dia 
     */
    public Data(int ano, int mes, int dia) {
        setData(ano, mes, dia);
    }

    /**
     * Construtor copia
     * @param outraData 
     */
    public Data(Data outraData) {
       /* ano = outraData.ano;
        mes = outraData.mes;
        dia = outraData.dia;*/

        setData(outraData.ano, outraData.getMes(), outraData.dia);
    }
    
    /**
     * Transforma uma data inserida como texto no formato Data
     * @param dataTexto - data em formato aaaa/mm/dd
     * @return a dataTexto em formato da classe Data
     */
    public Data (String dataTexto){
         try{
            int dia = Integer.parseInt(dataTexto.substring(8,10));
            int mes = Integer.parseInt(dataTexto.substring(5,7));
            int ano = Integer.parseInt(dataTexto.substring(0,4));
            setData(ano, mes, dia);
        }
        catch(IllegalArgumentException iae){
            throw new DataInvalidaException("A data informada é inválida!");
        }
    }

    /**
     * Devolve o ano da data
     * @return ano
     */
    public int getAno() {
        return ano;
    }
/**
 * Devolve o mes da data
 * @return numero do mes
 */
    public int getMes() {
        return mes.ordinal() + 1;
    }
/**
 * Devolve o dia da data
 * @return 
 */
    public int getDia() {
        return dia;
    }
/**
 * Atualiza e valida uma data
 * @param ano
 * @param mes
 * @param dia 
 */
    public final void setData(int ano, int mes, int dia) {
        this.ano = ano;

        if (mes < 1 || mes > 12) {
            throw new MesInvalidoException();
        }
        this.mes = Mes.obterMes(mes);

        if (dia < 1 || dia > Mes.obterMes(mes).numeroDeDias(ano)) {
            throw new DiaInvalidoException();
        }

        this.dia = dia;
    }

    /**
     * Representacao textual da data no com dia da semana
     * @return 
     */
    @Override
    public String toString() {
        return String.format("%s, %d de %s de %d", diaDaSemana(), dia, mes, ano);
    }

    /**
     * Representacao textual da data com formato aaaa/mm/dd
     * @return 
     */
    public String toAnoMesDiaString() {
        return String.format("%04d/%02d/%02d", ano, mes.ordinal() + 1, dia);
    }

    /**
     * Verifica se duas datas são iguais
     * @param outroObjecto
     * @return 
     */
    @Override
    public boolean equals(Object outroObjecto) {
        if (this == outroObjecto) {
            return true;
        }
        if (outroObjecto == null || getClass() != outroObjecto.getClass()) {
            return false;
        }
        Data outraData = (Data) outroObjecto;
        return (ano == outraData.ano) &&
                mes.equals(outraData.mes) &&
                (dia == outraData.dia);
    }

    /**
     * compara duas datas
     * @param outraData
     * @return 
     */
    @Override
    public int compareTo(Data outraData) {
        return (outraData.isMaior(this)) ? -1 : (isMaior(outraData)) ? 1 : 0;
    }

    /**
     * Devolve o dia da semana a que se refere uma determinada data
     * @return 
     */
    public String diaDaSemana() {
        int totalDias = contaDias();
        totalDias = totalDias % 7;

        return DiaDaSemana.designacaoDiaDaSemana(totalDias);
    }

    /**
     * verifica se uma data e mais recente que outra
     * @param outraData
     * @return 
     */
    public boolean isMaior(Data outraData) {
        int totalDias = contaDias();
        int totalDias1 = outraData.contaDias();

        return totalDias > totalDias1;
    }

    /**
     * devolve a diferenca de dias entre duas datas
     * @param outraData
     * @return 
     */
    public int diferenca(Data outraData) {
        int totalDias = contaDias();
        int totalDias1 = outraData.contaDias();

        return Math.abs(totalDias - totalDias1);
    }
    
    /**
     * devolve a diferenca de dias entre duas datas
     * @param outraData
     * @return 
     */
    public int diferenca(int ano, int mes, int dia) {
        int totalDias = contaDias();
        Data outraData = new Data(ano, mes, dia);
        int totalDias1 = outraData.contaDias();

        return Math.abs(totalDias - totalDias1);
    }
    
    /**
     * verifica se um dado ano e bissexto
     * @param ano
     * @return 
     */
    public static boolean isAnoBissexto(int ano) {
        return ano % 4 == 0 &&
                ano % 100 != 0 ||
                ano % 400 == 0;
    }

    /**
     * Devolve a data actual
     * @return 
     */
    public static Data dataActual() {
        Calendar hoje = Calendar.getInstance();
        int ano = hoje.get(Calendar.YEAR);
        int mes = hoje.get(Calendar.MONTH) + 1;
        int dia = hoje.get(Calendar.DAY_OF_MONTH);

        return new Data(ano, mes, dia);
    }
    
    /**
     * conta o númerp de dias desde o ano 1
     * @return 
     */
    public int contaDias() {
        int totalDias = 0;

        for (int i = 1; i < ano; i++) {
            totalDias += isAnoBissexto(i) ? 366 : 365;
        }

        for (int i = 1; i < mes.ordinal() + 1; i++) {
            totalDias += Mes.obterMes(i).numeroDeDias(ano);
        }

        totalDias += dia;

        return totalDias;
    }

    /**
     * Determina uma idade de acordo com a data de nascimento
     * @param dataNascimento
     * @return 
     */
    public int getIdade(Data dataNascimento) {
        int idade = Data.dataActual().getAno() - dataNascimento.getAno();
        if (Data.dataActual().getMes() < dataNascimento.getMes()
                || ((Data.dataActual().getMes() == dataNascimento.getMes())
                && Data.dataActual().getDia() < dataNascimento.getDia())) {
            return idade - 1;
        }
        return idade;
    }
}
