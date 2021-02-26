CREATE OR REPLACE TRIGGER trgValidaDataReconhecimento
    AFTER INSERT OR UPDATE
    ON ReconhecimentoGP
    FOR EACH ROW
    
BEGIN

    IF TRUNC
        (:new.dataReconhecimento) > trunc(sysdate) 
    THEN
        RAISE_APPLICATION_ERROR(-20000, 'A data de in�cio de publicita��o do an�ncio n�o pode ser anterior � data actual.');    
    END IF;
    
END;
/