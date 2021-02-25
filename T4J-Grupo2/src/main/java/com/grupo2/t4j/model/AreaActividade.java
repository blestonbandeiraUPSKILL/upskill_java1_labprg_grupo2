package com.grupo2.t4j.model;

import com.grupo2.t4j.exception.AreaActividadeDuplicadaException;

import java.io.Serializable;
import java.util.Objects;

public class AreaActividade implements Serializable{
    

/**
 * O código único de cada Área de Actividade.
 */
    private String codigo;
    
/**
 * A descrição breve da Área de Actividade.
 */
    private String descBreve;

/**
 * A descrição detalhada da Área de Actividade.
 */
   private String descDetalhada;

    /**
     * Construtor vazio da classe AreaActividade
     */
    public AreaActividade() {
    }
    
    /**
     * Construtor completo da classe AreaActividade
     * @param codigo o código único de cada Área de Actividade.
     * @param descBreve a descrição breve da Área de Actividade.
     * @param descDetalhada a descrição detalhada da Área de Actividade.
     */
    public AreaActividade(String codigo, String descBreve, String descDetalhada){
        setCodigo(codigo);
        setDescBreve(descBreve);
        setDescDetalhada(descDetalhada);
    }
    
    /**
     * Construtor da classe AreaActividade
     * @param areaActividade é do tipo da classe AreaActividade
     */
    public AreaActividade(AreaActividade areaActividade){
        setCodigo(areaActividade.codigo);
        setDescBreve(areaActividade.descBreve);
        setDescDetalhada(areaActividade.descDetalhada);
    }

    /**
     * Verifica a validade do parâmetro recebido e regista o código da Área de Actividade
     * @param codigo o código único de cada Área de Actividade.
     */
    public void setCodigo(String codigo) {
        if (codigo == null || codigo.trim().isEmpty()) {
            throw new AreaActividadeDuplicadaException("O Código da Área de Actividade é um campo obrigatório!");
        }
       /* if (RepositorioAreaActividade.getInstance().getAreaActividadeByCodigo(codigo).equals(codigo)) {
            throw new AreaActividadeDuplicadaException("Código de Área de Actividade "
                    + "já existe!");
        }*/
        this.codigo = codigo;
    }
    
    /**
     * Verifica a validade do parâmetro recebido e regista a descrição breve da Área de Actividade
     * @param descBreve a descrição breve da Área de Actividade
     */
    public void setDescBreve(String descBreve) {
        if (descBreve == null || descBreve.trim().isEmpty()) {
            throw new IllegalArgumentException("Descrição breve é obrigatória!");
        }
        if (descBreve.length() < 5) {
            throw new IllegalArgumentException("Descrição breve tem de ter pelo menos 5 caracteres!");
        }
        this.descBreve = descBreve;
    }
    
    /**
     * Verifica a validade do parâmetro recebido e regista a descrição breve da Área de Actividade
     * @param descDetalhada a descrição detalhada da Área de Actividade
     */
    public void setDescDetalhada(String descDetalhada) {
        if (descDetalhada == null || descDetalhada.trim().isEmpty()) {
            throw new IllegalArgumentException("Descrição detalhada é obrigatória!");
        }
        if (descDetalhada.length() < 10) {
            throw new IllegalArgumentException("Descrição detalhada tem de ter pelo menos 10 caracteres!");
        }
        this.descDetalhada = descDetalhada;
    }
    
    /**
     * Devolve o código único de cada Área de Actividade.
     * @return codigo
     */
    public String getCodigo() {
        return codigo;
    }
    
    /**
     * Devolve a descrição breve da Área de Actividade.
     * @return descBreve
     */
    public String getDescBreve() {
        return descBreve;
    }

    /**
     * Devolve a descrição detalhada da Área de Actividade
     * @return descDetalhada
     */
    public String getDescDetalhada() {
        return descDetalhada;
    }
    
    /**
     * Representação textual da classe Área de Actividade  em formato de exibição
     * @return codigo e descBreve
     */ 
    @Override    
    public String toString(){
        return String.format("%s", descBreve);
    }
    
    /**
     * Representação textual da classe Área de Actividade
     * @return codigo, descBreve e descDetalhada
     */ 
    public String toStringCompleto(){
        return String.format("ID: %-10s |Descrição breve: %-20s |Descrição detalhada: %-120s", codigo, descBreve, descDetalhada );
    }

    @Override
    public boolean equals(Object areaActividade) {
        if (this == areaActividade)
            return true;
        if (areaActividade == null || getClass() != areaActividade.getClass())
            return false;
        AreaActividade that = (AreaActividade) areaActividade;
        return Objects.equals(codigo, that.codigo)
                && Objects.equals(descBreve, that.descBreve)
                && Objects.equals(descDetalhada, that.descDetalhada);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo, descBreve, descDetalhada);
    }
}