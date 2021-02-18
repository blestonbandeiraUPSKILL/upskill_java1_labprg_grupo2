package com.grupo2.t4j.controller;

import com.grupo2.t4j.model.*;
import com.grupo2.t4j.persistence.inmemory.RepositorioCaracterizacaoCTInMemory;

import java.util.List;

public class RegistarCaracterizacaoCTController {

    private RepositorioCaracterizacaoCTInMemory repositorioCaracterizacaoCTInMemory;

    public boolean registarCaracterizacaoCT(CaracterizacaoCT caracterizacaoCT) {
        return RepositorioCaracterizacaoCTInMemory.getInstance().addCaracterizacaoCT(caracterizacaoCT);
    }

    public List<CaracterizacaoCT> getCaracterizacaoCTS() {
        return RepositorioCaracterizacaoCTInMemory.getInstance().getListaCaracterizacaoCTS();
    }

    public CaracterizacaoCT novaCaracterizacaoCTS(GrauProficiencia gp, Obrigatoriedade obrigatoriedade,
                                                  CompetenciaTecnica competenciaTecnica) {
        return RepositorioCaracterizacaoCTInMemory.getInstance().novaCaracterizacaoCT(gp, obrigatoriedade, competenciaTecnica);
    }

    public List<CaracterizacaoCT> getCaracterizacaoCTSByCompetenciaTecnica(List<CompetenciaTecnica> competenciasTecnicasByAreaActividade) {
        return RepositorioCaracterizacaoCTInMemory.getInstance().getCaracterizacaoCTSbyCompetenciaTecnica(competenciasTecnicasByAreaActividade);
    }

    /*public List<CaracterizacaoCT> getCaracterizacaoCTSByCategoria(Categoria categoria) {
        return RepositorioCaracterizacaoCT.getInstance().getCaracterizacaoCTSByCategoria(categoria);
    }*/
}
