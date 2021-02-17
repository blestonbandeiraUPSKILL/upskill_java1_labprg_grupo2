package com.grupo2.t4j.persistence;

import com.grupo2.t4j.exception.CaracterizacaoCTDuplicadaException;
import com.grupo2.t4j.model.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public interface RepositorioCaracterizacaoCT {


    void save(GrauProficiencia gp, Obrigatoriedade obrigatoriedade,
              CompetenciaTecnica competenciaTecnica) throws CaracterizacaoCTDuplicadaException;

    List<CaracterizacaoCT> getAll();

    List<CaracterizacaoCT> findByCompetenciaTecnica(List<CompetenciaTecnica> competenciasTecnicas);

}
