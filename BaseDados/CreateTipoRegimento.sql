CREATE OR REPLACE PROCEDURE createTipoRegimento(
    p_designacao tiporegimento.designacao%type,
    p_descricaoRegras tiporegimento.descricaoregras%type
    )
    
IS
    
BEGIN

    INSERT INTO TipoRegimento
        (designacao, descricaoRegras)
    VALUES
        (p_designacao, p_descricaoregras);
    
END;
/