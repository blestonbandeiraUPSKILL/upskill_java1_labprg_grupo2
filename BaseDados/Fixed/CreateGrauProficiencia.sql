CREATE OR REPLACE PROCEDURE createGrauProficiencia(
    p_grau grauProficiencia.grau%type,
    p_designacao grauproficiencia.designacao%type,
    p_codigoCompetenciaTecnica competenciaTecnica.codigoCompetenciaTecnica%type
   )
IS

BEGIN
    
    INSERT INTO GrauProficiencia
        (grau, designacao, codigoCompetenciaTecnica)
    VALUES
        (p_grau, p_designacao, p_codigoCompetenciaTecnica);
    
END;
/

