select * from grauproficiencia;

select * from caracterct;

SELECT * FROM GrauProficiencia 
INNER JOIN CaracterCT 
ON GrauProficiencia.idGrauProficiencia = CaracterCT.grauProfMinimo 
INNER JOIN Categoria 
ON CaracterCT.codigoCategoria LIKE Categoria.codigoCategoria 
INNER JOIN Tarefa 
ON Categoria.codigoCategoria LIKE Tarefa.codigoCategoria
INNER JOIN ReconhecimentoGP
ON ReconhecimentoGP.idGrauProficiencia = GrauProficiencia.idGrauProficiencia
INNER JOIN Freelancer
ON ReconhecimentoGP.emailFreelancer LIKE Freelancer.email;

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

select * from categoria



SELECT * FROM Tarefa 
INNER JOIN Categoria 
ON Categoria.codigoCategoria LIKE Tarefa.codigoCategoria 
INNER JOIN CaracterCT 
ON CaracterCT.codigoCategoria LIKE Categoria.codigoCategoria 
INNER JOIN GrauProficiencia 
ON CaracterCT.grauProfMinimo = GrauProficiencia.idGrauProficiencia 
INNER JOIN ReconhecimentoGP 
ON GrauProficiencia.idGrauProficiencia = ReconhecimentoGP.idGrauProficiencia 
INNER JOIN Freelancer 
ON ReconhecimentoGP.emailFreelancer LIKE Freelancer.email 
WHERE freelancer.email LIKE 'rosa@gmails.com'
AND Grauproficiencia.grau IN
    ((SELECT GrauProficiencia.grau FROM GrauProficiencia
    INNER JOIN ReconhecimentoGP 
    ON ReconhecimentoGP.idGrauProficiencia = GrauProficiencia.idGrauProficiencia)
    >= 
    (SELECT GrauProficiencia.grau FROM GrauProficiencia
    INNER JOIN CaracterCT
    ON CaracterCT.grauProfMinimo = GrauProficiencia.idGrauProficiencia))
AND GrauProficiencia.designacao IN
    ((SELECT GrauProficiencia.designacao  FROM GrauProficiencia
    INNER JOIN ReconhecimentoGP 
    ON ReconhecimentoGP.idGrauProficiencia = GrauProficiencia.idGrauProficiencia)
    = 
    (SELECT GrauProficiencia.designacao FROM GrauProficiencia
    INNER JOIN CaracterCT 
    ON CaracterCT.grauProfMinimo = GrauProficiencia.idGrauProficiencia));