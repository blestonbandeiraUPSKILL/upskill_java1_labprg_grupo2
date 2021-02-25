package com.grupo2.t4j.persistence.inmemory;

import com.grupo2.t4j.persistence.*;


public class FabricaRepositoriosInMemory implements FabricaRepositorios {

    @Override
    public RepositorioAdministrativoInMemory getRepositorioAdministrativo() {
        return RepositorioAdministrativoInMemory.getInstance();
    }

    @Override
    public RepositorioAnuncio getRepositorioAnuncio() {
        return null;
    }

    @Override
    public RepositorioAreaActividade getRepositorioAreaActividade() {
        return RepositorioAreaActividadeInMemory.getInstance();
    }
    
    @Override
    public RepositorioCandidatura getRepositorioCandidatura() {
        return RepositorioCandidaturaInMemory.getInstance();
    }

    @Override
    public RepositorioCaracterizacaoCT getRepositorioCaracterizacaoCT() {
        return RepositorioCaracterizacaoCTInMemory.getInstance();
    }

    @Override
    public RepositorioCategoriaTarefa getRepositorioCategoriaTarefa() {
        return RepositorioCategoriaTarefaInMemory.getInstance();
    }

    @Override
    public RepositorioColaborador getRepositorioColaborador() {
        return RepositorioColaboradorInMemory.getInstance();
    }

    @Override
    public RepositorioCompetenciaTecnica getRepositorioCompetenciaTecnica() {
        return RepositorioCompetenciaTecnicaInMemory.getInstance();
    }

    @Override
    public RepositorioEnderecoPostal getRepositorioEnderecoPostal() {
        return RepositorioEnderecoPostalInMemory.getInstance();
    }

    @Override
    public RepositorioFreelancer getRepositorioFreelancer() {
        return RepositorioFreelancerInMemory.getInstance();
    }
    
    @Override
    public RepositorioHabilitacaoAcademica getRepositorioHabilitacaoAcademica() {
        return RepositorioHabilitacaoAcademicaInMemory.getInstance();
    }

    @Override
    public RepositorioGrauProficiencia getRepositorioGrauProficiencia() {
        return RepositorioGrauProficienciaInMemory.getInstance();
    }

    @Override
    public RepositorioOrganizacao getRepositorioOrganizacao() {
        return RepositorioOrganizacaoInMemory.getInstance();
    }
    
    @Override
    public RepositorioReconhecimentoGP getRepositorioReconhecimentoGP() {
        return RepositorioReconhecimentoGPInMemory.getInstance();
    }
    
    @Override
    public RepositorioTarefa getRepositorioTarefa() {
        return null;
    }

    @Override
    public RepositorioUtilizador getRepositorioUtilizador() {
        return null;
    } 
}
