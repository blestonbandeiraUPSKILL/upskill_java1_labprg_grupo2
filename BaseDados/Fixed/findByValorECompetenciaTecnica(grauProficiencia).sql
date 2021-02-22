CREATE OR REPLACE PROCEDURE findByValorECompetenciaTecnica(
    p_grau grauproficiencia.grau%type,
    p_codigoCompetenciaTecnica grauproficiencia.codigoCompetenciaTecnica%type
    )

IS

    v_count_grau int;
    v_count_codigo int;
    ex_GrauProficiencia exception;

BEGIN
    SELECT count(grau), count(codigoCompetenciaTecnica) INTO v_count_grau, v_count_codigo
    FROM GrauProficiencia
    WHERE 
        grau LIKE p_grau
        AND
        codigocompetenciatecnica LIKE p_codigoCompetenciaTecnica;       
  
    
    IF v_count_grau = 0 AND v_count_codigo = 0
    THEN
        RAISE ex_GrauProficiencia;
    END IF;
    
    EXCEPTION WHEN
        ex_GrauProficiencia
    THEN
        RAISE_APPLICATION_ERROR(-20001, 'Grau de Proficiência inexistente.');

END;
/