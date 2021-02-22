CREATE OR REPLACE PROCEDURE findCategoriaByAreaActividade(
    p_codigoAreaActividade categoria.codigoAreaActividade%type   
    )

IS
    v_count int;
    ex_Categoria exception;

BEGIN
    SELECT count(*) INTO v_count
    FROM Categoria
    WHERE codigoAreaActividade LIKE p_codigoAreaActividade;
    
    IF v_count != 0
    THEN
        RAISE ex_Categoria;
    END IF;
    
    EXCEPTION WHEN
        ex_Categoria
    THEN
        RAISE_APPLICATION_ERROR(-20001, 'Categoria já existe.');

END;
/