package com.grupo2.t4j.persistence;


import java.sql.SQLException;

public interface FabricaRepositorios {

    RepositorioAnuncio getRepositorioAnuncio();
    RepositorioAreaActividade getRepositorioAreaActividade();
    RepositorioAtribuicao getRepositorioAtribuicao();
    RepositorioCandidatura getRepositorioCandidatura();
    RepositorioCaracterizacaoCT getRepositorioCaracterizacaoCT();
    RepositorioCategoriaTarefa getRepositorioCategoriaTarefa();
    RepositorioClassificacao getRepositorioClassificacao();
    RepositorioColaborador getRepositorioColaborador();
    RepositorioColaboradorSeriacao getRepositorioColaboradorSeriacao();
    RepositorioCompetenciaTecnica getRepositorioCompetenciaTecnica();
    RepositorioEnderecoPostal getRepositorioEnderecoPostal() throws SQLException;
    RepositorioFreelancer getRepositorioFreelancer();
    RepositorioHabilitacaoAcademica getRepositorioHabilitacaoAcademica();
    RepositorioOrganizacao getRepositorioOrganizacao() throws SQLException;
    RepositorioSeriacao getRepositorioSeriacao();
    RepositorioTarefa getRepositorioTarefa();
    RepositorioGrauProficiencia getRepositorioGrauProficiencia() throws SQLException;
    RepositorioTipoRegimento getRepositorioTipoRegimento();
    RepositorioReconhecimentoGP getRepositorioReconhecimentoGP();
}
