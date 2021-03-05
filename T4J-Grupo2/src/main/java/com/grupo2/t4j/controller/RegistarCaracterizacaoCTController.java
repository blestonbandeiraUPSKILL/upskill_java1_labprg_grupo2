package com.grupo2.t4j.controller;

import com.grupo2.t4j.domain.CaracterizacaoCT;
import com.grupo2.t4j.domain.Obrigatoriedade;
import com.grupo2.t4j.persistence.FabricaRepositorios;
import com.grupo2.t4j.persistence.RepositorioCaracterizacaoCT;
import com.grupo2.t4j.persistence.database.FabricaRepositoriosDatabase;

import java.sql.SQLException;
import java.util.List;

public class RegistarCaracterizacaoCTController {

    //private FabricaRepositorios fabricaRepositorios = new FabricaRepositoriosInMemory();
    private FabricaRepositorios fabricaRepositorios = new FabricaRepositoriosDatabase();
    private RepositorioCaracterizacaoCT repositorioCaracterizacaoCT = fabricaRepositorios.getRepositorioCaracterizacaoCT();

    public boolean save(CaracterizacaoCT caracterizacaoCT) throws SQLException {
        return repositorioCaracterizacaoCT.save(caracterizacaoCT);
    }

    public List<CaracterizacaoCT> getAll() {
        return repositorioCaracterizacaoCT.getAll();
    }

       /**
     * Caracterizar a competência técnica boolean
     *
     * @param codigoCategoria as código da categoria
     * @param codigoGP as código do grau de proficiência
     * @param obrigatoriedade as requisito da competência técnica
     * @return boolean
     */
    public boolean registarCaracterizacaoCTS(String codigoCategoria, int codigoGP, Obrigatoriedade obrigatoriedade) throws SQLException {

        CaracterizacaoCT caracterizacaoCT = new CaracterizacaoCT(codigoCategoria, codigoGP, obrigatoriedade);

        return repositorioCaracterizacaoCT.save(caracterizacaoCT);
    }

    public CaracterizacaoCT findByCodigo(int codigoCCT) {
        return repositorioCaracterizacaoCT.findByCodigo(codigoCCT);
    }
    
    public List<CaracterizacaoCT> findByCategoria(String codigoCategoria) throws SQLException{
        return repositorioCaracterizacaoCT.findByCategoria(codigoCategoria);
    }
    
    public CaracterizacaoCT findByCategoriaEGrau (String codigoCategoria, 
            int codigoGP) throws SQLException {
        return repositorioCaracterizacaoCT.findByCategoriaEGrau(codigoCategoria,codigoGP);
    }

    public List<CaracterizacaoCT> getAllByCategoria(String codigoCategoria) throws SQLException {
        return repositorioCaracterizacaoCT.getAllByCategoria(codigoCategoria);
    }
}
