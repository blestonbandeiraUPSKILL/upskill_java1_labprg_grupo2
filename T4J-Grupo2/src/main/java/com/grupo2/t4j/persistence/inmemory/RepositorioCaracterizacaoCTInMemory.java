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
    public void save(GrauProficiencia gp, Obrigatoriedade obrigatoriedade, CompetenciaTecnica competenciaTecnica) throws CaracterizacaoCTDuplicadaException {
       //falta código de adicionar isto - n sei qual é a abordagem que temos neste momento
    }

    @Override
    public List<CaracterizacaoCT> getAll() {
        return new ArrayList<CaracterizacaoCT>(listaCaracterizacaoCTS);
    }

    @Override
    public List<CaracterizacaoCT> findByCompetenciaTecnica(List<CompetenciaTecnica> competenciasTecnicas) {
        ArrayList<CaracterizacaoCT> caracterizacaoCTSbyCompetenciaTecnica = new ArrayList<>();

        for (CaracterizacaoCT cct : listaCaracterizacaoCTS) {
            if (cct.getCompetenciaTecnica().equals(competenciasTecnicas)) {
                caracterizacaoCTSbyCompetenciaTecnica.add(cct);
            }
        }
        return caracterizacaoCTSbyCompetenciaTecnica;
    }

}
