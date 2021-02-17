package com.grupo2.t4j.persistence.inmemory;

import com.grupo2.t4j.exception.CaracterizacaoCTDuplicadaException;
import com.grupo2.t4j.model.CaracterizacaoCT;
import com.grupo2.t4j.model.CompetenciaTecnica;
import com.grupo2.t4j.model.GrauProficiencia;
import com.grupo2.t4j.model.Obrigatoriedade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RepositorioCaracterizacaoCTInMemory implements Serializable {

    public static RepositorioCaracterizacaoCTInMemory instance;
    List<CaracterizacaoCT> listaCaracterizacaoCTS;

    private RepositorioCaracterizacaoCTInMemory() {
        listaCaracterizacaoCTS = new ArrayList<>();
    }

    public static RepositorioCaracterizacaoCTInMemory getInstance() {
        if(instance == null) {
            instance = new RepositorioCaracterizacaoCTInMemory();
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
