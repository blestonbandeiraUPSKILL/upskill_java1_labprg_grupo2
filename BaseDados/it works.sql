select * from grauproficiencia;

select * from caracterct;

SELECT * FROM GrauProficiencia 
INNER JOIN CaracterCT 
ON GrauProficiencia.idGrauProficiencia = CaracterCT.grauProfMinimo 
INNER JOIN Categoria 
ON CaracterCT.codigoCategoria LIKE Categoria.codigoCategoria 
INNER JOIN Tarefa 
ON Categoria.codigoCategoria LIKE Tarefa.codigoCategoria;

select * from categoria;

select * from freelancer;

Select * from grauproficiencia
inner join reconhecimentogp
on grauproficiencia.idgrauproficiencia = reconhecimentogp.idgrauproficiencia
inner join freelancer
on reconhecimentogp.emailfreelancer LIKE freelancer.email
inner join caracterct
on caracterct.grauprofminimo = grauproficiencia.idgrauproficiencia
inner join categoria 
on caracterct.codigocategoria LIKE categoria.codigocategoria
inner join tarefa
on categoria.codigocategoria LIKE tarefa.codigocategoria
WHERE freelancer.email LIKE 'rosa@gmails.com'