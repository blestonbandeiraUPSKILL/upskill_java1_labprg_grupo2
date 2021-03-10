CREATE OR REPLACE PROCEDURE createRolename(
    p_designacao Rolename.designacao%type
    )

IS

BEGIN

    INSERT INTO Rolename
        (designacao)
    VALUES
        (p_designacao);

END;