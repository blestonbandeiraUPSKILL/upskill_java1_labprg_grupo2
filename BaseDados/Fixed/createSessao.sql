CREATE OR REPLACE PROCEDURE createSessao(
    p_value appContext.value%type
    )

IS
    v_idAppContext appcontext.idappcontext%type;
    
BEGIN

    SELECT idAppContext INTO v_idAppContext
    FROM AppContext 
    WHERE AppContext.value LIKE p_value;
    
    INSERT INTO UserSession
        (idAppContext)
    VALUES
        (v_idAppContext);

END;
/