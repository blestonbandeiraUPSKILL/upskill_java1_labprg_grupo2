package com.grupo2.t4j.persistence;


import java.sql.SQLException;

public interface FabricaRepositorios {

    RepositorioAdministrativo getRepositorioAdministrativo();
    RepositorioAnuncio getRepositorioAnuncio();
    RepositorioAreaActividade getRepositorioAreaActividade();
    RepositorioCandidatura getRepositorioCandidatura();
    RepositorioCaracterizacaoCT getRepositorioCaracterizacaoCT();
    RepositorioCategoriaTarefa getRepositorioCategoriaTarefa();
    RepositorioClassificacao getRepositorioClassificacao();
    RepositorioColaborador getRepositorioColaborador();
    RepositorioCompetenciaTecnica getRepositorioCompetenciaTecnica();
    RepositorioEnderecoPostal getRepositorioEnderecoPostal() throws SQLException;
    RepositorioFreelancer getRepositorioFreelancer();
    RepositorioHabilitacaoAcademica getRepositorioHabilitacaoAcademica();
    RepositorioOrganizacao getRepositorioOrganizacao() throws SQLException;
    RepositorioSeriacao getRepositorioSeriacao();
    RepositorioTarefa getRepositorioTarefa();
    RepositorioGrauProficiencia getRepositorioGrauProficiencia() throws SQLException;
    RepositorioUtilizador getRepositorioUtilizador();
    RepositorioReconhecimentoGP getRepositorioReconhecimentoGP();
}
