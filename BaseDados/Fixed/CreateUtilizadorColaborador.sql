CREATE OR REPLACE PROCEDURE createUtilizadorColaborador(
    p_email utilizador.email%type,
    p_nome utilizador.nome%type,
    p_password utilizador.password%type,
    p_rolename rolename.designacao%type,
    p_telefone colaborador.telefone%type,
    p_funcao colaborador.funcao%type,
    p_nifOrganizacao organizacao.nif%type
    )
IS
    v_email utilizador.email%type;
    v_rolename Rolename.idRolename%type;
    
BEGIN

    SELECT idRolename INTO v_rolename
    FROM Rolename
    WHERE designacao LIKE p_rolename;
    
    INSERT INTO Utilizador
        (email, nome, password, idRolename)
    VALUES
        (p_email, p_nome, p_password, v_rolename)
    RETURNING p_email
    INTO v_email;
    
    INSERT INTO Colaborador
        (email, funcao, telefone, nifOrganizacao)
    VALUES
        (v_email, p_funcao, p_telefone, p_nifOrganizacao);
END;
/