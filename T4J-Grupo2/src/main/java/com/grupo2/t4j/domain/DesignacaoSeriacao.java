/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.domain;

/**
 *
 * @author CAD
 */
public enum DesignacaoSeriacao {
    SERIACAO_SUBJETIVA_ATRIBUICAO_OPCIONAL{
        
        @Override
        public String toString() {
            return "Seriação Subjetiva com Atribuição Opcional";
        }
    },
    SERIACAO_SUBJETIVA_ATRIBUICAO_OBRIGATORIA{
        @Override
        public String toString() {
            return "Seriação Subjetiva com Atribuição Obrigatória";
        }
    },
    SERIACAO_E_ATRIBUICAO_AUTOMATICA_MENOR_PRECO{
        @Override
        public String toString() {
            return "Seriaação e Atribuição Automática - 2º melhor preço";
        }
    }    
}
