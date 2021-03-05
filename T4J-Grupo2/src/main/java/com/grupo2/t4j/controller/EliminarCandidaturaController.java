/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.controller;

/**
 *
 * @author marta
 */
public class EliminarCandidaturaController {
    
    public boolean deleteCandidatura(int idCandidatura) {
        return repositorioCandidatura.deleteCandidatura(idCandidatura);
    }
    
}
