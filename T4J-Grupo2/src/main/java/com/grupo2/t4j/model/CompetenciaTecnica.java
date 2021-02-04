/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.model;

import com.grupo2.t4j.exception.AreaActividadeInexistenteException;
import com.grupo2.t4j.exception.DescricaoInvalidaException;
import java.io.Serializable;

/**
 *
 * @author acris
 */
public class CompetenciaTecnica implements Serializable{

   
    
   /**
    * Atributos Competencia Tecnica
    */
    private String codigo;
    private String descricaoBreve;
    private String descricaoDetalhada;
    private AreaActividade areaActividade;
    private CaracterizacaoCT cct;
    
    private static final char SEPARADOR = ';';
    
/**
 * Construtor Competencia Tecnica Completo
 * @param codigo
 * @param descricaoBreve
 * @param descricaoDetalhada
 *
 */
    public CompetenciaTecnica(String codigo, String descricaoBreve, String descricaoDetalhada, AreaActividade areaActividade, CaracterizacaoCT cct) {
        this.codigo = codigo;
        this.descricaoBreve = descricaoBreve;
        this.descricaoDetalhada = descricaoDetalhada;
        this.areaActividade=areaActividade;
        this.cct=cct;
        
        
    }
    
    /**
 * Construtor Competencia Tecnica 
 * @param codigo
 * @param descricaoBreve
 * @param descricaoDetalhada
 *
 */
    public CompetenciaTecnica(String codigo, String descricaoBreve, String descricaoDetalhada) {
        this.codigo = codigo;
        this.descricaoBreve = descricaoBreve;
        this.descricaoDetalhada = descricaoDetalhada;
        
        
        
    }
/**
 * Construtor Classe Competencia Tecnica
 * @param compTec 
 */
    public CompetenciaTecnica(CompetenciaTecnica compTec){
        setCodigo(codigo);
        setDescricaoBreve(descricaoBreve);
        setDescricaoDetalhada(descricaoDetalhada);
        
    } 

    /**
     * @return the codigo
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the descricaoBreve
     */
    public String getDescricaoBreve() {
        return descricaoBreve;
    }

    /**
     * @param descricaoBreve the descricaoBreve to set
     */
    public void setDescricaoBreve(String descricaoBreve) {
        if (descricaoBreve == null || descricaoBreve.trim().isEmpty()) {
            throw new DescricaoInvalidaException("Deve introduzir uma descrição válida!");
        } else {
            this.descricaoBreve = descricaoBreve;
        }
    }

    /**
     * @return the descricaoDetalhada
     */
    public String getDescricaoDetalhada() {
        return descricaoDetalhada;
    }

    /**
     * @param descricaoDetalhada the descricaoDetalhada to set
     */
    public void setDescricaoDetalhada(String descricaoDetalhada) {
        if (descricaoDetalhada == null || descricaoDetalhada.trim().isEmpty()) {
            throw new DescricaoInvalidaException("Deve introduzir uma descrição válida!");
        } else {
            this.descricaoDetalhada = descricaoDetalhada;
        }
    }

    /**
     * @return the cct
     */
    public CaracterizacaoCT getCct() {
        return cct;
    }

    /**
     * @param cct the cct to set
     */
    public void setCct(CaracterizacaoCT cct) {
        this.cct = cct;
    } 

    /**
     * @return the areaActividade
     */
    public AreaActividade getAreaActividade() {
        return areaActividade;
    }

    /**
     * @param areaActividade the areaActividade to set
     */
    public void setAreaActividade(AreaActividade areaActividade) {
        if (areaActividade != null) {
            this.areaActividade = areaActividade;
        }else {
            throw new AreaActividadeInexistenteException ("A área de actividade não existe");
        }
    }

    /**
     * Representacao textual da classe competencia tecnica
     * @return 
     */
    @Override
    public String toString() {
        return "CompetenciaTecnica{" + "codigo: " + codigo + ",/ndescricaoBreve: " 
                + descricaoBreve + ", /ndescricaoDetalhada: " + descricaoDetalhada +
                ", /nareaActividade: " + areaActividade + ", /ngrau=" + cct + '}';
    }
    
    public String toStringExport() {
        return String.format("s%c%s%c%s", areaActividade.toString(), SEPARADOR, cct.toString());
    }
    
    /*public String toStringExport() {
        return String.format("%s%c%s%c%s%c%s", codigo, SEPARADOR, descricaoBreve, SEPARADOR, areaActividade.toString(), SEPARADOR, cct.toString());
    }*/
   
   /* public String[] getCompetenciaComoArray(String compTecnica) {
        String[] dados = compTecnica.trim().split(String.valueOf(SEPARADOR));
        int nrAtributos = 8;

        if (dados.length == nrAtributos) {
            try {
                new CompetenciaTecnica(dados[0],dados[1], dados[2], Integer.parseInt(dados[0]));
                
                return dados;
            }
            catch(Exception ex) {
                throw new RuntimeException("Dados Inválidos do Contacto");
            }
        }

        throw new RuntimeException("Dados Inválidos do Contacto");
    }*/   

   
}
