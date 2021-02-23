CREATE OR REPLACE PROCEDURE findById(
    p_idEnderecoPostal enderecoPostal.idEnderecoPostal%type
    )

IS
    v_count int;
    ex_EnderecoPostal exception;
    
BEGIN

    SELECT count(*) INTO v_count
    FROM EnderecoPostal
    WHERE idenderecopostal LIKE p_idEnderecoPostal;
    
    IF v_count != 0
    THEN
        RAISE ex_EnderecoPostal;
    END IF;
    
    EXCEPTION WHEN
        ex_EnderecoPostal
    THEN
        RAISE_APPLICATION_ERROR(-20001, 'Endereço Postal já existe.');
        

END;
/