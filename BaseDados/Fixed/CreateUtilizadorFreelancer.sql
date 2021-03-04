CREATE OR REPLACE PROCEDURE createFreelancer(
    p_email utilizador.email%type,
    p_nome utilizador.nome%type,
    p_password utilizador.password%type,
    p_rolename rolename.designacao%type,
    p_telefone freelancer.telefone%type,
    p_nif freelancer.nif%type,
    p_arruamento enderecopostal.arruamento%type,
    p_numeroPorta enderecopostal.numeroporta%type,
    p_localidade enderecopostal.localidade%type,
    p_codPostal enderecopostal.codpostal%type
    )
IS
    v_email utilizador.email%type;
    v_idEnderecoPostal enderecopostal.idenderecopostal%type;
    v_rolename Rolename.idRolename%type;
    
BEGIN

    SELECT idRolename INTO v_rolename
    FROM Rolename
    WHERE designacao LIKE p_rolename;

    INSERT INTO EnderecoPostal
        (arruamento, numeroPorta, localidade, codPostal)
    VALUES
        (p_arruamento, p_numeroPorta, p_localidade, p_codPostal)
    RETURNING idEnderecoPostal
    INTO v_idEnderecoPostal;

    INSERT INTO Utilizador
        (email, nome, password, idRolename)
    VALUES
        (p_email, p_nome, p_password, v_rolename)
    RETURNING p_email
    INTO v_email;
    
    INSERT INTO Freelancer
        (email, telefone, nif, idenderecopostal)
    VALUES
        (v_email, p_telefone, p_nif, v_idEnderecoPostal);
END;
/