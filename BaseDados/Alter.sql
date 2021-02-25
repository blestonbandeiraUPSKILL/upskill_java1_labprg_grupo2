--ALTER TABLE Organizacao
--    ADD CONSTRAINT fk_Organizacao_emailGestor
--        FOREIGN KEY (emailGestor)
--        REFERENCES Utilizador (email);

--ALTER TABLE Organizacao
--    DROP CONSTRAINT fk_Organizacao_emailGestor;

ALTER TABLE Organizacao
    ADD CONSTRAINT fk_Organizacao_idEnderecoPostal
        FOREIGN KEY (idEnderecoPostal)
        REFERENCES EnderecoPostal (idEnderecoPostal);
        
ALTER TABLE Colaborador
    ADD CONSTRAINT fk_Colaborador_emailColaborador
        FOREIGN KEY (email)
        REFERENCES Utilizador(email);

ALTER TABLE Colaborador
    ADD CONSTRAINT fk_Colaborador_nifOrganizacao
        FOREIGN KEY (nifOrganizacao)
        REFERENCES Organizacao(nif);

ALTER TABLE Administrativo
    ADD CONSTRAINT fk_Administrativo_emailAdministrativo
        FOREIGN KEY (email)
        REFERENCES Utilizador(email);

ALTER TABLE Freelancer
    ADD CONSTRAINT fk_Freelancer_emailFreelancer
        FOREIGN KEY (email)
        REFERENCES Utilizador(email);

ALTER TABLE Freelancer
    ADD CONSTRAINT fk_Freelancer_idEnderecoPostal
        FOREIGN KEY (idEnderecoPostal)
        REFERENCES EnderecoPostal(idEnderecoPostal);

ALTER TABLE Tarefa
    ADD CONSTRAINT pk_Tarefa
        PRIMARY KEY (referencia, nifOrganizacao);
        
ALTER TABLE Tarefa
    ADD CONSTRAINT fk_Tarefa_nifOrganizacao
        FOREIGN KEY (nifOrganizacao)
        REFERENCES Organizacao(nif);

ALTER TABLE Tarefa
    ADD CONSTRAINT fk_Tarefa_codigoCategoria
        FOREIGN KEY (codigoCategoria)
        REFERENCES Categoria(codigoCategoria);
        
ALTER TABLE Tarefa
    ADD CONSTRAINT fk_Tarefa_emailColaborador
        FOREIGN KEY (emailColaborador)
        REFERENCES Colaborador (email);

ALTER TABLE Anuncio
    ADD CONSTRAINT fk_Anuncio_nifOrganizacao
        FOREIGN KEY (nifOrganizacao, referenciaTarefa)
        REFERENCES Tarefa(nifOrganizacao, referencia);
        
ALTER TABLE Anuncio
    ADD CONSTRAINT fk_Anuncio_idProcessoSeriacao
        FOREIGN KEY (idProcessoSeriacao)
        REFERENCES ProcessoSeriacao(idProcessoSeriacao);

ALTER TABLE Candidatura
    ADD CONSTRAINT fk_Candidatura_idAnuncio
        FOREIGN KEY (idAnuncio)
        REFERENCES Anuncio(idAnuncio);


ALTER TABLE Candidatura
    ADD CONSTRAINT fk_Candidatura_emailFreelancer
        FOREIGN KEY (emailFreelancer)
        REFERENCES Freelancer(email);

ALTER TABLE ReconhecimentoGP
    ADD CONSTRAINT pk_ReconhecimentoGP
        PRIMARY KEY (idGrauProficiencia, emailFreelancer);
        
ALTER TABLE ReconhecimentoGP
    ADD CONSTRAINT fk_ReconhecimentoGP_idGrauProficiencia
        FOREIGN KEY (idGrauProficiencia)
        REFERENCES GrauProficiencia(idGrauProficiencia);

ALTER TABLE ReconhecimentoGP
    ADD CONSTRAINT fk_ReconhecimentoGP_emailFreelancer
        FOREIGN KEY (emailFreelancer)
        REFERENCES Freelancer(email);

ALTER TABLE GrauProficiencia
    ADD CONSTRAINT fk_GrauProficiencia_codigoCompetenciaTecnica
        FOREIGN KEY (codigoCompetenciaTecnica)
        REFERENCES CompetenciaTecnica(codigoCompetenciaTecnica);
        
ALTER TABLE FreelancerHabAcademica
    ADD CONSTRAINT pk_FreelancerHabAcademica
        PRIMARY KEY(emailFreelancer, idHabilitacaoAcademica);
        
ALTER TABLE FreelancerHabAcademica
    ADD CONSTRAINT fk_FreelancerHabAcademica_emailFreelancer
        FOREIGN KEY (emailFreelancer)
        REFERENCES Freelancer(email);

ALTER TABLE FreelancerHabAcademica
    ADD CONSTRAINT fk_FreelancerHabAcademica_idHabilitacaoAcademica
        FOREIGN KEY (idHabilitacaoAcademica)
        REFERENCES HabilitacaoAcademica(idHabilitacaoAcademica);

ALTER TABLE Categoria
    ADD CONSTRAINT fk_Categoria_codigoAreaActividade
        FOREIGN KEY (codigoAreaActividade)
        REFERENCES AreaActividade(codigoAreaActividade);

--ALTER TABLE CompetenciaTecnica
--    ADD CONSTRAINT fk_CompetenciaTecnica_idCaracterCT
--        FOREIGN KEY (idCaracterCT)
--        REFERENCES CaracterCT(idCaracterCT);

ALTER TABLE CompetenciaTecnica
    ADD CONSTRAINT fk_CompetenciaTecnica_codigoAreaActividade
        FOREIGN KEY (codigoAreaActividade)
        REFERENCES AreaActividade(codigoAreaActividade);

ALTER TABLE ProcessoSeriacao
    ADD CONSTRAINT fk_ProcessoSeriacao_idTipoRegimento
        FOREIGN KEY (idTipoRegimento)
        REFERENCES TipoRegimento(idTipoRegimento);

ALTER TABLE Classificacao
    ADD CONSTRAINT fk_Classificacao_idProcessoSeriacao
        FOREIGN KEY (idProcessoSeriacao)
        REFERENCES ProcessoSeriacao(idProcessoSeriacao);

ALTER TABLE CaracterCT
    ADD CONSTRAINT fk_CaracterCT_GRAUPROFMINIMO
        FOREIGN KEY (GRAUPROFMINIMO)
        REFERENCES GrauProficiencia(idGrauProficiencia);

--ALTER TABLE CompetenciaTecnica
--    DROP COLUMN idCaracterCT;

--ALTER TABLE GrauProficiencia
--    drop column valor;

--ALTER TABLE GrauProficiencia
--    ADD grau varchar(10);

ALTER TABLE GrauProficiencia
    drop constraint uk_GrauProficiencia_grau;

ALTER TABLE Categoria
    drop constraint fk_Categoria_idCaracterCT;
    
ALTER TABLE GrauProficiencia
    ADD CONSTRAINT uk_GrauProficiencia_grau_codigoCompetenciaTecnica 
        UNIQUE (grau, codigoCompetenciaTecnica);

--ALTER TABLE CaracterCT
--    ADD codigoCategoria varchar(15);

ALTER TABLE CaracterCT
    ADD CONSTRAINT fk_CaracterCT_codigoCategoria
        FOREIGN KEY (codigoCategoria)
        REFERENCES Categoria(codigoCategoria);


ALTER TABLE Tarefa
    MODIFY emailColaborador varchar(50);

ALTER TABLE HabilitacaoAcademica
    MODIFY grau varchar(50);

ALTER TABLE HabilitacaoAcademica
    MODIFY mediaCurso numeric;

ALTER TABLE Tarefa
    MODIFY custoEstimado numeric;

