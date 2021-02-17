CREATE OR REPLACE PROCEDURE createReconhecimentoGP(
    p_dataReconhecimento reconhecimentogp.datareconhecimento%type,
    p_codigoCompetenciaTecnica competenciatecnica.codigocompetenciatecnica%type
    )

IS
    v_count int;
    ex_CompetenciaTecnica exception;

BEGIN

    SELECT count(*) INTO v_count
    FROM CompetenciaTecnica
    WHERE codigocompetenciatecnica = p_codigocompetenciatecnica;
    
    IF v_count = 0
    THEN
        RAISE ex_CompetenciaTecnica;
    END IF;
    
    INSERT INTO ReconhecimentoGP
        (dataReconhecimento, codigoCompetenciaTecnica)
    VALUES
        (p_dataReconhecimento, p_codigoCompetenciaTecnica);
        
    EXCEPTION WHEN
        ex_CompetenciaTecnica
    THEN
        RAISE_APPLICATION_ERROR(-20001, 'Competência Técnica inexistente');

END;
/