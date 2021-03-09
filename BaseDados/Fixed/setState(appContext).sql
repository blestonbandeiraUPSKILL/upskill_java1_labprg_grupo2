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

    IF (((sysdate - (v_timestamp + (10/(24*60*60))))*(24*60*60)) <= 10) THEN 
        v_idEstadoContext := 2;
    ELSE
       v_idEstadoContext := 3;

    END IF;
    
    RETURN v_idEstadoContext;
    
END;
/