CREATE OR REPLACE PROCEDURE findByGrau(
    p_grau grauProficiencia.grau%type,
    p_codigoCompetenciaTecnica competenciaTecnica.codigoCompetenciaTecnica%type
    )

IS
    v_count int;
    ex_GrauProficiencia exception;
    
BEGIN

    SELECT count(*) INTO v_count
    FROM GrauProficiencia
    WHERE grau LIKE p_grau AND codigoCompetenciaTecnica LIKE p_codigoCompetenciaTecnica;
    
    IF v_count = 0
    THEN
        RAISE ex_GrauProficiencia;
    END IF;
    
    EXCEPTION WHEN
        ex_GrauProficiencia
    THEN
        RAISE_APPLICATION_ERROR(-20001, 'Grau de Proficiência inexistente.');

END;
/