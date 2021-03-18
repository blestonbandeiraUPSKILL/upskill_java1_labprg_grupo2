package com.grupo2.t4j.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.grupo2.t4j.domain.Obrigatoriedade;

public class CaracterizacaoCTDTO {

    private int idCaracterizacao;

    private String codigoCategoria;
    private String descBreveCompetencia;
    private String designacaoGrau;
    private int codigoGP;
    private Obrigatoriedade obrigatoriedade;

    public CaracterizacaoCTDTO (){
    }

    public CaracterizacaoCTDTO(int idCaracterizacao, String codigoCategoria, String descBreveCompetencia,
                            String designacaoGrau, int codigoGP, Obrigatoriedade obrigatoriedade){
        this.idCaracterizacao = idCaracterizacao;
        this.codigoCategoria = codigoCategoria;
        this.descBreveCompetencia = descBreveCompetencia;
        this.designacaoGrau = designacaoGrau;
        this.codigoGP = codigoGP;
        this.obrigatoriedade = obrigatoriedade;
    }

    public int getIdCaracterizacao() {
        return idCaracterizacao;
    }

    public int getCodigoGP() {
        return codigoGP;
    }

    public Obrigatoriedade getObrigatoriedade() {
        return obrigatoriedade;
    }

    public String getCodigoCategoria() {
        return codigoCategoria;
    }

    @Override
    public String toString() {
        return String.format("Competencia Téncina: %-30s %nGrau de Proficiencia: %-20s "
                + "%nCarácter: %-15s", descBreveCompetencia, designacaoGrau, obrigatoriedade.toString());

    }
}
