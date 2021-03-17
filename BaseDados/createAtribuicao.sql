CREATE OR REPLACE PROCEDURE createAtribuicao(
    p_nifOrganizacao Atribuicao.nifOrganizacao%type,
    p_referenciaTarefa Atribuicao.referenciaTarefa%type,
    p_emailFreelancer Atribuicao.emailFreelancer%type,
    p_idAnuncio Atribuicao.idAnuncio%type
    )

IS
    v_idAtribuicao Atribuicao.idAtribuicao%type;
    
BEGIN

    select *
  from ( select a.*, max(pk) over () as max_pk
           from my_table a
                )
 where pk = max_pk
END;
/