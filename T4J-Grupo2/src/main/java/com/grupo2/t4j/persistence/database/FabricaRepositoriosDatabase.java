package com.grupo2.t4j.persistence.database;

import com.grupo2.t4j.persistence.*;

import java.sql.SQLException;

public class FabricaRepositoriosDatabase implements FabricaRepositorios {

    @Override
    public RepositorioAnuncio getRepositorioAnuncio() {
        return RepositorioAnuncioDataBase.getInstance();
    }

    @Override
    public RepositorioAreaActividade getRepositorioAreaActividade() {
        return RepositorioAreaActividadeDatabase.getInstance();
    }
    
    @Override
    public RepositorioCandidatura getRepositorioCandidatura() {
        return RepositorioCandidaturaDatabase.getInstance();
    }

    @Override
    public RepositorioCaracterizacaoCT getRepositorioCaracterizacaoCT() {
        return RepositorioCaracterizacaoCTDatabase.getInstance();
    }

    @Override
    public RepositorioCategoriaTarefa getRepositorioCategoriaTarefa() {
        return RepositorioCategoriaTarefaDatabase.getInstance();
    }
    
    @Override
    public RepositorioClassificacao getRepositorioClassificacao() {
        return RepositorioClassificacaoDatabase.getInstance();
    }
    
    @Override
    public RepositorioColaborador getRepositorioColaborador() {
        return RepositorioColaboradorDatabase.getInstance();
    }
    
    @Override
    public RepositorioColaboradorSeriacao getRepositorioColaboradorSeriacao() {
        return RepositorioColaboradorSeriacaoDatabase.getInstance();
    }

    @Override
    public RepositorioCompetenciaTecnica getRepositorioCompetenciaTecnica() {
        return RepositorioCompetenciaTecnicaDatabase.getInstance();
    }

    @Override
    public RepositorioEnderecoPostal getRepositorioEnderecoPostal() throws SQLException {
        return RepositorioEnderecoPostalDatabase.getInstance();
    }

    @Override
    public RepositorioFreelancer getRepositorioFreelancer() {
        return RepositorioFreelancerDatabase.getInstance();
    }
    
    @Override
    public RepositorioHabilitacaoAcademica getRepositorioHabilitacaoAcademica() {
        return RepositorioHabilitacaoAcademicaDatabase.getInstance();
    }

    @Override
    public RepositorioGrauProficiencia getRepositorioGrauProficiencia() throws SQLException {
        return RepositorioGrauProficienciaDatabase.getInstance();
    }
    
    @Override
    public RepositorioTipoRegimento getRepositorioTipoRegimento() {
        return RepositorioTipoRegimentoDatabase.getInstance();
    }
    
    @Override
    public RepositorioSeriacao getRepositorioSeriacao() {
        return RepositorioSeriacaoDatabase.getInstance();
    }
    
    @Override
    public RepositorioOrganizacao getRepositorioOrganizacao() throws SQLException {
        return RepositorioOrganizacaoDatabase.getInstance();
    }
    
    @Override
    public RepositorioReconhecimentoGP getRepositorioReconhecimentoGP() {
        return  RepositorioReconhecimentoGPDatabase.getInstance();
    }
    @Override
    public RepositorioTarefa getRepositorioTarefa() {
        return RepositorioTarefaDatabase.getInstance();
    }    
}
