/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.model;

import com.grupo2.t4j.exception.AreaActividadeInexistenteException;
import com.grupo2.t4j.exception.CodigoInvalidoException;
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
 * Construtor CompetenciaTecnica Completo
 * @param codigo
 * @param descricaoBreve
 * @param descricaoDetalhada
 *
 */
    public CompetenciaTecnica(String codigo, String descricaoBreve, String descricaoDetalhada, AreaActividade areaActividade, CaracterizacaoCT cct) {
        setCodigo(codigo);
        setDescricaoBreve(descricaoBreve);
        setDescricaoDetalhada(descricaoDetalhada);
        setAreaActividade(areaActividade);
        setCct(cct);    
    }
    
    /**
 * Construtor CompetenciaTecnica 
 * @param codigo
 * @param descricaoBreve
 * @param descricaoDetalhada
 *
 */
    public CompetenciaTecnica(String codigo, String descricaoBreve, String descricaoDetalhada) {
        setCodigo(codigo);
        setDescricaoBreve(descricaoBreve);
        setDescricaoDetalhada(descricaoDetalhada);    
    }
/**
 * Construtor Competencia Tecnica
 * @param compTec 
 */
    public CompetenciaTecnica(CompetenciaTecnica compTec){
        setCodigo(compTec.codigo);
        setDescricaoBreve(compTec.descricaoBreve);
        setDescricaoDetalhada(compTec.descricaoDetalhada);
    }

    public CompetenciaTecnica(String codigo, String descricaoBreve, String descricaoDetalhada, AreaActividade at) {
        setCodigo(codigo);
        setDescricaoBreve(descricaoBreve);
        setDescricaoDetalhada(descricaoDetalhada);
        setAreaActividade(at);
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
    public void setCodigo(String codigo) throws CodigoInvalidoException{
        if (codigo == null || codigo.trim().isEmpty()) {
            throw new CodigoInvalidoException("Deve introduzir uma descrição válida!");
        } else {
            this.codigo = codigo;
        }
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
    public void setDescricaoBreve(String descricaoBreve) throws DescricaoInvalidaException {
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
    public void setDescricaoDetalhada(String descricaoDetalhada) throws DescricaoInvalidaException{
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
        return String.format("Competencia Tecnica: %nID: %s%nDescricao Breve:"
                + " %s%n Descricao Detalhada: %s%n", codigo, descricaoBreve, descricaoDetalhada);
    }
    
}
