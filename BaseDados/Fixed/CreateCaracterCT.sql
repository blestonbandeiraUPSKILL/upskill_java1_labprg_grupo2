CREATE OR REPLACE PROCEDURE createCaracterCT(
    p_obrigatoria caracterct.obrigatoria%type,
    p_grauProfMinimo caracterCT.grauProfMinimo%type,
    p_codigoCategoria caracterCT.codigoCategoria%type
    )
IS

BEGIN
    
    INSERT INTO CaracterCT
        (obrigatoria, grauprofminimo, codigoCategoria)
    VALUES
        (p_obrigatoria, p_grauprofminimo, p_codigoCategoria);
    
   
END;
/