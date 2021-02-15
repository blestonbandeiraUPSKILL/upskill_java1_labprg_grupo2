ALTER TABLE Organizacao
    ADD CONSTRAINT fk_Organizacao_emailGestor
        FOREIGN KEY (emailGestor)
        REFERENCES Utilizador (email);

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
    ADD CONSTRAINT fk_Tarefa_nifOrganizacao
        FOREIGN KEY (nifOrganizacao)
        REFERENCES Organizacao(nif);

ALTER TABLE Tarefa
    ADD CONSTRAINT fk_Tarefa_idCategoria
        FOREIGN KEY (idCategoria)
        REFERENCES Categoria(idCategoria);
        
ALTER TABLE Tarefa
    ADD CONSTRAINT fk_Tarefa_emailColaborador
        FOREIGN KEY (emailColaborador)
        REFERENCES Colaborador (email);
        
ALTER TABLE Candidatura
    ADD CONSTRAINT fk_Candidatura_idAnuncio
        FOREIGN KEY (idAnuncio)
        REFERENCES Anuncio(idAnuncio);

ALTER TABLE Candidatura
    ADD CONSTRAINT fk_Candidatura_idProcessoSeriacao
        FOREIGN KEY (idProcessoSeriacao)
        REFERENCES ProcessoSeriacao(idProcessoSeriacao);

ALTER TABLE Candidatura
    ADD CONSTRAINT fk_Candidatura_emailFreelancer
        FOREIGN KEY (emailFreelancer)
        REFERENCES Freelancer(email);

ALTER TABLE ReconhecimentoGP
    ADD CONSTRAINT fk_ReconhecimentoGP_codigoCompetenciaTecnica
        FOREIGN KEY (codigoCompetenciaTecnica)
        REFERENCES CompetenciaTecnica(codigoCompetenciaTecnica);

ALTER TABLE GrauProficiencia
    ADD CONSTRAINT fk_GrauProficiencia_codigoCompetenciaTecnica
        FOREIGN KEY (codigoCompetenciaTecnica)
        REFERENCES CompetenciaTecnica(codigoCompetenciaTecnica);
        
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
    ADD CONSTRAINT fk_CompetenciaTecnica_idCaracterCT
        FOREIGN KEY (idCaracterCT)
        REFERENCES CaracterCT(idCaracterCT);

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

ALTER TABLE Tarefa
    ADD CONSTRAINT pk_Tarefa
        PRIMARY KEY (referencia, nifOrganizacao);
