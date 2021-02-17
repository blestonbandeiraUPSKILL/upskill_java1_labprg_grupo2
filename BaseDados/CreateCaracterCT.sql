CREATE OR REPLACE PROCEDURE createCaracterCT(
    p_obrigatoria caracterct.obrigatoria%type,
    p_grauProfMinimo grauproficiencia.idgrauproficiencia%type
    )
IS
    v_count int;
    ex_GrauProficiencia exception;
    
BEGIN

    SELECT count(*) INTO v_count
    FROM GrauProficiencia
    WHERE idGrauProficiencia = p_grauProfMinimo;
    
    IF v_count = 0
    THEN
        RAISE ex_GrauProficiencia;
    END IF;
    
    INSERT INTO CaracterCT
        (obrigatoria, grauprofminimo)
    VALUES
        (p_obrigatoria, p_grauprofminimo);
    
    EXCEPTION WHEN
        ex_GrauProficiencia
    THEN
        RAISE_APPLICATION_ERROR(-20001, 'Grau de Proficiência Inexistente');

END;
/