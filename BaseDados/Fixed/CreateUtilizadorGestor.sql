CREATE OR REPLACE PROCEDURE createUtilizadorGestor(
    p_emailGestor utilizador.email%type,
    p_funcao colaborador.funcao%type,
    p_telefoneGestor colaborador.telefone%type,
    p_nifOrganizacao organizacao.nif%type,
    p_rolename rolename.designacao%type  
    )
IS
    v_rolename Rolename.idRolename%type;
    
BEGIN

    SELECT idRolename INTO v_rolename
    FROM Rolename 
    WHERE designacao LIKE p_rolename;
    
    INSERT INTO Colaborador
        (email, funcao, telefone, nifOrganizacao)
    VALUES
        (p_emailGestor, p_funcao, p_telefoneGestor, p_nifOrganizacao);
        

END;
/