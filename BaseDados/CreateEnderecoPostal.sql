CREATE OR REPLACE PROCEDURE createEnderecoPostal(
    p_arruamento enderecopostal.arruamento%type, 
    p_numeroPorta enderecopostal.numeroporta%type,
    p_localidade enderecopostal.localidade%type,
    p_codPostal enderecopostal.codpostal%type
    )
IS
    v_idEnderecoPostal enderecopostal.idenderecopostal%type;

BEGIN

    INSERT INTO EnderecoPostal
        (arruamento, numeroPorta, localidade, codPostal)
    VALUES
        (p_arruamento, p_numeroPorta, p_localidade, p_codPostal)
    RETURNING idEnderecoPostal 
    INTO v_idEnderecoPostal;    

END;
/