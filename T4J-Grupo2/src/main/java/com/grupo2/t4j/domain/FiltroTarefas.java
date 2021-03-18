/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.domain;

/**
 *
 * @author acris
 */
public enum FiltroTarefas {
    
    TAREFAS_DA_ORGANIZACAO {
        public String toString() {
            return "Tarefas da Organização";
        }
    },
    TAREFAS_PUBLICADAS{
        public String toString() {
            return "Tarefas Publicadas";
        }
    },
    TAREFAS_PARA_PUBLICAR{
        public String toString() {
            return "Tarefas para Publicar";
        }
    }
    
    
    
    
}
