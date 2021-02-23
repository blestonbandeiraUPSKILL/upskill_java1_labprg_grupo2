CREATE OR REPLACE PROCEDURE findPasswordByEmail(
    p_email utilizador.password%type
    )

IS
    v_count int;
    ex_Password exception;
    
BEGIN
    SELECT utilizador.password
    FROM Utilizador 
    INNER JOIN Colaborador ON
        colaborador.email LIKE utilizador.email
    WHERE colaborador.email LIKE p_email;

END;
/
    
