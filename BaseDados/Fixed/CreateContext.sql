CREATE OR REPLACE PROCEDURE createContext(
    p_value appcontext.value%type
    )

IS 
    v_idAppContext AppContext.idAppContext%type;
    
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