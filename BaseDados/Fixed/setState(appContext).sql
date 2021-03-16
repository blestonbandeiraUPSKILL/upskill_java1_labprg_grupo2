CREATE OR REPLACE FUNCTION setState(
    p_context AppContext.value%type
    )

RETURN 
     appContext.idEstadoContext%type
     
IS
    v_idEstadoContext int;
    v_timestamp date;
   
BEGIN

    SELECT timestamp INTO v_timestamp
    FROM AppContext
    WHERE value LIKE p_context;

    IF (v_timestamp +(20/(24*60)) > sysdate ) THEN 
        UPDATE AppContext 
        SET IDESTADOCONTEXT = 2
        WHERE value LIKE p_context;
    ELSE
    UPDATE AppContext 
        SET IDESTADOCONTEXT = 3
        WHERE value LIKE p_context;
    END IF;
    
    SELECT idEstadoContext INTO v_idEstadoContext
    FROM AppContext
    WHERE value LIKE p_context;
    
    RETURN v_idEstadoContext;
    
END;
/