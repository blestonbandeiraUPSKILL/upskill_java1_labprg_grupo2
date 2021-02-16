CREATE OR REPLACE PROCEDURE createOrganizacao(
    p_nif organizacao.nif%type,
    p_nome organizacao.nome%type,
    p_website organizacao.website%type,
    p_telefone organizacao.telefone%type,
    p_email organizacao.email%type,
    p_emailGestor organizacao.emailGestor%type,
    p_arruamento enderecopostal.arruamento%type,
    p_numeroPorta enderecopostal.numeroporta%type,
    p_localidade enderecopostal.localidade%type,
    p_codPostal enderecopostal.codpostal%type,
    p_nomeGestor utilizador.nome%type,
    p_password utilizador.password%type,
    p_rolename utilizador.rolename%type,
    p_telefoneGestor colaborador.telefone%type,
    p_funcao colaborador.funcao%type
    )
is
    v_idEnderecoPostal enderecoPostal.idEnderecoPostal%type;
    v_nif organizacao.nif%type;
    
BEGIN
   
   createEnderecoPostal(p_arruamento, p_numeroPorta, p_localidade, p_codPostal);

    INSERT INTO Organizacao 
        (nif, nome, website, 
        telefone, email, 
        emailGestor, idEnderecoPostal)
    VALUES 
        (p_nif, p_nome, p_website, 
        p_telefone, p_email, 
        p_emailGestor, v_idenderecopostal)
    RETURNING nif
    INTO v_nif;
    
    createUtilizadorGestor(p_emailGestor, p_nomeGestor, p_password, p_telefoneGestor,
    p_funcao, v_nif);
    
END;
/



