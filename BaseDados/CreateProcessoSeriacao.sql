CREATE OR REPLACE PROCEDURE createSeriacao(  
    p_idAnuncio ProcessoSeriacao.idAnuncio%type
    )

IS

BEGIN
    
    INSERT INTO ProcessoSeriacao
        (dataRealizacao, idAnuncio)
    VALUES
        (sysdate, p_idAnuncio);
    
END;
/