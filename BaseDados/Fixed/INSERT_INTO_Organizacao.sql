INSERT INTO Organizacao
    (nif, nome, website, telefone, email, emailGestor, idEnderecoPostal)
VALUES
    ('111222666', 'Marcelo Advogados', 'http://www.marcelo.advogados.pt', '222333777', 'marcelo@marceloadvogados.pt', 'jose@marceloadvogados.pt', 1);
    
INSERT INTO Organizacao
    (nif, nome, website, telefone, email, emailGestor, idEnderecoPostal)
VALUES
    ('111222888', 'Inês Engenharia', 'http://www.inesengenharia.com', '222333999', 'ines@inesengenharia.com', 'sofia@inesengenharia.com', 2);

commit; 

select * from organizacao;
    