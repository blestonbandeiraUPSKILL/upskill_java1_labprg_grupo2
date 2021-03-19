package com.grupo2.t4j.persistence.database;

/**
 *
 * @author CAD
 */

import com.grupo2.t4j.domain.Atribuicao;
import com.grupo2.t4j.persistence.RepositorioAtribuicao;
import com.grupo2.t4j.utils.DBConnectionHandler;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RepositorioAtribuicaoDatabase implements RepositorioAtribuicao{

    private static RepositorioAtribuicaoDatabase repositorioAtribuicaoDatabase;

    private RepositorioAtribuicaoDatabase(){

    }

    public static RepositorioAtribuicaoDatabase getInstance(){
        if(repositorioAtribuicaoDatabase == null){
            repositorioAtribuicaoDatabase = new RepositorioAtribuicaoDatabase();
        }
        return repositorioAtribuicaoDatabase;
    }

    @Override
    public boolean save(String nifOrganizacao, String refTarefa, int idAnuncio, int idCandidatura, String emailFreelancer,
                 double valorAceito, int numDiasAceito, String codigoAtribuicao) throws SQLException{
        return false;
    }

    @Override
    public boolean save(String nifOrganizacao, String refTarefa, int idAnuncio, int idCandidatura, String emailFreelancer,
                 double valorAceito, int numDiasAceito, String codigoAtribuicao, String dataInicioTarefa) throws SQLException{
        return false;
    }

    @Override
    public boolean save(Atribuicao atribuicao) throws SQLException{
        return false;
    }

    @Override
    public Atribuicao findAtribuicaoByAnuncio(int idAnuncio) throws SQLException{
        return new Atribuicao();
    }

    @Override
    public List<Atribuicao> getAllByOrganizacao(String nifOrganizacao) throws SQLException{
        return new ArrayList<>();
    }
}
