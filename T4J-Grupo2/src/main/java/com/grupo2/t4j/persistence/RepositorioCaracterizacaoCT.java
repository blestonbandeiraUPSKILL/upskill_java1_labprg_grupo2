package com.grupo2.t4j.persistence;

import com.grupo2.t4j.exception.CaracterizacaoCTDuplicadaException;
import com.grupo2.t4j.domain.CaracterizacaoCT;
import com.grupo2.t4j.domain.Obrigatoriedade;

import java.sql.SQLException;
import java.util.List;

public interface RepositorioCaracterizacaoCT {


    void save(String codigoCategoria, int codigoGP, Obrigatoriedade obrigatoriedade) throws CaracterizacaoCTDuplicadaException;

    boolean save(CaracterizacaoCT caracterizacaoCT) throws SQLException;

    List<CaracterizacaoCT> getAll();

    CaracterizacaoCT findByCodigo(int codigoCCT);
    
    CaracterizacaoCT findByCategoriaEGrau (String codigoCategoria,
                                           int codigoGP) throws SQLException;

    List<CaracterizacaoCT> getAllByCategoria(String codigoCategoria) throws SQLException;
}
