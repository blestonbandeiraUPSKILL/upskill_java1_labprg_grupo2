package com.grupo2.t4j.persistence;


import com.grupo2.t4j.exception.CategoriaDuplicadaException;
import com.grupo2.t4j.model.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public interface RepositorioCategoriaTarefa {


     void save(String descBreve, String descDetalhada,
               AreaActividade areaActividade, List<CaracterizacaoCT> caracterizacaoCTS);



     Categoria findById(String id);


    ArrayList<Categoria> findByAreaActividade(AreaActividade at);

    ArrayList<Categoria> getAll();


}
