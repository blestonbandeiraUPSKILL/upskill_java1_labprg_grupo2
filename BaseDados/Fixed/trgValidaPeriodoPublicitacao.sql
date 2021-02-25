CREATE OR REPLACE TRIGGER trgValidaPeriodoPublicitacao
    AFTER INSERT OR UPDATE
    ON Anuncio
    FOR EACH ROW
    
BEGIN

    IF TRUNC
        (:new.dataInicioPublicitacao) < trunc(sysdate) 
    THEN
        RAISE_APPLICATION_ERROR(-20000, 'A data de in�cio de publicita��o do an�ncio n�o pode ser anterior � data actual.');    
    END IF;
    
END;
/