CREATE OR REPLACE PROCEDURE createUtilizadorColaborador(
    p_email colaborador.email%type,
    p_funcao colaborador.funcao%type,
    p_telefone colaborador.telefone%type,
    p_nifOrganizacao organizacao.nif%type
    )
IS
  
BEGIN

    INSERT INTO Colaborador
        (email, funcao, telefone, nifOrganizacao)
    VALUES
        (p_email, p_funcao, p_telefone, p_nifOrganizacao);
END;
/