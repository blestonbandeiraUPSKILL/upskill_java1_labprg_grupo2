CREATE OR REPLACE PROCEDURE findByEmail(
    p_email colaborador.email%type
    )

IS
    v_count int;
    ex_Colaborador exception;
    
BEGIN
    SELECT count(*) INTO v_count
    FROM Colaborador 
    WHERE email LIKE p_email;
    
    IF v_count != 0
    THEN
        RAISE ex_Colaborador;
    END IF;
    
    EXCEPTION WHEN
        ex_Colaborador
    THEN
        RAISE_APPLICATION_ERROR(-20001, 'Colaborador já existe.');
        

END;
/