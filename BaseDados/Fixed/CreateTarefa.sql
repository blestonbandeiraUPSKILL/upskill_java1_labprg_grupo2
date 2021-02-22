CREATE OR REPLACE PROCEDURE createTarefa(
    p_nifOrganizacao organizacao.nif%type,
    p_referencia tarefa.referencia%type,
    p_designacao tarefa.designacao%type,
    p_descInformal tarefa.descinformal%type,
    p_descTecnica tarefa.desctecnica%type,
    p_duracaoEstimada tarefa.duracaoestimada%type,
    p_custoEstimado tarefa.custoestimado%type,
    p_codigoCategoria categoria.codigoCategoria%type,
    p_emailColaborador colaborador.email%type
    )

IS
 

BEGIN
    
    INSERT INTO Tarefa
        (nifOrganizacao, referencia, designacao, descInformal, descTecnica, 
        duracaoEstimada, custoEstimado, codigoCategoria, emailColaborador)
    VALUES
        (p_nifOrganizacao, p_referencia, p_designacao, p_descInformal, p_descTecnica, 
        p_duracaoEstimada, p_custoEstimado, p_codigoCategoria, p_emailColaborador);
    

END;
/