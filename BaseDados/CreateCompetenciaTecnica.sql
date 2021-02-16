CREATE OR REPLACE PROCEDURE createCompetenciaTecnica(
    p_codigoCompetenciaTecnica competenciatecnica.codigocompetenciatecnica%type,
    p_descBreve competenciatecnica.descbreve%type,
    p_descDetalhada competenciatecnica.descdetalhada%type,
    p_idCaracterCT caracterct.idcaracterct%type,
    p_codigoAreaActividade areaactividade.codigoareaactividade%type
    )
IS
    v_count int;
    ex_AreaActividade exception;
    
BEGIN

    SELECT count(*) INTO v_count
    FROM AreaActividade 
    WHERE codigoareaactividade = p_codigoareaactividade;
    
    IF v_count = 0
    THEN
        RAISE ex_AreaActividade;
    END IF;    

    INSERT INTO CompetenciaTecnica
        (codigocompetenciatecnica, descbreve, descdetalhada, idcaracterct, codigoareaactividade)
    VALUES
        (p_codigocompetenciatecnica, p_descbreve, p_descdetalhada, p_idcaracterct, p_codigoareaactividade);
    
    EXCEPTION WHEN
        ex_AreaActividade
    THEN
        RAISE_APPLICATION_ERROR(-20001, 'Área de Actividade Inexistente.');
    
END;
/