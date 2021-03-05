/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.persistence.database;

/**
 *
 * @author CAD
 */

import com.grupo2.t4j.exception.HabilitacaoAcademicaDuplicadaException;
import com.grupo2.t4j.domain.HabilitacaoAcademica;
import com.grupo2.t4j.domain.ReconhecimentoGP;
import com.grupo2.t4j.persistence.RepositorioHabilitacaoAcademica;
import com.grupo2.t4j.utils.DBConnectionHandler;

import java.sql.*;
import java.util.ArrayList;

public class RepositorioHabilitacaoAcademicaDatabase implements RepositorioHabilitacaoAcademica{
    
    /**
     * Define uma instância estática do Repositório em que estão
     * registados todas as Habilitações Acadêmicas dos Freelancers
     */
    private static RepositorioHabilitacaoAcademicaDatabase repositorioHabilitacaoAcademicaDatabase;

    /**
     * Inicializa o Repositório das Habilitações Acadêmicas dos Freelancers
     */
    private RepositorioHabilitacaoAcademicaDatabase(){    }

    /**
     * Devolve uma instância estática do Repositório de Habilitações Acadêmicas dos Freelancers
     *
     * @return RepositorioHabilitacaoAcademicaDatabase
     */
    public static RepositorioHabilitacaoAcademicaDatabase getInstance(){
        if(repositorioHabilitacaoAcademicaDatabase == null) {
            repositorioHabilitacaoAcademicaDatabase = new RepositorioHabilitacaoAcademicaDatabase();
        }
        return repositorioHabilitacaoAcademicaDatabase;
    }


    @Override
    public boolean save(String grau, String designacaoCurso,
           String nomeInstituicao, double mediaCurso, String emailFreelancer) throws HabilitacaoAcademicaDuplicadaException,
            SQLException{

        Connection connection = DBConnectionHandler.getInstance().openConnection();

        try {
            CallableStatement callableStatement = connection.prepareCall(
                "{CALL createHabilitacao(?, ?, ?, ?, ?) } ");

            if (findByGrauDesigInst(grau, designacaoCurso, nomeInstituicao, emailFreelancer) == null){


                connection.setAutoCommit(false);

                callableStatement.setString(1, grau);
                callableStatement.setString(2, designacaoCurso);
                callableStatement.setString(3, nomeInstituicao);
                callableStatement.setDouble(4, mediaCurso);
                callableStatement.setString(5, emailFreelancer);
                                
                callableStatement.executeQuery();

                connection.commit();
                return true;
            }
        }
        catch (SQLException exception) {
            exception.printStackTrace();
            exception.getSQLState();
            try {
                System.err.print("Transaction is being rolled back");
                connection.rollback();
            }
            catch (SQLException sqlException) {
                sqlException.getErrorCode();
            }
        }

        finally {
            DBConnectionHandler.getInstance().closeAll();
        }


        return false;
    }

    @Override
    public boolean save(HabilitacaoAcademica habilitacaoAcademica, String emailFreelancer) throws HabilitacaoAcademicaDuplicadaException,
            SQLException {

        Connection connection = DBConnectionHandler.getInstance().openConnection();

        try {
            CallableStatement callableStatement = connection.prepareCall(
                "{CALL createHabilitacaoAcademica(?, ?, ?, ?, ?) } ");

            if (findByGrauDesigInst(habilitacaoAcademica.getGrau(),
                habilitacaoAcademica.getDesignacaoCurso(),
                habilitacaoAcademica.getNomeInstituicao(), emailFreelancer) == null){


                connection.setAutoCommit(false);

                callableStatement.setString(1, habilitacaoAcademica.getGrau());
                callableStatement.setString(2, habilitacaoAcademica.getDesignacaoCurso());
                callableStatement.setString(3, habilitacaoAcademica.getNomeInstituicao());
                callableStatement.setDouble(4, habilitacaoAcademica.getMediaCurso());
                callableStatement.setString(5, emailFreelancer);

                callableStatement.executeQuery();

                connection.commit();
                return true;
            }
        }
        catch (SQLException exception) {
            exception.printStackTrace();
            exception.getSQLState();
            try {
                System.err.print("Transaction is being rolled back");
                connection.rollback();
            }
            catch (SQLException sqlException) {
                sqlException.getErrorCode();
            }
        }

        finally {
            DBConnectionHandler.getInstance().closeAll();
        }
        return false;
    }

    @Override
    public HabilitacaoAcademica findByGrauDesigInst(String grau, String designacaoCurso,
                                                String nomeInstituicao, String emailFreelancer) throws SQLException{

        HabilitacaoAcademica habilitacaoAcademica = new HabilitacaoAcademica();

        Connection connection = DBConnectionHandler.getInstance().openConnection();

        try {
            CallableStatement callableStatement = connection.prepareCall(
                    "{CALL findByGrauDesigInst(?, ?, ?, ?)}");


            connection.setAutoCommit(false);

            callableStatement.setString(1, grau);
            callableStatement.setString(2, designacaoCurso);
            callableStatement.setString(3, nomeInstituicao);
            callableStatement.setString(4, emailFreelancer);

            callableStatement.executeQuery();

            return null;

        } catch (SQLException exceptionOrg) {
            exceptionOrg.printStackTrace();
            exceptionOrg.getSQLState();

        }
        finally {
            DBConnectionHandler.getInstance().closeAll();
        }

        return habilitacaoAcademica;

    }
    
    @Override
    public HabilitacaoAcademica findById(int idHabilitacao) throws SQLException{
         
        /*DBConnectionHandler dbConnectionHandler = new DBConnectionHandler(jdbcUrl, username, password);
        Connection connection = dbConnectionHandler.openConnection();

        CallableStatement callableStatementOrg = connection.prepareCall(
                 "{CALL findById(?)}");

        try {
            connection.setAutoCommit(false);

            callableStatementOrg.setInt(6, idHabilitacao);
            callableStatementOrg.executeQuery();

            return null;

        } catch (SQLException exceptionOrg) {
            exceptionOrg.printStackTrace();
            exceptionOrg.getSQLState();


        }

        return new HabilitacaoAcademica();*/
        
        return null;
    }
    
    public ReconhecimentoGP findByEmail(String emailFreelancer) throws SQLException{
         
        /*DBConnectionHandler dbConnectionHandler = new DBConnectionHandler(jdbcUrl, username, password);
        Connection connection = dbConnectionHandler.openConnection();

        CallableStatement callableStatementOrg = connection.prepareCall(
                 "{CALL findByEmail(?)}");

        try {
            connection.setAutoCommit(false);

            callableStatementOrg.setInt(5,  emailFreelancer);
            callableStatementOrg.executeQuery();

            return null;

        } catch (SQLException exceptionOrg) {
            exceptionOrg.printStackTrace();
            exceptionOrg.getSQLState();


        }

        return new HabilitacaoAcademica();*/
        
        return null;
    }
    
    @Override
    public ArrayList<HabilitacaoAcademica> getAll(String email) throws SQLException {
        
        ArrayList<HabilitacaoAcademica> habilitacoesAcademicas = new ArrayList<>();

        Connection connection = DBConnectionHandler.getInstance().openConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM HabilitacaoAcademica " +
                            "INNER JOIN FreelancerHabAcademica " +
                            "ON habilitacaoacademica.idhabilitacaoacademica = FreelancerHabAcademica.idhabilitacaoAcademica " +
                            "INNER JOIN Freelancer " +
                            "ON FreelancerHabAcademica.emailFreelancer LIKE Freelancer.email " +
                            "WHERE freelancer.email LIKE ?"
            );

            preparedStatement.setString(1, email);

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {

                int idHabilitacao = resultSet.getInt(1);
                String grau = resultSet.getString(2);
                String designacaoCurso = resultSet.getString(3);
                String nomeInstituicao = resultSet.getString(4);
                Double mediaCurso = resultSet.getDouble(5);

                habilitacoesAcademicas.add(new HabilitacaoAcademica(idHabilitacao, grau, designacaoCurso, nomeInstituicao, mediaCurso));
            }
        }
        catch (SQLException exception) {
            exception.printStackTrace();
            exception.getSQLState();
            try {
                System.err.print("Transaction is being rolled back");
                connection.rollback();
            }
            catch (SQLException sqlException) {
                sqlException.getErrorCode();
            }
        }
        finally {
            DBConnectionHandler.getInstance().closeAll();
        }
        return habilitacoesAcademicas;
    }
    

}
