CREATE OR REPLACE PROCEDURE createUtilizadorAdministrativo(
    p_email utilizador.email%type,
    p_nome utilizador.nome%type,
    p_password utilizador.password%type
    )
IS
    v_email utilizador.email%type;
    
BEGIN

    INSERT INTO Utilizador
        (email, nome, password, rolename)
    VALUES
        (p_email, p_nome, p_password, 'administrativo')
    RETURNING p_email
    INTO v_email;
    
    INSERT INTO Administrativo
        (email)
    VALUES
        (p_email);
END;
/