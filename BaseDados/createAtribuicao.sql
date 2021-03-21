CREATE OR REPLACE PROCEDURE createAtribuicao(
    p_nifOrganizacao Atribuicao.nifOrganizacao%type,
    p_referenciaTarefa Atribuicao.referenciaTarefa%type,
    p_emailFreelancer Atribuicao.emailFreelancer%type,
    p_idAnuncio Atribuicao.idAnuncio%type
    )

IS
    v_idAtribuicao Atribuicao.idAtribuicao%type;
    v_numeroAnual Atribuicao.numeroAnual%type;
    v_ano varchar(4);
    
BEGIN

    SELECT *
    FROM ( 
        SELECT max(IDATRIBUICAO) over () as max_pk
        FROM Atribuicao
        );
    
    SELECT to_char(sysdate, 'YYYY') INTO v_ano 
    FROM dual;
    
    CONCAT( v_ano,  max_pk);
    
    
END;
/