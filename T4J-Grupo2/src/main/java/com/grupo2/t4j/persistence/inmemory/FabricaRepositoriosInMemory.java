package com.grupo2.t4j.persistence.inmemory;

import com.grupo2.t4j.persistence.*;


public class FabricaRepositoriosInMemory implements FabricaRepositorios {

    @Override
    public RepositorioAdministrativoInMemory getRepositorioAdministrativo() {
        return new RepositorioAdministrativoInMemory();
    }

    @Override
    public RepositorioAnuncio getRepositorioAnuncio() {
        return null;
    }

    @Override
    public RepositorioAreaActividade getRepositorioAreaActividade() {
        return new RepositorioAreaActividadeInMemory();
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
    public RepositorioOrganizacao getRepositorioOrganizacao() {
        return null;
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
