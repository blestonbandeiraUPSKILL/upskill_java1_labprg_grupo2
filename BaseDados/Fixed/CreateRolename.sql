CREATE OR REPLACE PROCEDURE createRolename(
    p_designacao Rolename.designacao%type,
    p_descricao Rolename.descricao%type
    )

IS

BEGIN

    INSERT INTO Rolename
        (designacao, descricao)
    VALUES
        (p_designacao, p_descricao);

END;