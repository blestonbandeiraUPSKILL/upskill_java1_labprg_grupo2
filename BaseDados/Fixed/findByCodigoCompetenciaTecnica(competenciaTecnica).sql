CREATE OR REPLACE PROCEDURE findByCodigoCompetenciaTecnica(
    p_codigo competenciatecnica.codigocompetenciatecnica%type
    )

IS
    v_count int;
    ex_CompetenciaTecnica exception;

BEGIN

    SELECT count(*) INTO v_count
    From CompetenciaTecnica
    WHERE codigoCompetenciaTecnica LIKE p_codigo;
    
    IF v_count = 0
    THEN
        RAISE ex_CompetenciaTecnica;
    END IF;
    
    EXCEPTION WHEN
        ex_CompetenciaTecnica
    THEN
        RAISE_APPLICATION_ERROR(-20001, 'Competência técnica inexistente.');

END;