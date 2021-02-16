CREATE OR REPLACE PROCEDURE createTipoRegimento(
    p_designacao tiporegimento.designacao%type,
    p_descricaoRegras tiporegimento.descricaoregras%type
    )
IS
    v_idTipoRegimento tiporegimento.idtiporegimento%type;
    
BEGIN

    INSERT INTO TipoRegimento
        (designacao, descricaoRegras)
    VALUES
        (p_designacao, p_descricaoregras)
    RETURNING idtiporegimento
    INTO v_idTipoRegimento;
    
END;
/