package com.grupo2.t4j.controller;

import com.grupo2.t4j.domain.CaracterizacaoCT;
import com.grupo2.t4j.domain.Obrigatoriedade;
import com.grupo2.t4j.dto.CaracterizacaoCTDTO;
import com.grupo2.t4j.dto.ObrigatoriedadeDTO;
import com.grupo2.t4j.persistence.FabricaRepositorios;
import com.grupo2.t4j.persistence.RepositorioCaracterizacaoCT;
import com.grupo2.t4j.persistence.database.FabricaRepositoriosDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
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
    public List<CaracterizacaoCTDTO> getAll() {
        List<CaracterizacaoCT> caracterizacaoCT = repositorioCaracterizacaoCT.getAll();
        List<CaracterizacaoCTDTO> caracterizacaoCTDTO = new ArrayList<>();

        for(CaracterizacaoCT cct : caracterizacaoCT) {
            caracterizacaoCTDTO.add((CaracterizacaoCTDTO) cct.toDTO());
        }
        return caracterizacaoCTDTO;
    }

       /**
     * Caracterizar a competência técnica boolean
     *
     * @param codigoCategoria as código da categoria
     * @param codigoGP as código do grau de proficiência
     * @param obrigatoriedade as requisito da competência técnica
     * @return boolean
     */
    public boolean registarCaracterizacaoCTS(String codigoCategoria, int codigoGP, ObrigatoriedadeDTO obrigatoriedade) throws SQLException {

        Obrigatoriedade o = Obrigatoriedade.valueOf(obrigatoriedade.toString());

        CaracterizacaoCT caracterizacaoCT = new CaracterizacaoCT(codigoCategoria, codigoGP, o);

        return repositorioCaracterizacaoCT.save(caracterizacaoCT);
    }

    /**
     * Devolve uma caracterizacaoCT a partir do seu codigo
     * @param codigoCCT
     * @return 
     */
    public CaracterizacaoCTDTO findByCodigo(int codigoCCT) {
        CaracterizacaoCT caracterizacaoCT = repositorioCaracterizacaoCT.findByCodigo(codigoCCT);
        return (CaracterizacaoCTDTO) caracterizacaoCT.toDTO();
    }

    /**
     * Devolve uma lista de Caracterizacoes de uma categoria
     * @param codigoCategoria
     * @return
     * @throws SQLException 
     */
    public List<CaracterizacaoCTDTO> getAllByCategoria(String codigoCategoria) throws SQLException {
        List<CaracterizacaoCT> caracterizacaoCT = repositorioCaracterizacaoCT.getAllByCategoria(codigoCategoria);
        List<CaracterizacaoCTDTO> caracterizacaoCTDTO = new ArrayList<>();

        for (CaracterizacaoCT cct : caracterizacaoCT) {
            caracterizacaoCTDTO.add((CaracterizacaoCTDTO) cct.toDTO());
        }
        return caracterizacaoCTDTO;
    }
}
