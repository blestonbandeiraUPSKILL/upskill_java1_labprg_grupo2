CREATE OR REPLACE PROCEDURE createUtilizadorGestor(
    p_emailGestor utilizador.email%type,
    p_funcao colaborador.funcao%type,
    p_telefoneGestor colaborador.telefone%type,
    p_nifOrganizacao organizacao.nif%type 
    )
IS
    
BEGIN
    
    INSERT INTO Colaborador
        (email, funcao, telefone, nifOrganizacao)
    VALUES
        (p_emailGestor, p_funcao, p_telefoneGestor, p_nifOrganizacao);
        

END;
/