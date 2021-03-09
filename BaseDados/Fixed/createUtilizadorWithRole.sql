CREATE OR REPLACE PROCEDURE createUtilizadorWithRole(
    p_email utilizador.email%type,
    p_nome utilizador.nome%type,
    p_password utilizador.password%type,
    p_idRolename utilizador.idRolename%type
    )

IS
    
BEGIN
    
    INSERT INTO Utilizador
    (email, nome, password, idRolename)
    VALUES
    (p_email, p_nome, p_password, p_idRolename);

END;