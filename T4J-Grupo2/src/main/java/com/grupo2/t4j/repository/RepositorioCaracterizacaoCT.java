package com.grupo2.t4j.repository;

import com.grupo2.t4j.exception.CaracterizacaoCTDuplicadaException;
import com.grupo2.t4j.model.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RepositorioCaracterizacaoCT implements Serializable {

    public static RepositorioCaracterizacaoCT instance;
    List<CaracterizacaoCT> listaCaracterizacaoCTS;

    private RepositorioCaracterizacaoCT() {
        listaCaracterizacaoCTS = new ArrayList<>();
    }

    public static RepositorioCaracterizacaoCT getInstance() {
        if(instance == null) {
            instance = new RepositorioCaracterizacaoCT();
        }
        return instance;
    }

    public boolean addCaracterizacaoCT(CaracterizacaoCT caracterizacaoCT) throws CaracterizacaoCTDuplicadaException {
        if(caracterizacaoCT != null) {
            this.listaCaracterizacaoCTS.add(caracterizacaoCT);
            return true;
        }
        else {
            throw new CaracterizacaoCTDuplicadaException("Esta lista é duplicada!");
        }
    }

    public CaracterizacaoCT novaCaracterizacaoCT (GrauProficiencia gp,
                                                  Obrigatoriedade obrigatoriedade,
                                                  CompetenciaTecnica competenciaTecnica) {
        return new CaracterizacaoCT(gp, obrigatoriedade, competenciaTecnica);
    }

    public List<CaracterizacaoCT> getListaCaracterizacaoCTS() {
        return new ArrayList<CaracterizacaoCT>(listaCaracterizacaoCTS);
    }

    public List<CaracterizacaoCT> getCaracterizacaoCTSbyCompetenciaTecnica(List<CompetenciaTecnica> competenciasTecnicas) {
        ArrayList<CaracterizacaoCT> caracterizacaoCTSbyCompetenciaTecnica = new ArrayList<>();

        for (CaracterizacaoCT cct : listaCaracterizacaoCTS) {
            if (cct.getCompetenciaTecnica().equals(competenciasTecnicas)) {
                caracterizacaoCTSbyCompetenciaTecnica.add(cct);
            }
        }
        return caracterizacaoCTSbyCompetenciaTecnica;

    }

    /*public List<CaracterizacaoCT> getCaracterizacaoCTSByCategoria(Categoria categoria) {
        ArrayList<CaracterizacaoCT> caracterizacaoCTSbyCategoria = new ArrayList<>();
        
        for (CaracterizacaoCT cct : listaCaracterizacaoCTS) {
            if (cct.equals(competenciasTecnicas)) {
                caracterizacaoCTSbyCompetenciaTecnica.add(cct);
            }
        }
        return caracterizacaoCTSbyCompetenciaTecnica;
    }*/
}
