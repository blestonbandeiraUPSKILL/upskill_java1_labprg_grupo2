CREATE OR REPLACE PROCEDURE createAnuncio(
    p_dataInicioPublicacao anuncio.datainiciopublicacao%type,
    p_dataFimPublicacao anuncio.datafimpublicacao%type,
    p_dataInicioCandidatura anuncio.datainiciocandidatura%type,
    p_dataFimCandidatura anuncio.datafimcandidatura%type,
    p_dataInicioSeriacao anuncio.datainicioseriacao%type,
    p_dataFimSeriacao anuncio.datafimseriacao%type,
    p_referenciaTarefa tarefa.referencia%type,
    p_nifOrganizacao tarefa.niforganizacao%type,
    p_idProcessoSeriacao processoSeriacao.idProcessoSeriacao%type
    )

IS
    v_countTarefa int;
    v_countProcessoSeriacao int;
    ex_Tarefa exception;
    ex_ProcessoSeriacao exception;

BEGIN

    SELECT count(*) INTO v_countTarefa
    FROM Tarefa
    WHERE referencia = p_referenciaTarefa AND nifOrganizacao = p_nifOrganizacao;
    
    IF v_countTarefa = 0
    THEN
        RAISE ex_Tarefa;
    END IF;
    
    SELECT count(*) INTO v_countProcessoSeriacao
    FROM ProcessoSeriacao
    WHERE idProcessoSeriacao = p_idProcessoSeriacao;
    
    IF v_countProcessoSeriacao = 0
    THEN
        RAISE ex_ProcessoSeriacao;
    END IF;
    
    INSERT INTO Anuncio
        (dataInicioPublicacao, dataFimPublicacao, dataInicioCandidatura, dataFimCandidatura, dataInicioSeriacao, dataFimSeriacao, referenciaTarefa, nifOrganizacao, idProcessoSeriacao)
    VALUES
        (p_dataInicioPublicacao, p_dataFimPublicacao, p_dataInicioCandidatura, p_dataFimCandidatura, p_dataInicioSeriacao, p_dataFimSeriacao, p_referenciaTarefa, p_nifOrganizacao, p_idProcessoSeriacao);
    
    EXCEPTION WHEN
        ex_Tarefa OR ex_ProcessoSeriacao
    THEN
        ROLLBACK;
        RAISE_APPLICATION_ERROR(-20001, 'Tarefa Inexistente');
    

END;
/