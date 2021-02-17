CREATE OR REPLACE FUNCTION getOrganizacaoByNif(
    p_nifOrganizacao organizacao.nif%type
    )

IS
    v_count int;

BEGIN
    SELECT count(*) INTO v_count
    FROM Organizacao
    WHERE nif = p_nifOrganizacao;
    
    

END;
/