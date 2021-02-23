CREATE OR REPLACE PROCEDURE findByCategoria(
    p_codigoCategoria caracterCT.codigoCategoria%TYPE
    )

IS
    v_count int;
    ex_CaracterCT exception;

BEGIN
    SELECT count(*) INTO v_count
    FROM CaracterCT
    WHERE codigoCategoria LIKE p_codigoCategoria;
    
    IF v_count != 0
    THEN
        RAISE ex_CaracterCT;
    END IF;
    
    EXCEPTION WHEN
        ex_CaracterCT
    THEN
        RAISE_APPLICATION_ERROR(-20001, 'CaracterCT já existe');

END;
/