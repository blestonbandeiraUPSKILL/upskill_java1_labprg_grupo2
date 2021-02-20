package com.grupo2.t4j.persistence;


import com.grupo2.t4j.model.*;

import java.util.ArrayList;
import java.util.List;

public interface RepositorioCategoriaTarefa {


     void save(String codigoCategoria, String descBreve, String descDetalhada,
               String codigoAreaActividade, List<CaracterizacaoCT> caracterizacaoCTS);

    boolean save(Categoria categoria);

     Categoria findByCodigo(String codigoCategoria);


    ArrayList<Categoria> findByAreaActividade(String codigoAreaActividade);

    ArrayList<Categoria> getAll();


}
