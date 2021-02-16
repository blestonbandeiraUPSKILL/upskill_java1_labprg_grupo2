CREATE OR REPLACE PROCEDURE createOrganizacao(
    p_nif organizacao.nif%type,
    p_nome organizacao.nome%type,
    p_website organizacao.website%type,
    p_telefone organizacao.telefone%type,
    p_email organizacao.email%type,
    p_emailGestor organizacao.emailGestor%ype,
    p_arruamento enderecopostal.arruamento%type,
    p_numeroPorta enderecopostal.numeroporta%type,
    p_localidade enderecopostal.localidade%type,
    p_codPostal enderecopostal.codpostal%type,
    p_nomeGestor utilizador.nome%type,
    p_emailGestor utilizador.email%type,
    p_password utilizador.password&type,
    p_rolneame utilizador.rolename%type,
    p_telefoneGestor colaborador.telefone%type,
    p_funcao colaborador.funcao%type
    )
is
    v_idEnderecoPostal enderecoPostal.idEnderecoPostal%type;
    v_nif organizacao.nif%type;
    v_emailGestor utilizador.email%type;
    ex_organizacao exception;

BEGIN
    INSERT INTO EnderecoPostal
        (arruamento, numeroPorta, localidade, codPostal)
    VALUES
        (p_arruamento, p_numeroPorta, p_localidade, p_codPostal)
    RETURNING idEnderecoPostal 
    INTO v_idEnderecoPostal;    
    
    INSERT INTO Utilizador
        (email, nome, password, rolename)
    VALUES
        (emailGestor, nomeGestor, password, 'gestor')
    RETURNING emailGestor
    INTO v_emailGestor;

    INSERT INTO Organizacao 
        (nif, nome, website, telefone, email, v_emailGestor, v_idEnderecoPostal)
    VALUES 
        (p_nif, p_nome, p_website, p_telefone, p_email, p_emailGestor)
    RETURNING nif
    INTO v_nif;
    
    INSERT INTO Colaborador
        (email, funcao, telefone, nifOrganizacao)
    VALUES
        (v_emailGestor, p_funcao, p_telefoneGestor, v_nif);
END;
/
    
 
 
   