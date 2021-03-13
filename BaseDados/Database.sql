SELECT * FROM Tarefa
LEFT JOIN Anuncio 
ON Tarefa.referencia LIKE Anuncio.referenciaTarefa 
AND Tarefa.nifOrganizacao LIKE Anuncio.nifOrganizacao 
WHERE Anuncio.referenciaTAREFA IS NULL AND Anuncio.nifOrganizacao IS NULL 
--AND Tarefa.referencia LIKE 'EP_01'
AND Tarefa.nifOrganizacao LIKE '111222666'
AND Tarefa.emailColaborador LIKE 'francisca@marceloadvogados.pt'

select * from tarefa

select * from anuncio

SELECT * FROM Tarefa
INNER JOIN Anuncio 
ON Tarefa.referencia LIKE Anuncio.referenciaTarefa 
AND Tarefa.nifOrganizacao LIKE Anuncio.nifOrganizacao 
WHERE tarefa.referencia LIKE 'EP_01'
AND tarefa.nifOrganizacao LIKE '111222666'
AND Tarefa.emailColaborador LIKE 'francisca@marceloadvogados.pt'

SELECT * FROM Tarefa INNER JOIN Anuncio
ON Tarefa.referencia LIKE Anuncio.referenciaTarefa 
AND Tarefa.nifOrganizacao LIKE Anuncio.nifOrganizacao