package com.grupo2.t4j.persistence;

import com.grupo2.t4j.exception.CaracterizacaoCTDuplicadaException;
import com.grupo2.t4j.model.*;

import java.sql.SQLException;
import java.util.List;

public interface RepositorioCaracterizacaoCT {


    void save(String codigoCategoria, String codigoGP, Obrigatoriedade obrigatoriedade) throws CaracterizacaoCTDuplicadaException;

    boolean save(CaracterizacaoCT caracterizacaoCT) throws SQLException;

    List<CaracterizacaoCT> getAll();

    List<CaracterizacaoCT> findByCategoria(String codigoCategoria) throws SQLException;

    CaracterizacaoCT findByCodigo(String codigoCCT);
    
    CaracterizacaoCT findByCategoriaEGrau (String codigoCategoria, 
            String codigoGP) throws SQLException; 
}
