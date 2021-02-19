package com.grupo2.t4j.persistence;

import com.grupo2.t4j.exception.CaracterizacaoCTDuplicadaException;
import com.grupo2.t4j.model.*;

import java.util.List;

public interface RepositorioCaracterizacaoCT {


    void save(String codigoCCT, String codigoGP, Obrigatoriedade obrigatoriedade, String codigoCompetenciaTecnica) throws CaracterizacaoCTDuplicadaException;

    boolean save(CaracterizacaoCT caracterizacaoCT);

    List<CaracterizacaoCT> getAll();

    List<CaracterizacaoCT> findByCompetenciaTecnica(List<String> codigoCompetenciasTecnicas);

}
