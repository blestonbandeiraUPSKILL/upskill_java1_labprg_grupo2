CREATE OR REPLACE PROCEDURE findByNifEEmail(
    p_nifOrganizacao Tarefa.nifOrganizacao%type,
    p_email tarefa.emailcolaborador%type
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
        emailColaborador LIKE p_email;
        
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