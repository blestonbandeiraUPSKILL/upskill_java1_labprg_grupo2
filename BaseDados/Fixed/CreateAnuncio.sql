CREATE OR REPLACE PROCEDURE createAnuncio(
    p_nifOrganizacao tarefa.niforganizacao%type,
    p_referenciaTarefa tarefa.referencia%type,
    p_dataInicioPublicitacao anuncio.datainicioPublicitacao%type,
    p_dataFimPublicitacao anuncio.datafimPublicitacao%type,
    p_dataInicioCandidatura anuncio.datainiciocandidatura%type,
    p_dataFimCandidatura anuncio.datafimcandidatura%type,
    p_dataInicioSeriacao anuncio.datainicioseriacao%type,
    p_dataFimSeriacao anuncio.datafimseriacao%type,
    p_idProcessoSeriacao processoSeriacao.idProcessoSeriacao%type
    )

IS
   
BEGIN
    
    INSERT INTO Anuncio
        (referenciaTarefa, nifOrganizacao, dataInicioPublicitacao, dataFimPublicitacao, dataInicioCandidatura, dataFimCandidatura, dataInicioSeriacao, dataFimSeriacao, idProcessoSeriacao)
    VALUES
        (p_referenciaTarefa, p_nifOrganizacao, p_dataInicioPublicitacao, p_dataFimPublicitacao, p_dataInicioCandidatura, p_dataFimCandidatura, p_dataInicioSeriacao, p_dataFimSeriacao, p_idProcessoSeriacao);
        
END;
/