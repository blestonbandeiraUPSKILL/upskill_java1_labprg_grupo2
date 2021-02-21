package com.grupo2.t4j.persistence;

import com.grupo2.t4j.exception.CaracterizacaoCTDuplicadaException;
import com.grupo2.t4j.model.*;

import java.sql.SQLException;
import java.util.List;

public interface RepositorioCaracterizacaoCT {


    void save(String codigoCCT, String codigoGP, Obrigatoriedade obrigatoriedade, String codigoCompetenciaTecnica) throws CaracterizacaoCTDuplicadaException;

    boolean save(CaracterizacaoCT caracterizacaoCT) throws SQLException;

    List<CaracterizacaoCT> getAll();

    List<CaracterizacaoCT> findByCompetenciaTecnica(List<String> codigoCompetenciasTecnicas);

    CaracterizacaoCT findByCodigo(String codigoCCT);
}
