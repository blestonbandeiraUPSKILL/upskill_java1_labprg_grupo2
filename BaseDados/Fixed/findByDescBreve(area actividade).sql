CREATE OR REPLACE PROCEDURE findByCodigoAreaActividade(
    p_codigo areaactividade.codigoareaactividade%type
    )

IS
    v_count int;
    ex_AreaActividade exception;

BEGIN
    SELECT count(*) INTO v_count
    FROM AreaActividade
    WHERE codigoAreaActividade LIKE p_codigo;
    
    IF v_count = 0
    THEN
        RAISE ex_AreaActividade;
    END IF;
    
    EXCEPTION WHEN
        ex_AreaActividade
    THEN
        RAISE_APPLICATION_ERROR(-20001, 'Área de Actividade com este código já registada.');

END;
/