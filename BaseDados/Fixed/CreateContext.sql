CREATE OR REPLACE PROCEDURE createContext(
    p_value appcontext.value%type, 
    p_emailUtilizador userSession.emailUtilizador%type
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
    (idAppContext, timestamp, emailUtilizador)
    VALUES
    (v_idAppContext, trunc(LOCALTIMESTAMP), p_emailUtilizador);
    

END;
/