CREATE OR REPLACE PROCEDURE createAnuncio(
    p_nifOrganizacao anuncio.niforganizacao%type,
     p_referenciaTarefa anuncio.referenciatarefa%type,
    p_dataInicioPublicitacao anuncio.datainicioPublicitacao%type,
    p_dataFimPublicitacao anuncio.datafimPublicitacao%type,
    p_dataInicioCandidatura anuncio.datainiciocandidatura%type,
    p_dataFimCandidatura anuncio.datafimcandidatura%type,
    p_dataInicioSeriacao anuncio.datainicioseriacao%type,
    p_dataFimSeriacao anuncio.datafimseriacao%type,
    p_idTipoRegimento tipoRegimento.idTipoRegimento%type
    )

IS
   
BEGIN
    
    INSERT INTO Anuncio
        (nifOrganizacao, referenciaTarefa, dataInicioPublicitacao, dataFimPublicitacao, dataInicioCandidatura, dataFimCandidatura, dataInicioSeriacao, dataFimSeriacao, idTipoRegimento)
    VALUES
        (p_nifOrganizacao, p_referenciaTarefa, p_dataInicioPublicitacao, p_dataFimPublicitacao, p_dataInicioCandidatura, p_dataFimCandidatura, p_dataInicioSeriacao, p_dataFimSeriacao, p_idTipoRegimento);
        
END;
/