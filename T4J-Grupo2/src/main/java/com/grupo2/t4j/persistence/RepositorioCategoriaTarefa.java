package com.grupo2.t4j.persistence;


import com.grupo2.t4j.model.Categoria;
import com.grupo2.t4j.model.Obrigatoriedade;

import java.sql.SQLException;
import java.util.ArrayList;

public interface RepositorioCategoriaTarefa {


    boolean save(String codigoCategoria, String descBreve,
           String descDetalhada, String codigoAreaActividade, int idGrauProficiencia,
           Obrigatoriedade obrigatoriedade) throws SQLException;

    boolean save(Categoria categoria) throws SQLException;

    Categoria findByCodigo(String codigoCategoria) throws SQLException;


    ArrayList<Categoria> findByAreaActividade(String codigoAreaActividade)throws SQLException;

    ArrayList<Categoria> getAll() throws SQLException;


}
