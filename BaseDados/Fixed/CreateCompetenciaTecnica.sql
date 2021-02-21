CREATE OR REPLACE PROCEDURE createCompetenciaTecnica(
    p_codigoCompetenciaTecnica competenciatecnica.codigocompetenciatecnica%type,
    p_descBreve competenciatecnica.descbreve%type,
    p_descDetalhada competenciatecnica.descdetalhada%type,
    p_codigoAreaActividade areaactividade.codigoareaactividade%type
    )
IS
   

BEGIN

    INSERT INTO CompetenciaTecnica
        (codigocompetenciatecnica, descbreve, descdetalhada, codigoareaactividade)
    VALUES
        (p_codigocompetenciatecnica, p_descbreve, p_descdetalhada, p_codigoareaactividade);
    
    
END;
/