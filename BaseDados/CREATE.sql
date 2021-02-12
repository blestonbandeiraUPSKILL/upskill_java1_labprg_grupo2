CREATE TABLE Organizacao (
    nif varchar(9) 
        CONSTRAINT pk_nif_Organizacao PRIMARY KEY,
    nome varchar(20) 
        CONSTRAINT nn_nome_Organizacao NOT NULL,
    website varchar(50),
    telefone varchar (9)
        CONSTRAINT nn_telefone_Organizacao NOT NULL,
    email varchar(20)
        CONSTRAINT nn_email_Organizacao NOT NULL
        CONSTRAINT uk_email_Organizacao UNIQUE,
    emailGestor varchar(20)
        CONSTRAINT nn_emailGestor_Organizacao NOT NULL,
    idEnderecoPostal varchar(20)    
);