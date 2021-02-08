package com.grupo2.t4j.controller;

import com.grupo2.t4j.model.*;
import com.grupo2.t4j.repository.RepositorioCaracterizacaoCT;

import java.util.List;

public class RegistarCaracterizacaoCTController {

    private RepositorioCaracterizacaoCT repositorioCaracterizacaoCT;

    public boolean registarCaracterizacaoCT(CaracterizacaoCT caracterizacaoCT) {
        return RepositorioCaracterizacaoCT.getInstance().addCaracterizacaoCT(caracterizacaoCT);
    }

    public List<CaracterizacaoCT> getCaracterizacaoCTS() {
        return RepositorioCaracterizacaoCT.getInstance().getListaCaracterizacaoCTS();
    }

    public CaracterizacaoCT novaCaracterizacaoCTS(GrauProficiencia gp, Obrigatoriedade obrigatoriedade,
                                                  CompetenciaTecnica competenciaTecnica) {
        return RepositorioCaracterizacaoCT.getInstance().novaCaracterizacaoCT(gp, obrigatoriedade, competenciaTecnica);
    }

    public List<CaracterizacaoCT> getCaracterizacaoCTSByCompetenciaTecnica(List<CompetenciaTecnica> competenciasTecnicasByAreaActividade) {
        return RepositorioCaracterizacaoCT.getInstance().getCaracterizacaoCTSbyCompetenciaTecnica(competenciasTecnicasByAreaActividade);
    }
}
