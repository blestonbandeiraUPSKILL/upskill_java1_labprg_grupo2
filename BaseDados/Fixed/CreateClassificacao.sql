CREATE OR REPLACE PROCEDURE createClassificacao(
    p_posicao classificacao.posicao%type,
    p_idProcessoSeriacao classificacao.idprocessoseriacao%type,
    p_idCandidatura classificacao.idcandidatura%type)
   

IS 

BEGIN

    INSERT INTO Classificacao
        (posicao, idProcessoSeriacao, idCandidatura)
        VALUES
        (p_posicao, p_idProcessoSeriacao, p_idCandidatura);
 
END;