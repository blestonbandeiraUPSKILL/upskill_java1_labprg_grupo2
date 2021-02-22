package com.grupo2.t4j.persistence;


import com.grupo2.t4j.model.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface RepositorioCategoriaTarefa {


     void save(String codigoCategoria, String descBreve, String descDetalhada,
               String codigoAreaActividade, List<CaracterizacaoCT> caracterizacaoCTS);

    boolean save(Categoria categoria) throws SQLException;

     Categoria findByCodigo(String codigoCategoria) throws SQLException;


    ArrayList<Categoria> findByAreaActividade(String codigoAreaActividade)throws SQLException;

    ArrayList<Categoria> getAll() throws SQLException;


}
