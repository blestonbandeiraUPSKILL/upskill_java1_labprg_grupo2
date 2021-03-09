ALTER TABLE Utilizador
    ADD CONSTRAINT fk_Utilizador_idRolename
        FOREIGN KEY (idRolename)
        REFERENCES Rolename(idRolename);
        
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
        PRIMARY KEY (nifOrganizacao, referencia);
        
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

ALTER TABLE CompetenciaTecnica
    ADD CONSTRAINT fk_CompetenciaTecnica_codigoAreaActividade
        FOREIGN KEY (codigoAreaActividade)
        REFERENCES AreaActividade(codigoAreaActividade);

ALTER TABLE ProcessoSeriacao
    ADD CONSTRAINT fk_ProcessoSeriacao_idAnuncio
        FOREIGN KEY (idAnuncio)
        REFERENCES Anuncio(idAnuncio);

ALTER TABLE Classificacao
    ADD CONSTRAINT fk_Classificacao_idProcessoSeriacao
        FOREIGN KEY (idProcessoSeriacao)
        REFERENCES ProcessoSeriacao(idProcessoSeriacao);

ALTER TABLE Classificacao
    ADD CONSTRAINT fk_Classificacao_idCandidatura
        FOREIGN KEY (idCandidatura)
        REFERENCES Candidatura(idCandidatura);

ALTER TABLE CaracterCT
    ADD CONSTRAINT fk_CaracterCT_GRAUPROFMINIMO
        FOREIGN KEY (GRAUPROFMINIMO)
        REFERENCES GrauProficiencia(idGrauProficiencia);

ALTER TABLE GrauProficiencia
    ADD CONSTRAINT uk_GrauProficiencia_grau_codigoCompetenciaTecnica 
        UNIQUE (grau, codigoCompetenciaTecnica);

ALTER TABLE CaracterCT
    ADD CONSTRAINT fk_CaracterCT_codigoCategoria
        FOREIGN KEY (codigoCategoria)
        REFERENCES Categoria(codigoCategoria);

ALTER TABLE Anuncio
    ADD CONSTRAINT ck_Anuncio_dataInicioCandidatura
    CHECK (dataInicioCandidatura BETWEEN dataInicioPublicitacao AND dataFimPublicitacao);

ALTER TABLE Anuncio
    ADD CONSTRAINT ck_Anuncio_dataFimCandidatura
    CHECK (dataFimCandidatura BETWEEN dataInicioPublicitacao AND dataFimPublicitacao);


ALTER TABLE Anuncio
    ADD CONSTRAINT ck_Anuncio_dataInicioSeriacao
    CHECK (dataInicioSeriacao BETWEEN dataInicioPublicitacao AND dataFimPublicitacao);

ALTER TABLE Anuncio
    ADD CONSTRAINT ck_Anuncio_dataFimSeriacao
    CHECK (dataFimSeriacao BETWEEN dataInicioPublicitacao AND dataFimPublicitacao);
    
ALTER TABLE Anuncio
    ADD CONSTRAINT ck_Anuncio_dataInicioSeriacao_dataFimSeriacao
    CHECK (dataInicioSeriacao < dataFimSeriacao);
    
ALTER TABLE Anuncio
    ADD CONSTRAINT ck_Anuncio_dataInicioPublicitacao_dataFimPublicitacao
    CHECK (dataInicioPublicitacao < dataFimPublicitacao);

ALTER TABLE Anuncio
    ADD CONSTRAINT ck_Anuncio_dataInicioCandidatura_dataFimCandidatura
    CHECK (dataInicioCandidatura < dataFimCandidatura);
    

ALTER TABLE Anuncio
    ADD CONSTRAINT fk_Anuncio_idTipoRegimento
    FOREIGN KEY (idTipoRegimento)
    REFERENCES TipoRegimento(idTipoRegimento);

ALTER TABLE ColaboradorSeriacao
    ADD CONSTRAINT pk_ColaboradorSeriacao
        PRIMARY KEY (emailColaborador, idProcessoSeriacao);
        
ALTER TABLE ColaboradorSeriacao
    ADD CONSTRAINT fk_ColaboradorSeriacao_idProcessoSeriacao
        FOREIGN KEY (idProcessoSeriacao)
        REFERENCES ProcessoSeriacao(idProcessoSeriacao);
        
ALTER TABLE ColaboradorSeriacao
    ADD CONSTRAINT fk_ColaboradorSeriacao_emailColaborador
        FOREIGN KEY (emailColaborador)
        REFERENCES Colaborador(email);

ALTER TABLE UserSession
    ADD CONSTRAINT fk_UserSession_idAppContext
    FOREIGN KEY (idAppContext)
    REFERENCES AppContext(idAppContext);

ALTER TABLE UserSession     
    ADD CONSTRAINT fk_UserSession_emailUtilizador
    FOREIGN KEY (emailUtilizador)
    REFERENCES Utilizador(email);
    