CREATE OR REPLACE PROCEDURE findByCodigoCategoria(
    p_codigoCategoria categoria.codigoCategoria%type
    )

IS
    v_count int;
    ex_Categoria exception;

BEGIN
    SELECT count(*) INTO v_count
    FROM Categoria
    WHERE codigoCategoria LIKE p_codigoCategoria;
    
    IF v_count = 0
    THEN
        RAISE ex_Categoria;
    END IF;
    
    EXCEPTION WHEN
        ex_Categoria
    THEN
        RAISE_APPLICATION_ERROR(-20001, 'Categoria Inexistente');

END;
/