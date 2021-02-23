CREATE OR REPLACE PROCEDURE findByCategoria(
    p_codigoCategoria tarefa.codigocategoria%type    
    )

IS

    v_count int;
    ex_Tarefa exception;

BEGIN

    SELECT count(*) INTO v_count
    FROM Tarefa
    WHERE codigoCategoria LIKE p_codigoCategoria;
    
    IF v_count != 0
    THEN
        RAISE ex_Tarefa;
    END IF;
    
    EXCEPTION WHEN
        ex_Tarefa
    THEN
        RAISE_APPLICATION_ERROR(-20001, 'Tarefa já existe.');

END;
/