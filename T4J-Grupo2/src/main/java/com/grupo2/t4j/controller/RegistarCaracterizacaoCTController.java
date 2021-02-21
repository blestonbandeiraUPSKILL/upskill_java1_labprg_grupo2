package com.grupo2.t4j.controller;

import com.grupo2.t4j.model.*;
import com.grupo2.t4j.persistence.FabricaRepositorios;
import com.grupo2.t4j.persistence.RepositorioCaracterizacaoCT;
import com.grupo2.t4j.persistence.database.FabricaRepositoriosDatabase;
import com.grupo2.t4j.persistence.inmemory.FabricaRepositoriosInMemory;
import com.grupo2.t4j.persistence.inmemory.RepositorioCaracterizacaoCTInMemory;

import java.sql.SQLException;
import java.util.List;

public class RegistarCaracterizacaoCTController {

    private FabricaRepositorios fabricaRepositorios = new FabricaRepositoriosInMemory();
    //private FabricaRepositorios fabricaRepositorios = new FabricaRepositoriosDatabase();
    private RepositorioCaracterizacaoCT repositorioCaracterizacaoCT = fabricaRepositorios.getRepositorioCaracterizacaoCT();

    public boolean registarCaracterizacaoCT(CaracterizacaoCT caracterizacaoCT) throws SQLException {
        return repositorioCaracterizacaoCT.save(caracterizacaoCT);
    }

    public List<CaracterizacaoCT> getAll() {
        return repositorioCaracterizacaoCT.getAll();
    }

    public boolean registarCaracterizacaoCTS(String codigoCCT, String codigoGP, Obrigatoriedade obrigatoriedade,
                                                  String codigoCompetenciaTecnica) throws SQLException {

        CaracterizacaoCT caracterizacaoCT = new CaracterizacaoCT(codigoCCT, codigoGP, obrigatoriedade, codigoCompetenciaTecnica);

        return repositorioCaracterizacaoCT.save(caracterizacaoCT);
    }

    public CaracterizacaoCT findByCodigo(String codigoCCT) {
        return repositorioCaracterizacaoCT.findByCodigo(codigoCCT);
    }
}
