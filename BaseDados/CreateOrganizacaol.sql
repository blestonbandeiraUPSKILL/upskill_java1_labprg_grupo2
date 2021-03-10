CREATE OR REPLACE PROCEDURE createOrganizacao(
    p_arruamento enderecopostal.arruamento%type,
    p_numeroPorta enderecopostal.numeroporta%type,
    p_localidade enderecopostal.localidade%type,
    p_codPostal enderecopostal.codpostal%type,
   
    p_nif organizacao.nif%type,
    p_nome organizacao.nome%type,
    p_website organizacao.website%type,
    p_telefone organizacao.telefone%type,
    p_email organizacao.email%type,
    
    p_emailGestor organizacao.emailGestor%type,
    p_funcao colaborador.funcao%type,
    p_telefoneGestor colaborador.telefone%type,
    p_rolename rolename.designacao%type
    )
is
    v_idEnderecoPostal enderecoPostal.idEnderecoPostal%type;
    v_nif organizacao.nif%type;
    
BEGIN
    
    INSERT INTO EnderecoPostal
        (arruamento, numeroPorta, localidade, codPostal)
    VALUES
        (p_arruamento, p_numeroPorta, p_localidade, p_codPostal)
    RETURNING idEnderecoPostal
    INTO v_idEnderecoPostal;
        
    INSERT INTO Organizacao 
        (nif, nome, website, 
        telefone, email, 
        emailGestor, idEnderecoPostal)
    VALUES 
        (p_nif, p_nome, p_website, 
        p_telefone, p_email, 
        p_emailGestor, v_idEnderecoPostal);
   
    createUtilizadorGestor(p_emailGestor, p_funcao, 
    p_telefoneGestor, p_nif, p_rolename);
    
    
END;
/



