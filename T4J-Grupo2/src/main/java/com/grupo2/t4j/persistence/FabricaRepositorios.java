package com.grupo2.t4j.persistence;



public interface FabricaRepositorios {

    RepositorioAdministrativo getRepositorioAdministrativo();
    RepositorioAnuncio getRepositorioAnuncio();
    RepositorioAreaActividade getRepositorioAreaActividade();
    RepositorioCaracterizacaoCT getRepositorioCaracterizacaoCT();
    RepositorioCategoriaTarefa getRepositorioCategoriaTarefa();
    RepositorioColaborador getRepositorioColaborador();
    RepositorioCompetenciaTecnica getRepositorioCompetenciaTecnica();
    RepositorioEnderecoPostal getRepositorioEnderecoPostal();
    RepositorioFreelancer getRepositorioFreelancer();
    RepositorioOrganizacao getRepositorioOrganizacao();
    RepositorioTarefa getRepositorioTarefa();
    RepositorioGrauProficiencia getRepositorioGrauProficiencia();
    RepositorioUtilizador getRepositorioUtilizador();
    RepositorioCandidatura getRepositorioCandidatura();
    RepositorioReconhecimentoGP getRepositorioReconhecimentoGP();
}
