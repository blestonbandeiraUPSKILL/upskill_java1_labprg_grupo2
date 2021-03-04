CREATE OR REPLACE PROCEDURE createUtilizadorGestor(
    p_emailGestor utilizador.email%type,
    p_nomeGestor utilizador.nome%type,
    p_password utilizador.password%type,
    p_telefoneGestor colaborador.telefone%type,
    p_rolename rolename.designacao%type,
    p_funcao colaborador.funcao%type,
    p_nifOrganizacao organizacao.nif%type
    )
IS
    v_emailGestor utilizador.email%type;
    v_rolename Rolename.idRolename%type;
    
BEGIN

    SELECT idRolename INTO v_rolename
    FROM Rolename 
    WHERE designacao LIKE p_rolename;
    
    INSERT INTO Utilizador
        (email, nome, password, idRolename)
    VALUES
        (p_emailGestor, p_nomeGestor, p_password, v_rolename)
    RETURNING p_emailGestor
    INTO v_emailGestor;
    
    INSERT INTO Colaborador
        (email, funcao, telefone, nifOrganizacao)
    VALUES
        (v_emailGestor, p_funcao, p_telefoneGestor, p_nifOrganizacao);
        

END;
/