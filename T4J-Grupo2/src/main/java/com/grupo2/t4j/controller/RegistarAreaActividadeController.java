/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.controller;

import com.grupo2.t4j.repository.RepositorioAreaActividade;

/**
 *
 * @author acris
 */
public class RegistarAreaActividadeController {

    public boolean registarAreaActividade(String codigo, String descricaoBreve, String descricaoDetalhada) {
        return RepositorioAreaActividade.getInstance().addAreaActividade(codigo, descricaoBreve, descricaoDetalhada);
    }
    
}