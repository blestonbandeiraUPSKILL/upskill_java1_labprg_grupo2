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
where sysdate BETWEEN dataInicioCandidatura AND dataFimCandidatura

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

SELECT * FROM GrauProficiencia 
INNER JOIN CaracterCT 
ON CaracterCT.grauProfMinimo = GrauProficiencia.idGrauProficiencia 
INNER JOIN Categoria 
ON Categoria.codigoCategoria LIKE CaracterCT.codigoCategoria 
INNER JOIN Tarefa 
ON Categoria.codigoCategoria LIKE Tarefa.codigoCategoria 
INNER JOIN Anuncio 
ON Tarefa.referencia LIKE Anuncio.referenciaTarefa 
AND Tarefa.nifOrganizacao LIKE Tarefa.nifOrganizacao 
WHERE 
--Tarefa.referencia LIKE ? " +
--AND Tarefa.nifOrganizacao LIKE 
--AND 
sysdate BETWEEN Anuncio.dataInicioCandidatura AND Anuncio.dataFimCandidatura



select * from anuncio
delete from anuncio where idanuncio = 24
left JOIN candidatura
on anuncio.idAnuncio = candidatura.idAnuncio
where candidatura.emailfreelancer like 'rosa@gmails.com'
and anuncio.referenciatarefa like 'T_02_01'

select * from tarefa
select * from candidatura
rollback;

delete from candidatura where idcandidatura = 7
delete from anuncio where idanuncio = 39;
commit;


select * from anuncio 
where referenciaTarefa like 'T_02_01'
and nifOrganizacao Like '111222888'

update anuncio
set dataInicioSeriacao = sysdate
where idAnuncio = 22

select * from utilizador