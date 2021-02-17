CREATE OR REPLACE PROCEDURE createTarefa(
    p_referencia tarefa.referencia%type,
    p_nifOrganizacao organizacao.nif%type,
    p_designacao tarefa.designacao%type,
    p_descInformal tarefa.descinformal%type,
    p_descTecnica tarefa.desctecnica%type,
    p_duracaoEstimada tarefa.duracaoestimada%type,
    p_custoEstimado tarefa.custoestimado%type,
    p_idCategoria categoria.idcategoria%type,
    p_emailColaborador colaborador.email%type
    )

IS
    v_countOrganizacao int;
    v_countCategoria int;
    v_countColaborador int;
    ex_Organizacao exception;
    ex_Categoria exception;
    ex_Colaborador exception;

BEGIN

    SELECT count(*) INTO v_countOrganizacao
    FROM Organizacao
    WHERE nif = p_nifOrganizacao;
    
    IF v_countOrganizacao = 0
    THEN
        RAISE ex_Organizacao;
    END IF;
    
    SELECT count(*) INTO v_countCategoria
    FROM Categoria
    WHERE idcategoria = p_idCategoria;
    
    IF v_countCategoria = 0
    THEN
        RAISE ex_Categoria;
    END IF;
    
    SELECT count(*) INTO v_countColaborador
    FROM Colaborador
    WHERE email = p_emailColaborador;
    
    IF v_countColaborador = 0
    THEN
        RAISE ex_Colaborador;
    END IF;
    
    INSERT INTO Tarefa
        (referencia, nifOrganizacao, designacao, descInformal, descTecnica, duracaoEstimada, custoEstimado, idCategoria, emailColaborador)
    VALUES
        (p_referencia, p_nifOrganizacao, p_designacao, p_descInformal, p_descTecnica, p_duracaoEstimada, p_custoEstimado, p_idCategoria, p_emailColaborador);
        
    EXCEPTION WHEN
        ex_Organizacao OR ex_Categoria OR ex_Colaborador
    THEN
        RAISE_APPLICATION_ERROR(-20001, 'Dados ienxistentes');

END;
/