package com.grupo2.t4j.persistence.database;

import com.grupo2.t4j.domain.*;
import com.grupo2.t4j.exception.FreelancerDuplicadoException;
import com.grupo2.t4j.persistence.RepositorioFreelancer;
import com.grupo2.t4j.utils.DBConnectionHandler;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RepositorioFreelancerDatabase implements RepositorioFreelancer{
    
    /**
     * Define uma instância estática do Repositório em que estão
     * registados todos os Freelancers
     */
    private static RepositorioFreelancerDatabase repositorioFreelancerDatabase;
    
    /**
     * Inicializa o Repositório de Freelancers
     */
    private RepositorioFreelancerDatabase(){    }

    /**
     * Devolve uma instância estática do Repositório dos Freelancers
     *
     * @return RepositorioFreelancerDatabase
     */
    public static RepositorioFreelancerDatabase getInstance(){
        if(repositorioFreelancerDatabase == null) {
            repositorioFreelancerDatabase = new RepositorioFreelancerDatabase();
        }
        return repositorioFreelancerDatabase;
    }


    /**
     * Regista um Freelancer
     * @param emailFree
     * @param nome
     * @param nif
     * @param telefone
     * @param password
     * @param arruamento
     * @param numeroPorta
     * @param localidade
     * @param codPostal
     * @return
     * @throws FreelancerDuplicadoException
     * @throws SQLException 
     */
    @Override
    public boolean save(String emailFree, String nome, String nif, String telefone, String password, String arruamento,
                        String numeroPorta, String localidade, String codPostal) throws FreelancerDuplicadoException,
            SQLException{

        Connection connection = DBConnectionHandler.getInstance().openConnection();
        try {
            CallableStatement callableStatement = connection.prepareCall(
                "{CALL createFreelancer(?, ?, ?, ?, ?, ?, ?) } ");

            if (findByNif(nif) == null){

                connection.setAutoCommit(false);

                callableStatement.setString(1, emailFree);
                callableStatement.setString(2, telefone);
                callableStatement.setString(3, nif);
                callableStatement.setString(4, arruamento);
                callableStatement.setString(5, numeroPorta);
                callableStatement.setString(6, localidade);
                callableStatement.setString(7, codPostal);
                
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
    public boolean save(Freelancer freelancer) throws FreelancerDuplicadoException,
            SQLException {
        return false;
    }

    /**
     * Devolve um freelancer a partir do seu nif
     * @param nif
     * @return
     * @throws SQLException 
     */
    @Override
    public Freelancer findByNif(String nif) throws SQLException{

        Connection connection = DBConnectionHandler.getInstance().openConnection();

        try {
            CallableStatement callableStatementOrg = connection.prepareCall(
                    "{CALL findFreelancerByNif(?)}");

            connection.setAutoCommit(false);

            callableStatementOrg.setString(1, nif);
            callableStatementOrg.executeQuery();

            return null;

        } catch (SQLException exceptionOrg) {
            exceptionOrg.printStackTrace();
            exceptionOrg.getSQLState();


        }
        finally {
            DBConnectionHandler.getInstance().closeAll();
        }

        return new Freelancer();
    }

    /**
     * Devolve a password de um freelancer
     * @param email
     * @return
     * @throws SQLException 
     */
    @Override
    public Password findPassword(String email) throws SQLException {

        Connection connection = DBConnectionHandler.getInstance().openConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT utilizador.password FROM Utilizador " +
                        "INNER JOIN Freelancer ON freelancer.email LIKE ? "
            );

            connection.setAutoCommit(false);

            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String password = resultSet.getString(1);
                return new Password(password);
            }

        }

        catch (SQLException exception) {
            exception.printStackTrace();
            exception.getSQLState();
        }
        finally {
            DBConnectionHandler.getInstance().closeAll();
        }

        return null;
    }

    /**
     * Devolve um Freelancer a partir do seu email
     * @param emailFree
     * @return
     * @throws SQLException 
     */
    @Override
    public Freelancer findByEmail(String emailFree) throws SQLException {

        Freelancer freelancer = new Freelancer();

        Connection connection = DBConnectionHandler.getInstance().openConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT Freelancer.telefone, " +
                            "Freelancer.nif, " +
                            "Utilizador.nome, " +
                            "Freelancer.idEnderecoPostal " +
                            "FROM Freelancer INNER JOIN Utilizador " +
                            "ON freelancer.email LIKE utilizador.email " +
                            "WHERE freelancer.email LIKE ?"
            );

            preparedStatement.setString(1, emailFree);

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                freelancer.setEmail(new Email(emailFree));
                freelancer.setTelefone(resultSet.getString(1));
                freelancer.setNif(resultSet.getString(2));
                freelancer.setNome(resultSet.getString(3));
                freelancer.setIdEnderecoPostal(resultSet.getInt(4));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
            exception.getSQLState();

        }
        finally {
            DBConnectionHandler.getInstance().closeAll();
        }

        return freelancer;

    }

    /**
     * Devolve uma lista de todos os Freelancers
     * @return
     * @throws SQLException 
     */
    @Override
    public ArrayList<Freelancer> getAll() throws SQLException {
        
        ArrayList<Freelancer> freelancers = new ArrayList<>();

        Connection connection = DBConnectionHandler.getInstance().openConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT Freelancer.email, Freelancer.telefone, Freelancer.nif, Utilizador.nome, Freelancer.idEnderecoPostal " +
                            "FROM Freelancer INNER JOIN Utilizador " +
                            "ON freelancer.email LIKE utilizador.email"
            );

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                String email = resultSet.getString(1);
                String telefone = resultSet.getString(2);
                String nif = resultSet.getString(3);
                String nome = resultSet.getString(4);
                int idEnderecoPostal = resultSet.getInt(5);
                freelancers.add(new Freelancer(email, nome, telefone, nif, idEnderecoPostal));
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

        return freelancers;
    }

    /**
     * Devolve todos os reconhecimentos de um freelancer
     * @param emailFreelancer
     * @return
     * @throws SQLException 
     */
    @Override
    public List<ReconhecimentoGP> getAllReconhecimentoGP(String emailFreelancer) throws SQLException {
        ArrayList<ReconhecimentoGP> reconhecimentosGP = new ArrayList<>();

        Connection connection = DBConnectionHandler.getInstance().openConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM ReconhecimentoGP " +
                            "INNER JOIN Freelancer " +
                            "ON ReconhecimentoGP.emailFreelancer LIKE Freelancer.email " +
                            "INNER JOIN GrauProficiencia " +
                            "ON ReconhecimentoGP.idGrauProficiencia = GrauProficiencia.idGrauProficiencia " +
                            "WHERE Freelancer.email LIKE ?"
            );

            preparedStatement.setString(1, emailFreelancer);

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                int idGrauProficiencia = resultSet.getInt(1);
                String dataReconhecimento = resultSet.getDate(3).toString();

                reconhecimentosGP.add(new ReconhecimentoGP(idGrauProficiencia, emailFreelancer, dataReconhecimento));
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

        return reconhecimentosGP;

    }

    /**
     * Devolve todas as habilitacoes academicas de um freelancer
     * @param emailFreelancer
     * @return
     * @throws SQLException 
     */
    @Override
    public List<HabilitacaoAcademica> getAllHabsAcademicas(String emailFreelancer) throws SQLException {
        ArrayList<HabilitacaoAcademica> habsAcademicas = new ArrayList<>();

        Connection connection = DBConnectionHandler.getInstance().openConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM HabilitacaoAcademica " +
                            "INNER JOIN FreelancerHabAcademica " +
                            "ON HabilitacaoAcademica.idHabilitacaoAcademica = FreelancerHabAcademica.idHabilitacaoAcademica " +
                            "INNER JOIN Freelancer " +
                            "ON FreelancerHabAcademica.emailFreelancer LIKE Freelancer.email " +
                            "WHERE Freelancer.email LIKE ?"
            );

            preparedStatement.setString(1, emailFreelancer);

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
               int idHabilitacaoAcademica = resultSet.getInt(1);
               String grau = resultSet.getString(2);
               String designacaoCurso = resultSet.getString(3);
               String nomeInstituicao = resultSet.getString(4);
               double mediaCurso = resultSet.getDouble(5);

                habsAcademicas.add(new HabilitacaoAcademica(idHabilitacaoAcademica, grau, designacaoCurso, nomeInstituicao, mediaCurso));
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

        return habsAcademicas;
    }

    /**
     * Devolve o endereco postal de um freelancer a partir do seu email
     * @param emailFreelancer
     * @return
     * @throws SQLException 
     */
    @Override
    public EnderecoPostal getEnderecoPostal(String emailFreelancer) throws SQLException {
        EnderecoPostal enderecoPostal = new EnderecoPostal();

        Connection connection = DBConnectionHandler.getInstance().openConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * From EnderecoPostal " +
                            "INNER JOIN Freelancer " +
                            "ON EnderecoPostal.idEnderecoPostal LIKE Freelancer.idEnderecoPostal " +
                            "WHERE Freelancer.email LIKE ?"
            );

            preparedStatement.setString(1, emailFreelancer);

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                enderecoPostal.setArruamento(resultSet.getString(2));
                enderecoPostal.setPorta(resultSet.getString(3));
                enderecoPostal.setLocalidade(resultSet.getString(4));
                enderecoPostal.setCodigoPostal(resultSet.getString(5));
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

        return enderecoPostal;
    }


}
