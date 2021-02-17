CREATE OR REPLACE FUNCTION getOrganizacaoByNif (
    p_nifOrganizacao organizacao.nif%type
    )
    
RETURN int

IS
    v_count int;

BEGIN
    SELECT count(*) INTO v_count
    FROM Organizacao
    WHERE nif = p_nifOrganizacao;
   
   RETURN v_count;
    
END;
/