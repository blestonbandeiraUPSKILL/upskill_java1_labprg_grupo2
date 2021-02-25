INSERT INTO Tarefa
    (nifOrganizacao, referencia, designacao, descInformal, descTecnica, duracaoEstimada, custoEstimado, codigoCategoria, emailColaborador)
VALUES
    ('111222666', 'T_01_01', 'Projecto de Sala de Reunião', 'Projecto de Sala de Reunião Anexa', 'Projecto de Sala de Reunião Anexa para 30 pessoas', 20, 20000, 'AC-003_02', 'tomas@marceloadvogados.pt');
    
INSERT INTO Tarefa
    (nifOrganizacao, referencia, designacao, descInformal, descTecnica, duracaoEstimada, custoEstimado, codigoCategoria, emailColaborador)
VALUES
    ('111222666', 'T_01_02', 'Construção de Sala de Reunião', 'Construção de Sala de Reunião Anexa', 'Construção de Sala de Reunião Anexa para 30 pessoas', 60, 250000, 'AC-003_01', 'tomas@marceloadvogados.pt');
    
INSERT INTO Tarefa
    (nifOrganizacao, referencia, designacao, descInformal, descTecnica, duracaoEstimada, custoEstimado, codigoCategoria, emailColaborador)
VALUES
    ('111222666', 'T_01_03', 'Limpeza Pós-Obra', 'Limpeza de Sede da Empresa', 'Limpeza de Sede da Empresa Pós-obra', 2, 1000, 'AC-002_01', 'francisca@marceloadvogados.pt');
    
INSERT INTO Tarefa
    (nifOrganizacao, referencia, designacao, descInformal, descTecnica, duracaoEstimada, custoEstimado, codigoCategoria, emailColaborador)
VALUES
    ('111222888', 'T_02_01', 'Limpeza de Escritório', 'Limpeza de Escritório da Empresa', 'Limpeza de Escritório da Empresa para evento', 2, 2000, 'AC-002_01', 'matilde@inesengenharia.com');
    
INSERT INTO Tarefa
    (nifOrganizacao, referencia, designacao, descInformal, descTecnica, duracaoEstimada, custoEstimado, codigoCategoria, emailColaborador)
VALUES
    ('111222888', 'T_02_02', 'Remodelação de Website', 'Remodelação de Website da Empresa', 'Remodelação de Website da Empresa com nova paleta de cores', 10, 5000, 'AC-001_01', 'eduardo@inesengenharia.com');
    


INSERT INTO Tarefa
    (nifOrganizacao, referencia, designacao, descInformal, descTecnica, duracaoEstimada, custoEstimado, codigoCategoria, emailColaborador)
VALUES
    ('111222666', 'T_01_04', 'Instalação de Ar-Condicionado', 'Instalação de AC em Empresa', 'Instalação de AC em Sala de Reunião nova', 5, 1000, 'AC-006_03', 'francisca@marceloadvogados.pt');


SELECT * FROM Tarefa;

commit;