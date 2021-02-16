CREATE OR REPLACE PROCEDURE createAreaActividade(
    p_codigoAreaActividade areaactividade.codigoareaactividade%type,
    p_descBreve areaactividade.descbreve%type,
    p_descDetalhada areaactividade.descdetalhada%type
    )
IS
    
BEGIN

    INSERT INTO AreaActividade
        (codigoareaactividade, descbreve, descdetalhada)
    VALUES
        (p_codigoareaactividade, p_descbreve, p_descdetalhada);
    
END;
/