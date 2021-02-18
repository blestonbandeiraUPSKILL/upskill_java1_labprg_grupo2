package com.grupo2.t4j.persistence.inmemory;

import com.grupo2.t4j.persistence.*;


public class FabricaRepositoriosInMemory implements FabricaRepositorios {

    @Override
    public RepositorioAdministrativoInMemory getRepositorioAdministrativo() {
        return new RepositorioAdministrativoInMemory();
    }

    @Override
    public RepositorioAnuncio getRepositorioAnuncio() {
        return new RepositorioAnuncioInMemory();
    }

    @Override
    public RepositorioAreaActividade getRepositorioAreaActividade() {
        return new RepositorioAreaActividadeInMemory();
    }

    @Override
    public RepositorioCaracterizacaoCT getRepositorioCaracterizacaoCT() {
        return new RepositorioCaracterizacaoCTInMemory();
    }

    @Override
    public RepositorioCategoriaTarefa getRepositorioCategoriaTarefa() {
        return new RepositorioCategoriaTarefaInMemory();
    }

    @Override
    public RepositorioColaborador getRepositorioColaborador() {
        return new RepositorioColaboradorInMemory();
    }

    @Override
    public RepositorioCompetenciaTecnica getRepositorioCompetenciaTecnica() {
        return new RepositorioCompetenciaTecnicaInMemory();
    }

    @Override
    public RepositorioOrganizacao getRepositorioOrganizacao() {
        return new RepositorioOrganizacaoInMemory();
    }

    @Override
    public RepositorioTarefa getRepositorioTarefa() {
        return new RepositorioTarefaInMemory();
    }

    @Override
    public RepositorioUtilizador getRepositorioUtilizador() {
        return new RepositorioUtilizadorInMemory();
    }


}
