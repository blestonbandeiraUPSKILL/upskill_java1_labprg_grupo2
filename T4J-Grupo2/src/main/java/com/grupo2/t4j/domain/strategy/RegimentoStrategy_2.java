/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.domain.strategy;

/**
 *
 * @author CAD
 */

import com.grupo2.t4j.domain.Candidatura;
import com.grupo2.t4j.domain.RegimentoStrategy;
import com.grupo2.t4j.persistence.FabricaRepositorios;
import com.grupo2.t4j.persistence.RepositorioCandidatura;
import com.grupo2.t4j.persistence.RepositorioClassificacao;
import com.grupo2.t4j.persistence.RepositorioSeriacao;
import com.grupo2.t4j.persistence.RepositorioTarefa;
import com.grupo2.t4j.persistence.database.FabricaRepositoriosDatabase;
import java.sql.SQLException;
import java.util.List;
public class RegimentoStrategy_2 implements RegimentoStrategy{
    
    private FabricaRepositorios fabricaRepositorios = new FabricaRepositoriosDatabase();
    private RepositorioTarefa repositorioTarefa = fabricaRepositorios.getRepositorioTarefa();
    private RepositorioCandidatura repositorioCandidatura = fabricaRepositorios.getRepositorioCandidatura();
    private RepositorioClassificacao repositorioClassificacao = fabricaRepositorios.getRepositorioClassificacao();
    private RepositorioSeriacao repositorioSeriacao = fabricaRepositorios.getRepositorioSeriacao();
    
    @Override
    public boolean seriar(int idAnuncio)throws SQLException{
        List<Candidatura> candidaturas = repositorioCandidatura.getAllByIdAnuncio(idAnuncio);
        
        return false;
    }
    
    @Override
    public boolean atribuir(int idAnuncio){
        return false;
    }
}
