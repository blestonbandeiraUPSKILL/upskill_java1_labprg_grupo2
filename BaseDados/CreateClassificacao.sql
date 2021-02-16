CREATE OR REPLACE PROCEDURE createClassificacao(
    p_lugar classificacao.lugar%type,
    p_idProcessoSeriacao processoseriacao.idprocessoseriacao%type
    )
IS
    v_count int;
    ex_ProcessoSeriacao exception;
    
BEGIN

    SELECT count(*) INTO v_count
    FROM ProcessoSeriacao
    WHERE idProcessoSeriacao = p_idProcessoSeriacao;

    IF v_count = 0
    THEN
        RAISE ex_ProcessoSeriacao;
    END IF;
    
    INSERT INTO Classificacao
        (lugar, idprocessoseriacao)
    VALUES
        (p_lugar, p_idprocessoseriacao);
   
    EXCEPTION WHEN
        ex_ProcessoSeriacao
    THEN
        RAISE_APPLICATION_ERROR(-20001, 'Processo de Seriação Inexistente');
    
END;
/