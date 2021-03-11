CREATE OR REPLACE PROCEDURE createSessao(
    p_idAppContext UserSession.idAppContext%type,
    p_idRolename UserSession.idRolename%type,
    p_emailUtilizador UserSession.emailUtilizador%type
    )

IS
   
BEGIN

    INSERT INTO UserSession
        (idAppContext, idRolename, emailUtilizador)
    VALUES
        (p_idAppContext, p_idRolename, p_emailUtilizador);

END;
/