CREATE OR REPLACE TRIGGER trgValidaPeriodoPublicitacao
    AFTER INSERT OR UPDATE
    ON Anuncio
    FOR EACH ROW
    
BEGIN

    IF TRUNC
        (:new.dataInicioPublicitacao) < trunc(sysdate) 
    THEN
        RAISE_APPLICATION_ERROR(-20000, 'A data de início de publicitação do anúncio não pode ser anterior à data actual.');    
    END IF;
    
END;
/