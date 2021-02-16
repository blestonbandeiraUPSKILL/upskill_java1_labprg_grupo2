CREATE OR REPLACE PROCEDURE createUtilizadorFreelancer(
    p_email utilizador.email%type,
    p_nome utilizador.nome%type,
    p_password utilizador.password%type,
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
    
BEGIN

    createEnderecoPostal(p_arruamento, p_numeroPorta, p_localidade, p_codPostal);

    INSERT INTO Utilizador
        (email, nome, password, rolename)
    VALUES
        (p_email, p_nome, p_password, 'freelancer')
    RETURNING p_email
    INTO v_email;
    
    INSERT INTO Freelancer
        (email, telefone, nif, idenderecopostal)
    VALUES
        (p_email, p_telefone, p_nif, v_idEnderecoPostal);
END;
/