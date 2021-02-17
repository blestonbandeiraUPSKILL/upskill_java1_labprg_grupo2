package com.grupo2.t4j.persistence;



public interface FabricaRepositorios {

    RepositorioAdministrativo getRepositorioAdministrativo();
    RepositorioAnuncio getRepositorioAnuncio();
    RepositorioAreaActividade getRepositorioAreaActividade();
    RepositorioCaracterizacaoCT getRepositorioCaracterizacaoCT();
    RepositorioCategoriaTarefa getRepositorioCategoriaTarefa();
    RepositorioColaborador getRepositorioColaborador();
    RepositorioCompetenciaTecnica getRepositorioCompetenciaTecnica();
    RepositorioOrganizacao getRepositorioOrganizacao();
    RepositorioTarefa getRepositorioTarefa();
    RepositorioUtilizador getRepositorioUtilizador();
}
