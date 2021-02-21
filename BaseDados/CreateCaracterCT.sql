CREATE OR REPLACE PROCEDURE createCaracterCT(
    p_obrigatoria caracterct.obrigatoria%type,
    p_grauProfMinimo grauproficiencia.idgrauproficiencia%type
    )
IS
    
BEGIN
    
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