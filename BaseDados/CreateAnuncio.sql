CREATE OR REPLACE PROCEDURE createAnuncio(
    p_dataInicioPublicacao anuncio.datainiciopublicacao%type,
    p_dataFimPublicacao anuncio.datafimpublicacao%type,
    p_dataInicioCandidatura anuncio.datainiciocandidatura%type,
    p_dataFimCandidatura anuncio.datafimcandidatura%type,
    p_dataInicioSeriacao anuncio.datainicioseriacao%type,
    p_dataFimSeriacao anuncio.datafimseriacao%type,
    p_referenciaTarefa tarefa.referencia%type,
    p_nifOrganizacao tarefa.niforganizacao%type
    )

IS
    v_count int;
    ex_Tarefa exception;

BEGIN

    SELECT count(*) INTO v_count
    FROM Tarefa
    WHERE referencia = p_referenciaTarefa AND nifOrganizacao = p_nifOrganizacao;
    
    IF v_count = 0
    THEN
        RAISE ex_Tarefa;
    END IF;
    
    INSERT INTO Anuncio
        (dataInicioPublicacao, dataFimPublicacao, dataInicioCandidatura, dataFimCandidatura, dataInicioSeriacao, dataFimSeriacao, referenciaTarefa, nifOrganizacao)
    VALUES
        (p_dataInicioPublicacao, p_dataFimPublicacao, p_dataInicioCandidatura, p_dataFimCandidatura, p_dataInicioSeriacao, p_dataFimSeriacao, p_referenciaTarefa, p_nifOrganizacao);
    
    EXCEPTION WHEN
        ex_Tarefa
    THEN
        ROLLBACK;
        RAISE_APPLICATION_ERROR(-20001, 'Tarefa Inexistente');
    

END;
/