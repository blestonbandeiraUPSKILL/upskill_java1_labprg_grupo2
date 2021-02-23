CREATE OR REPLACE PROCEDURE findByCodigoCateriaECodigoGrau(
    p_codigoCategoria caracterCT.codigoCategoria%type,
    p_codigoGrau caracterct.grauprofminimo%type
    )
    
IS
    v_count int;
    ex_CaracterCT exception;

BEGIN

    SELECT count(*) INTO v_count
    FROM CaracterCT
    WHERE 
        codigocategoria LIKE p_codigoCategoria
        AND
        grauprofminimo LIKE p_codigoGrau;
        
    IF v_count != 0
    THEN
        RAISE ex_CaracterCT;
    END IF;
    
    EXCEPTION WHEN
        ex_CaracterCT
    THEN
        RAISE_APPLICATION_ERROR(-20001, 'CaracterCT já existe.');

END;
/