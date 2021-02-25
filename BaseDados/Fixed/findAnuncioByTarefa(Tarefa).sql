CREATE OR REPLACE PROCEDURE findAnuncioByTarefa(
    p_nifOrganizacao Tarefa.nifOrganizacao%type,
    p_referencia tarefa.referencia%type
    )

IS
    v_count int;
    ex_Tarefa exception;
    
BEGIN

    SELECT count(*) INTO v_count
    FROM Tarefa
    WHERE 
        nifOrganizacao LIKE p_nifOrganizacao
        AND
        referencia LIKE p_referencia;
        
    IF v_count != 0
    THEN
        RAISE ex_Tarefa;
    END IF;
    
    EXCEPTION WHEN
        ex_Tarefa
    THEN
        RAISE_APPLICATION_ERROR(-20001, 'Tarefa existe.');

END;
/