CREATE OR REPLACE FUNCTION setState(
    p_context AppContext.value%type
    )

RETURN 
     appContext.idEstadoContext%type
     
IS
    v_idEstadoContext int;
    v_timestamp date;
   
BEGIN

    SELECT idEstadoContext INTO v_idEstadoContext
    FROM AppContext
    WHERE value LIKE p_context;
    
    SELECT timestamp INTO v_timestamp
    FROM AppContext
    WHERE value LIKE p_context;

    IF (v_timestamp +(20/(24*60)) > sysdate ) THEN 
        v_idEstadoContext := 2;
    ELSE
       v_idEstadoContext := 3;

    END IF;
    
    RETURN v_idEstadoContext;
    
END;
/