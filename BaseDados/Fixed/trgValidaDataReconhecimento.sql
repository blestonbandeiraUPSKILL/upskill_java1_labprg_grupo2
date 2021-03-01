CREATE OR REPLACE TRIGGER trgValidaDataReconhecimento
    AFTER INSERT OR UPDATE
    ON ReconhecimentoGP
    FOR EACH ROW
    
BEGIN

    IF TRUNC
        (:new.dataReconhecimento) > trunc(sysdate) 
    THEN
        RAISE_APPLICATION_ERROR(-20000, 'A data de início de publicitação do anúncio não pode ser anterior à data actual.');    
    END IF;
    
END;
/