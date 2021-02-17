CREATE OR REPLACE PROCEDURE createCategoria(
    p_descBreve categoria.descbreve%type,
    p_descDetalhada categoria.descdetalhada%type,
    p_codigoAreaActividade areaactividade.codigoareaactividade%type
    )
IS
    v_count int;
    ex_AreaActividade exception;
    
BEGIN

    SELECT count(*) INTO v_count
    FROM AreaActividade
    WHERE codigoAreaActividade = p_codigoAreaActividade;
    
    IF v_count = 0
    THEN
        RAISE ex_AreaActividade;
    END IF;

    INSERT INTO Categoria
        (descbreve, descdetalhada, codigoareaactividade)
    VALUES
        (p_descbreve, p_descdetalhada, p_codigoareaactividade);
        
    EXCEPTION WHEN
        ex_AreaActividade
    THEN
        RAISE_APPLICATION_ERROR(-20001, 'Área de Actividade Inexistente');
    
END;
/