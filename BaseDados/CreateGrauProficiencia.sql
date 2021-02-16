CREATE OR REPLACE PROCEDURE createGrauProficiencia(
    p_valor grauproficiencia.valor%type,
    p_designacao grauproficiencia.designacao%type,
    p_codigoCompetenciaTecnica competenciatecnica.codigocompetenciatecnica%type
    )
    
IS
    v_count int;
    ex_CompetenciaTecnica exception;

BEGIN
    
    SELECT count(*) INTO v_count
    FROM CompetenciaTecnica
    WHERE codigoCompetenciaTecnica = p_codigoCompetenciaTecnica;

    IF v_count = 0
    THEN
        RAISE ex_CompetenciaTecnica;
    END IF;
    
    INSERT INTO GrauProficiencia
        (valor, designacao, codigoCompetenciaTecnica)
    VALUES
        (p_valor, p_designacao, p_codigoCompetenciaTecnica);
    
    EXCEPTION WHEN
        ex_CompetenciaTecnica
    THEN 
        RAISE_APPLICATION_ERROR(-20001, 'Competência Técnica Inexistente');

END;
/