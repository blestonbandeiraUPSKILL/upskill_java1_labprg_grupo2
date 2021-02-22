package com.grupo2.t4j.persistence.database;

import com.grupo2.t4j.model.AreaActividade;
import com.grupo2.t4j.model.Categoria;
import com.grupo2.t4j.model.Tarefa;
import com.grupo2.t4j.persistence.RepositorioTarefa;
import com.grupo2.t4j.utils.DBConnectionHandler;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

public class RepositorioTarefaDatabase implements RepositorioTarefa {

    /**
     * Define uma instância estática do Repositório em que estão
     * registadas todas as Tarefas
     */
    private static RepositorioTarefaDatabase repositorioTarefaDatabase;
    
    String jdbcUrl = "jdbc:oracle:thin:@vsrvbd1.dei.isep.ipp.pt:1521/pdborcl";
    String username = "UPSKILL_BD_TURMA1_01";
    String password = "qwerty";

    /**
     * Inicializa o Repositório de Tarefas
     */
    RepositorioTarefaDatabase(){    }

    /**
     * Devolve uma instância estática do Repositório de Tarefas
     *
     * @return RepositorioTarefaDatabase
     */
    public static RepositorioTarefaDatabase getInstance(){
        if(repositorioTarefaDatabase == null) {
            repositorioTarefaDatabase = new RepositorioTarefaDatabase();
        }
        return repositorioTarefaDatabase;
    }

    @Override
    public void save(String codigoAreaActividade, String codigoCategoriaTarefa, 
            String referencia, String designacao, String descInformal, 
            String descTecnica, int duracao, double custo, String nifOrganizacao,
            String emailColaborador) {

    }

    @Override
    public boolean save(Tarefa tarefa) {

        return false;
    }

    @Override
    public Tarefa findByReferenciaENIF(String referencia, String nif) throws SQLException {
        
        DBConnectionHandler dbConnectionHandler = new DBConnectionHandler(jdbcUrl, username, password);
        Connection connection = dbConnectionHandler.openConnection();

        CallableStatement callableStatement = connection.prepareCall(
                "{ CALL findByRefenciaENif(?, ?) }"
        );

        try {
            connection.setAutoCommit(false);

            callableStatement.setString(1, referencia);
            callableStatement.setString(2, nif );

            callableStatement.executeUpdate();
            return null;

        }
        catch (SQLException exception) {
            exception.printStackTrace();
            exception.getSQLState();


        }
        return new Tarefa();
    }

    @Override
    public List<Tarefa> findByCategoria(String codigoCategoria) {
        return null;
    }

    @Override
    public ArrayList<Tarefa> getAll() {
        return null;
    }
}
