CREATE OR REPLACE PROCEDURE createProcessoSeriacao(
    p_dataRealizacao processoseriacao.datarealizacao%type,
    p_idTipoRegimento tiporegimento.idtiporegimento%type
    )

IS
    v_count int;
    ex_TipoRegimento exception;

BEGIN
    
    SELECT count(*) INTO v_count
    FROM TipoRegimento
    WHERE idTipoRegimento = p_idTipoRegimento;
    
    IF v_count = 0
    THEN
        RAISE ex_TipoRegimento;
    END IF;
    
    INSERT INTO ProcessoSeriacao
        (dataRealizacao, idTipoRegimento)
    VALUES
        (p_dataRealizacao, p_idTipoRegimento);
        
    EXCEPTION WHEN
        ex_TipoRegimento
    THEN 
        RAISE_APPLICATION_ERROR(-20001, 'Tipo de Regimento Inexistente');
        
END;
/