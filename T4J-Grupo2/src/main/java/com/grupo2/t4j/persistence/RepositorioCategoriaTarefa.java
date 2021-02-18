package com.grupo2.t4j.persistence;


import com.grupo2.t4j.model.*;

import java.util.ArrayList;
import java.util.List;

public interface RepositorioCategoriaTarefa {


     void save(String descBreve, String descDetalhada,
               AreaActividade areaActividade, List<CaracterizacaoCT> caracterizacaoCTS);

    boolean save(Categoria categoria);

     Categoria findByCodigo(String codigoCategoria);


    ArrayList<Categoria> findByAreaActividade(String codigoAreaActividade);

    ArrayList<Categoria> getAll();


}
