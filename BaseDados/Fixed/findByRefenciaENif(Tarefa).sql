CREATE OR REPLACE PROCEDURE findByRefENif(
    p_referencia tarefa.referencia%type,
    p_nifOrganizacao tarefa.nifOrganizacao%type
    )

IS
    v_count int;
    ex_Tarefa exception;
    
BEGIN
    SELECT count(*) INTO v_count
    FROM Tarefa
    WHERE 
        referencia LIKE p_referencia 
        AND
        nifOrganizacao LIKE p_nifOrganizacao;
        
    IF v_count != 0
    THEN   
        RAISE ex_Tarefa;
    END IF;
    
    EXCEPTION WHEN
        ex_Tarefa
    THEN RAISE_APPLICATION_ERROR(-20001, 'Tarefa já existe.');

END;
/