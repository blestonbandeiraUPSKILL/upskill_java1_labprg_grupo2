CREATE OR REPLACE PROCEDURE findByCompetenciaTecnica(
    p_codigoCompetenciaTecnica grauproficiencia.codigoCompetenciaTecnica%type
    )

IS
    v_count int;
    ex_GrauProficiencia exception;

BEGIN
    SELECT count(*) INTO v_count
    From GrauProficiencia
    WHERE codigocompetenciatecnica LIKE p_codigoCompetenciaTecnica;
    
    IF v_count != 0
    THEN
        RAISE ex_GrauProficiencia;
    END IF;
    
    EXCEPTION WHEN
        ex_GrauProficiencia
    THEN
        RAISE_APPLICATION_ERROR(-20001, 'Grau de Proficiência já existe.');

END;
/