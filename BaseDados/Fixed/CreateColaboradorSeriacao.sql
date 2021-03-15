CREATE OR REPLACE PROCEDURE createColaboradorSeriacao(
    p_emailColaborador colaboradorseriacao.emailcolaborador%type,
    p_idProcessoSeriacao colaboradorSeriacao.idProcessoSeriacao%type
    )

IS

BEGIN
    INSERT INTO ColaboradorSeriacao
    (emailColaborador, idProcessoSeriacao)
    VALUES
    (p_emailColaborador, p_idProcessoSeriacao);
    
END;
/