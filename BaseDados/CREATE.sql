CREATE TABLE Organizacao (
    nif varchar(9) 
        CONSTRAINT pk_Organizacao_nif PRIMARY KEY,
    nome varchar(20) 
        CONSTRAINT nn_Organizacao_nome NOT NULL,
    website varchar(50),
    telefone varchar (9)
        CONSTRAINT nn_Organizacao_telefone NOT NULL,
    email varchar(50)
        CONSTRAINT nn_Organizacao_email NOT NULL
        CONSTRAINT uk_Organizacao_email UNIQUE,
    emailGestor varchar(50)
        CONSTRAINT nn_Organizacao_emailGestor NOT NULL,
    idEnderecoPostal integer    
);

CREATE TABLE Colaborador(
    email varchar(50) 
        CONSTRAINT pk_Colaborador_email PRIMARY KEY,
    funcao varchar(50),
    telefone varchar(9),
    nifOrganizacao varchar(9)
);

CREATE TABLE Administrativo(
    email varchar(50)
        CONSTRAINT pk_Administrativo_email PRIMARY KEY
);

CREATE TABLE Freelancer(
    email varchar(50)
        CONSTRAINT pk_Freelancer_email PRIMARY KEY,
    telefone varchar(9),
    nif varchar(9),
    idEnderecoPostal integer
);

CREATE TABLE EnderecoPostal(
    idEnderecoPostal integer GENERATED AS IDENTITY
        CONSTRAINT pk_EnderecoPostal_idEnderecoPostal PRIMARY KEY,
    arruamento varchar(50),
    numeroPorta varchar(20),
    localidade varchar(20),
    codPostal varchar(8)
        CONSTRAINT nn_EnderecoPostal_codigoPostal NOT NULL
);

CREATE TABLE Utilizador(
    email varchar(50)
        CONSTRAINT pk_Utilizador_email PRIMARY KEY,
    nome varchar(100)
        CONSTRAINT nn_Utilizador_nome NOT NULL,
    password varchar(10)
        CONSTRAINT nn_Utilizador_password NOT NULL,
    rolename varchar(15)
        CONSTRAINT nn_Utilizador_rolename NOT NULL
);

CREATE TABLE Tarefa(   
    nifOrganizacao varchar(9),
    referencia varchar(50),
    designacao varchar(50)
        CONSTRAINT nn_Tarefa_designacao NOT NULL,
    descInformal varchar(50),
    descTecnica varchar(250),
    duracaoEstimada integer
        CONSTRAINT nn_Tarefa_duracaoEstimada NOT NULL,
    custoEstimado float(2)
        CONSTRAINT nn_Tarefa_custoEstimado NOT NULL,
    idCategoria integer,
    emailColaborador varchar(20)    
);

CREATE TABLE Anuncio(
    idAnuncio integer GENERATED AS IDENTITY
        CONSTRAINT pk_Anuncio_idAnuncio PRIMARY KEY,
    referenciaTarefa varchar(50),
    nifOrganizacao varchar(9),
    dataInicioPublicacao date
        CONSTRAINT nn_Anuncio_dataInicioPublicacao NOT NULL,
    dataFimPublicacao date
        CONSTRAINT nn_Anuncio_dataFimPublicacao NOT NULL,
    dataInicioCandidatura date
        CONSTRAINT nn_Anuncio_dataInicioCandidatura NOT NULL,
    dataFimCandidatura date
        CONSTRAINT nn_Anuncio_dataFimCandidatura NOT NULL,
    dataInicioSeriacao date
        CONSTRAINT nn_Anuncio_dataInicioSeriacao NOT NULL,
    dataFimSeriacao date
        CONSTRAINT nn_Anuncio_dataFimSeriacao NOT NULL,
    idProcessoSeriacao integer
);

CREATE TABLE Candidatura(
    idCandidatura integer GENERATED AS IDENTITY
        CONSTRAINT pk_Candidatura_idCandidatura PRIMARY KEY,
    dataFimCandidatura date
        CONSTRAINT nn_Candidatura_dataFimCandidatura NOT NULL,
    valorPretendido float(2)
        CONSTRAINT nn_Candidatura_valorPretendido NOT NULL,
    numeroDias integer
        CONSTRAINT nn_Candidatura_numeroDias NOT NULL,
    txtApresentacao varchar(250),
    txtMotivacao varchar(250),
    idAnuncio integer,
    emailFreelancer  varchar(20)
);

CREATE TABLE ReconhecimentoGP(
    idGrauProficiencia integer,
    emailFreelancer varchar(50),
    dataReconhecimento date
        CONSTRAINT nn_ReconhecimentoGP_dataReconhecimento NOT NULL
);

CREATE TABLE GrauProficiencia(
    idGrauProficiencia integer GENERATED AS IDENTITY
        CONSTRAINT pk_GrauProficiencia_idGrauProficiencia PRIMARY KEY,
    valor integer
        CONSTRAINT nn_GrauProficiencia_valor NOT NULL
        CONSTRAINT uk_GrauProficiencia_valor UNIQUE,
    designacao varchar(100)
        CONSTRAINT nn_GrauProficiencia_designacao NOT NULL,
    codigoCompetenciaTecnica varchar(15)
);

CREATE TABLE FreelancerHabAcademica(
    emailFreelancer varchar(50),
    idHabilitacaoAcademica integer
);

CREATE TABLE HabilitacaoAcademica(
    idHabilitacaoAcademica integer GENERATED AS IDENTITY
        CONSTRAINT pk_HabilitacaoAcademica_idHabilitacaoAcademica PRIMARY KEY,
    grau varchar(10)
        CONSTRAINT nn_HabilitacaoAcademica_grau NOT NULL,
    designacaoCurso varchar(50)
        CONSTRAINT nn_HabilitacaoAcademica_designacaoCurso NOT NULL,
    nomeInstituicao varchar (50)
        CONSTRAINT nn_HabilitacaoAcademica_nomeInstituicao NOT NULL,
    mediaCurso float(1)
        CONSTRAINT nn_HabilitacaoAcademica_mediaCurso NOT NULL
);

CREATE TABLE Categoria(
    idCategoria integer GENERATED AS IDENTITY
        CONSTRAINT pk_Categoria_idCategoria PRIMARY KEY,
    descBreve varchar(50)
        CONSTRAINT nn_Categoria_descBreve NOT NULL
        CONSTRAINT uk_Categoria_descBreve UNIQUE,
    descDetalhada varchar(250),
    codigoAreaActividade varchar(15)
);

CREATE TABLE AreaActividade(
    codigoAreaActividade varchar(15)
        CONSTRAINT pk_AreaActividadecodigo PRIMARY KEY,
    descBreve varchar(50)
        CONSTRAINT nn_AreaActividade_descBreve NOT NULL
        CONSTRAINT uk_AreaActividade_descBreve UNIQUE,
    descDetalhada varchar(250)
);

CREATE TABLE CompetenciaTecnica(
    codigoCompetenciaTecnica varchar(15)
        CONSTRAINT pk_CompetenciaTecnica_codigo PRIMARY KEY,
    descBreve varchar(50)
        CONSTRAINT nn_CompetenciaTecnica_descBreve NOT NULL
        CONSTRAINT uk_CompetenciaTecnica_descBreve UNIQUE,
    descDetalhada varchar(100),
    idCaracterCT varchar(15),
    codigoAreaActividade varchar(15)
);

CREATE TABLE CaracterCT(
    idCaracterCT varchar(15)
        CONSTRAINT pk_CaracterCT_idCaracterCT PRIMARY KEY,
    obrigatoria varchar(20)
        CONSTRAINT nn_CaracterCT_obrigatoria NOT NULL,
    grauProfMinimo integer
        CONSTRAINT nn_CaracterCT_grauProfMinimo NOT NULL
);

CREATE TABLE ProcessoSeriacao(
    idProcessoSeriacao integer GENERATED AS IDENTITY
        CONSTRAINT pk_ProcessoSeriacao_idProcessoSeriacao PRIMARY KEY,
    dataRealizacao date
        CONSTRAINT nn_ProcessoSeriacao_dataRealizacao NOT NULL,
    idTipoRegimento integer
);

CREATE TABLE TipoRegimento(
    idTipoRegimento integer GENERATED AS IDENTITY
        CONSTRAINT pk_TipoRegimento_idTipoRegimento PRIMARY KEY,
    designacao varchar(100)
        CONSTRAINT nn_TipoRegimento_designacao NOT NULL
        CONSTRAINT uk_TipoRegimento_designacao UNIQUE,
    descricaoRegras varchar(250)
        CONSTRAINT nn_TipoRegimento_descricaoRegras NOT NULL
);

CREATE TABLE Classificacao(
    idClassificacao integer GENERATED AS IDENTITY
        CONSTRAINT pk_Classificacao_idClassificacao PRIMARY KEY,
    lugar integer
        CONSTRAINT nn_Classificacao_lugar NOT NULL
        CONSTRAINT uk_Classificao_lugar UNIQUE,
    idProcessoSeriacao integer
);


