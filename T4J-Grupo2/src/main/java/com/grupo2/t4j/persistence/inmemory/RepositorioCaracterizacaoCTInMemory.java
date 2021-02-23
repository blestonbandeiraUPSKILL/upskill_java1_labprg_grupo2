package com.grupo2.t4j.persistence.inmemory;

import com.grupo2.t4j.exception.CaracterizacaoCTDuplicadaException;
import com.grupo2.t4j.model.CaracterizacaoCT;
import com.grupo2.t4j.model.CompetenciaTecnica;
import com.grupo2.t4j.model.GrauProficiencia;
import com.grupo2.t4j.model.Obrigatoriedade;
import com.grupo2.t4j.persistence.RepositorioCaracterizacaoCT;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RepositorioCaracterizacaoCTInMemory implements Serializable, RepositorioCaracterizacaoCT {

    public static RepositorioCaracterizacaoCTInMemory instance;
    List<CaracterizacaoCT> listaCaracterizacaoCTS;

    RepositorioCaracterizacaoCTInMemory() {
        listaCaracterizacaoCTS = new ArrayList<>();
    }

    public static RepositorioCaracterizacaoCTInMemory getInstance() {
        if(instance == null) {
            instance = new RepositorioCaracterizacaoCTInMemory();
        }
        return instance;
    }

    @Override
    public void save(String codigoCategoria, String codigoGP, Obrigatoriedade obrigatoriedade) throws CaracterizacaoCTDuplicadaException {
        CaracterizacaoCT cct = findByCodigo(codigoGP);
        if (cct == null) {
            CaracterizacaoCT caracterizacaoCT = new CaracterizacaoCT(codigoCategoria, codigoGP, obrigatoriedade);
            this.listaCaracterizacaoCTS.add(caracterizacaoCT);
        }

    }

    @Override
    public boolean save(CaracterizacaoCT caracterizacaoCT) {
        /*CaracterizacaoCT cct = findByCodigo(caracterizacaoCT.getCodigoCCT());
        if (cct == null) {
            this.listaCaracterizacaoCTS.add(caracterizacaoCT);
            return true;
        }
        else {
            throw new CaracterizacaoCTDuplicadaException(cct.getCodigoCCT() + "Lista de caracterizações já existente.");
        }*/
        return false;
    }

    @Override
    public List<CaracterizacaoCT> getAll() {
        return new ArrayList<CaracterizacaoCT>(listaCaracterizacaoCTS);
    }

    @Override
    public List<CaracterizacaoCT> findByCompetenciaTecnica(List<String> codigoCompetenciasTecnicas) {
        ArrayList<CaracterizacaoCT> caracterizacaoCTSbyCompetenciaTecnica = new ArrayList<>();

        for (CaracterizacaoCT cct : listaCaracterizacaoCTS) {
            if (cct.getCodigoCompetenciaTecnica().equals(codigoCompetenciasTecnicas)) {
                caracterizacaoCTSbyCompetenciaTecnica.add(cct);
            }
        }
        return caracterizacaoCTSbyCompetenciaTecnica;
    }

    public CaracterizacaoCT findByCodigo(String codigo) {
        /*for (int i = 0; i < this.listaCaracterizacaoCTS.size() ; i++) {
            CaracterizacaoCT cct = this.listaCaracterizacaoCTS.get(i);
            if (cct.getCodigoCCT().equals(codigo)) {
                return cct;
            }
        }*/
        return null;
    }

}
