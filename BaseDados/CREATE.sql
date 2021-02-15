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

CREATE TABLE Colaborador(
    email varchar(20) 
        CONSTRAINT pk_email_Colaborador PRIMARY KEY,
    funcao varchar(20),
    telefone varchar(9),
    nifOrganizacao varchar(9)
);

CREATE TABLE Administrativo(
    email varchar(20)
        CONSTRAINT pk_email_Administrativo PRIMARY KEY
);

CREATE TABLE Freelancer(
    email varchar(20)
        CONSTRAINT pk_email_Freelancer PRIMARY KEY,
    telefone varchar(9),
    nif varchar(9),
    idEnderecoPostal varchar(20)
);

CREATE TABLE EnderecoPostal(
    idEnderecoPostal varchar(20)
        CONSTRAINT pk_id_EnderecoPostal PRIMARY KEY,
    arruamento varchar(50),
    numeroPorta varchar(20),
    localidade varchar(20),
    codPostal varchar(8)
);

CREATE TABLE Utilizador(
    email varchar(20)
        CONSTRAINT pk_email_Utilizador PRIMARY KEY,
    nome varchar(100),
    password varchar(10)
);

CREATE TABLE Tarefa(
    referencia varchar(10),
    nifOrganizacao varchar(9),
    designacao varchar(50),
    descInformal varchar(50),
    descTecnica varchar(250),
    duracaoEstimada integer,
    custoEstimado float(2),
    idCategoria varchar(20),
    emailColaborador varchar(20)    
);

CREATE TABLE Anuncio(
    idAnuncio varchar(20)
        CONSTRAINT pk_id_Anuncio PRIMARY KEY,
    dataInicioPublicacao date,
    dataFimPublicacao date,
    dataInicioCandidatura date,
    dataFimCandidatura date,
    dataInicioSeriacao date,
    dataFimSeriacao date
);

CREATE TABLE Candidatura(
    idCandidatura varchar(20)
        CONSTRAINT pk_id_Candidatura PRIMARY KEY,
    dataFimCandidatura date,
    valorPretendido float(2),
    numeroDias numeric,
    txtApresentacao varchar(250),
    txtMotivacao varchar(250),
    idAnuncio varchar(20),
    idProcessoSeriacao varchar(20),
    emailFreelancer  varchar(20)
);

CREATE TABLE ReconhecimentoGP(
    idReconhecimentoGP varchar(20)
        CONSTRAINT pk_id_ReconhecimentoGP PRIMARY KEY,
    dataReconhecimento date,
    codigoCompetenciaTecnica varchar(20)
);

CREATE TABLE GrauProficiencia(
    idGrauProficiencia varchar(20)
        CONSTRAINT pk_id_GrauProficiencia PRIMARY KEY,
    valor integer,
    designacao varchar(100),
    codigoCompetenciaTecnica varchar(20)
);

CREATE TABLE FreelancerHabAcademica(
    emailFreelancer varchar(20),
    idHabilitacaoAcademica varchar(20)
);

CREATE TABLE HabilitacaoAcademica(
    idHabilitacaoAcademica varchar(20)
        CONSTRAINT pk_id_HabilitacaoAcademica PRIMARY KEY,
    grau varchar(10),
    designacaoCurso varchar(50),
    nomeInstituicao varchar (50),
    mediaCurso float(1)
);

CREATE TABLE Categoria(
    idCategoria varchar(20)
        CONSTRAINT pk_id_Categoria PRIMARY KEY,
    descBreve varchar(50),
    descDetalhada varchar(100),
    codigoAreaActividade varchar(20)
);

CREATE TABLE AreaActividade(
    codigoAreaActividade varchar(20)
        CONSTRAINT pk_codigo_AreaActividade PRIMARY KEY,
    descBreve varchar(50),
    descDetalhada varchar(100)
);

CREATE TABLE CompetenciaTecnica(
    codigoCompetenciaTecnica varchar(20)
        CONSTRAINT pk_codigo_CompetenciaTecnica PRIMARY KEY,
    descBreve varchar(50),
    descDetalhada varchar(100),
    idCaracterCT varchar(20),
    codigoAreaActividade varchar(20)
);

CREATE TABLE CaracterCT(
    idCaracterCT varchar(20)
        CONSTRAINT pk_id_CaracterCT PRIMARY KEY,
    obrigatoria varchar(1),
    grauProfMinimo varchar(20)
);

CREATE TABLE ProcessoSeriacao(
    idProcessoSeriacao varchar(20)
        CONSTRAINT pk_id_ProcessoSeriacao PRIMARY KEY,
    dataRealizacao date,
    idTipoRegimento varchar(20)
);

CREATE TABLE TipoRegimento(
    idTipoRegimento varchar(20)
        CONSTRAINT pk_id_TipoRegimento PRIMARY KEY,
    designacao varchar(100),
    descricaoRegras varchar(250)
);

CREATE TABLE Classificacao(
    idClassificacao varchar(20)
        CONSTRAINT pk_id_Classificacao PRIMARY KEY,
    lugar integer,
    idProcessoSeriacao varchar(20)
);


