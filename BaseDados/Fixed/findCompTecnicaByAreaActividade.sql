CREATE OR REPLACE PROCEDURE findCompTecnicaByAreaActividade(
    p_codigoAreaActividade competenciatecnica.codigoareaactividade%type
    )

IS
    v_count int;
    ex_CompetenciaTecnica exception;
    
BEGIN
    SELECT count(*) INTO v_count
    FROM CompetenciaTecnica
    WHERE 
    codigoAreaActividade LIKE p_codigoAreaActividade;
    
    IF v_count = 0
    THEN
        RAISE ex_CompetenciaTecnica;
    END IF;
    
    EXCEPTION WHEN
        ex_CompetenciaTecnica
    THEN
        RAISE_APPLICATION_ERROR(-20001, 'Competência Técnica inexistente.');

END;
/