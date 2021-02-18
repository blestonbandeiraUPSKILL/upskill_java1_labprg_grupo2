package com.grupo2.t4j.persistence.database;

import com.grupo2.t4j.persistence.*;

public class FabricaRepositoriosDatabase implements FabricaRepositorios {

    @Override
    public RepositorioAdministrativo getRepositorioAdministrativo() {
        return new RepositorioAdministrativoDatabase();
    }

    @Override
    public RepositorioAnuncio getRepositorioAnuncio() {
        return null;
    }

    @Override
    public RepositorioAreaActividade getRepositorioAreaActividade() {
        return new RepositorioAreaActividadeDatabase();
    }

    @Override
    public RepositorioCaracterizacaoCT getRepositorioCaracterizacaoCT() {
        return null;
    }

    @Override
    public RepositorioCategoriaTarefa getRepositorioCategoriaTarefa() {
        return null;
    }

    @Override
    public RepositorioColaborador getRepositorioColaborador() {
        return null;
    }

    @Override
    public RepositorioCompetenciaTecnica getRepositorioCompetenciaTecnica() {
        return null;
    }

    @Override
    public RepositorioEnderecoPostal getRepositorioEnderecoPostal() {
        return new RepositorioEnderecoPostalDatabase();
    }

    @Override
    public RepositorioFreelancer getRepositorioFreelancer() {
        return null;
    }

    @Override
    public RepositorioGrauProficiencia getRepositorioGrauProficiencia() {
        return new RepositorioGrauProficienciaDatabase();
    }

    @Override
    public RepositorioUtilizador getRepositorioUtilizador() {
        return null;
    }

    @Override
    public RepositorioOrganizacao getRepositorioOrganizacao() {
        return null;
    }

    @Override
    public RepositorioTarefa getRepositorioTarefa() {
        return null;
    }






}
