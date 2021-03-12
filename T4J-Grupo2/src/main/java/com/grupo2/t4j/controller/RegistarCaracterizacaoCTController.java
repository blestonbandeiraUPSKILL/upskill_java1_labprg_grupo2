package com.grupo2.t4j.controller;

import com.grupo2.t4j.domain.CaracterizacaoCT;
import com.grupo2.t4j.domain.Obrigatoriedade;
import com.grupo2.t4j.persistence.FabricaRepositorios;
import com.grupo2.t4j.persistence.RepositorioCaracterizacaoCT;
import com.grupo2.t4j.persistence.database.FabricaRepositoriosDatabase;

import java.sql.SQLException;
import java.util.List;

public class RegistarCaracterizacaoCTController {

    private FabricaRepositorios fabricaRepositorios = new FabricaRepositoriosDatabase();
    private RepositorioCaracterizacaoCT repositorioCaracterizacaoCT = fabricaRepositorios.getRepositorioCaracterizacaoCT();

    /**
     * Regista uma nova caracterizacaoCT
     * @param caracterizacaoCT
     * @return
     * @throws SQLException 
     */
    public boolean save(CaracterizacaoCT caracterizacaoCT) throws SQLException {
        return repositorioCaracterizacaoCT.save(caracterizacaoCT);
    }

    /**
     * Devolve uma lista de todas as caracterizacoesCT
     * @return 
     */
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

    /**
     * Devolve uma caracterizacaoCT a partir do seu codigo
     * @param codigoCCT
     * @return 
     */
    public CaracterizacaoCT findByCodigo(int codigoCCT) {
        return repositorioCaracterizacaoCT.findByCodigo(codigoCCT);
    }

    /**
     * Devolve uma lista de Caracterizacoes de uma categoria
     * @param codigoCategoria
     * @return
     * @throws SQLException 
     */
    public List<CaracterizacaoCT> getAllByCategoria(String codigoCategoria) throws SQLException {
        return repositorioCaracterizacaoCT.getAllByCategoria(codigoCategoria);
    }
}
