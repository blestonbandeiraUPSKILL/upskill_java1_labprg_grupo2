INSERT INTO Tarefa
    (nifOrganizacao, referencia, designacao, descInformal, descTecnica, duracaoEstimada, custoEstimado, codigoCategoria, emailColaborador)
VALUES
    ('111222666', 'T_01_01', 'Projecto de Sala de Reuni�o', 'Projecto de Sala de Reuni�o Anexa', 'Projecto de Sala de Reuni�o Anexa para 30 pessoas', 20, 20000, 'AC-003_02', 'tomas@marceloadvogados.pt');
    
INSERT INTO Tarefa
    (nifOrganizacao, referencia, designacao, descInformal, descTecnica, duracaoEstimada, custoEstimado, codigoCategoria, emailColaborador)
VALUES
    ('111222666', 'T_01_02', 'Constru��o de Sala de Reuni�o', 'Constru��o de Sala de Reuni�o Anexa', 'Constru��o de Sala de Reuni�o Anexa para 30 pessoas', 60, 250000, 'AC-003_01', 'tomas@marceloadvogados.pt');
    
INSERT INTO Tarefa
    (nifOrganizacao, referencia, designacao, descInformal, descTecnica, duracaoEstimada, custoEstimado, codigoCategoria, emailColaborador)
VALUES
    ('111222666', 'T_01_03', 'Limpeza P�s-Obra', 'Limpeza de Sede da Empresa', 'Limpeza de Sede da Empresa P�s-obra', 2, 1000, 'AC-002_01', 'francisca@marceloadvogados.pt');
    
INSERT INTO Tarefa
    (nifOrganizacao, referencia, designacao, descInformal, descTecnica, duracaoEstimada, custoEstimado, codigoCategoria, emailColaborador)
VALUES
    ('111222888', 'T_02_01', 'Limpeza de Escrit�rio', 'Limpeza de Escrit�rio da Empresa', 'Limpeza de Escrit�rio da Empresa para evento', 2, 2000, 'AC-002_01', 'matilde@inesengenharia.com');
    
INSERT INTO Tarefa
    (nifOrganizacao, referencia, designacao, descInformal, descTecnica, duracaoEstimada, custoEstimado, codigoCategoria, emailColaborador)
VALUES
    ('111222888', 'T_02_02', 'Remodela��o de Website', 'Remodela��o de Website da Empresa', 'Remodela��o de Website da Empresa com nova paleta de cores', 10, 5000, 'AC-001_01', 'eduardo@inesengenharia.com');
    


INSERT INTO Tarefa
    (nifOrganizacao, referencia, designacao, descInformal, descTecnica, duracaoEstimada, custoEstimado, codigoCategoria, emailColaborador)
VALUES
    ('111222666', 'T_01_04', 'Instala��o de Ar-Condicionado', 'Instala��o de AC em Empresa', 'Instala��o de AC em Sala de Reuni�o nova', 5, 1000, 'AC-006_03', 'francisca@marceloadvogados.pt');


SELECT * FROM Tarefa;

commit;