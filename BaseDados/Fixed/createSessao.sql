CREATE OR REPLACE PROCEDURE createSessao(
    p_value appContext.value%type
    )

IS
    v_idAppContext appcontext.idappcontext%type;
    
BEGIN

    INSERT INTO AppContext
        (value)
    VALUES
        (p_value)
    RETURNING idAppContext
    INTO v_idAppContext;
    
    INSERT INTO UserSession
        (idAppContext, timestamp)
    VALUES
        (v_idAppContext, trunc(LOCALTIMESTAMP));

END;
/