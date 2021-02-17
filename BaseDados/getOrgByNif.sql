CREATE OR REPLACE PROCEDURE getOrganizacaoByNif (
    p_nifOrganizacao organizacao.nif%type
    )

IS
    v_count int;
    ex_Organizacao exception;
    
BEGIN
    SELECT count(*) INTO v_count
    FROM Organizacao
    WHERE nif = p_nifOrganizacao;
    
    IF v_count = 0
    THEN
        RAISE ex_Organizacao;
    END IF;
        
END;
/