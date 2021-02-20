package com.grupo2.t4j.persistence;


import java.sql.SQLException;

public interface FabricaRepositorios {

    RepositorioAdministrativo getRepositorioAdministrativo();
    RepositorioAnuncio getRepositorioAnuncio();
    RepositorioAreaActividade getRepositorioAreaActividade();
    RepositorioCaracterizacaoCT getRepositorioCaracterizacaoCT();
    RepositorioCategoriaTarefa getRepositorioCategoriaTarefa();
    RepositorioColaborador getRepositorioColaborador();
    RepositorioCompetenciaTecnica getRepositorioCompetenciaTecnica();
    RepositorioEnderecoPostal getRepositorioEnderecoPostal() throws SQLException;
    RepositorioFreelancer getRepositorioFreelancer();
    RepositorioOrganizacao getRepositorioOrganizacao() throws SQLException;
    RepositorioTarefa getRepositorioTarefa();
    RepositorioGrauProficiencia getRepositorioGrauProficiencia() throws SQLException;
    RepositorioUtilizador getRepositorioUtilizador();
    RepositorioCandidatura getRepositorioCandidatura();
    RepositorioReconhecimentoGP getRepositorioReconhecimentoGP();
}
