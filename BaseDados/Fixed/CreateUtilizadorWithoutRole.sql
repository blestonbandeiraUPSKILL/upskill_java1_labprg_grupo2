CREATE OR REPLACE PROCEDURE createUtilizadorWithoutRole(
    p_email utilizador.email%type,
    p_nome utilizador.nome%type,
    p_password utilizador.password%type
    )

IS

BEGIN
    
    INSERT INTO Utilizador
    (email, nome, password)
    VALUES
    (p_email, p_nome, p_password);

END;