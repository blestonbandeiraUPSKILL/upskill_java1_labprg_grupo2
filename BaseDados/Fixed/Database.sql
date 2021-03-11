select * from usersession;

select * from appcontext;

select * from candidatura;
delete from candidatura where idCandidatura = 42;
commit;
select * from estadocontext;

DROP PROCEDURE setState

select * from rolename;

SELECT setState('2n7nPNLjONzaL957Ycv0') FROM dual;
SELECT (((sysdate + (10/(24*60*60))) - sysdate)*(24*60*60)) FROM dual;
select (((sysdate + (10/(24*60))) - sysdate)*(24*60)) from dual;
select (sysdate/(24*60*60)) from dual
SELECT timestamp FROM appContext WHERE value LIKE 'YPUEk2rG3AQVDLrHmUA3'

select sysdate from dual;
select sysdate+(10/(24*60)) from dual;

select * from utilizador;

select * from caracterct;

update caracterct
set obrigatoria = 'obrigatoria' where idCaracterct = 29
commit;

select * from colaborador;
delete from utilizador where email like 'mariaines@marceloadvogados.pt'
SELECT * FROM Utilizador 
INNER JOIN Rolename 
ON Utilizador.idRolename = Rolename.idRolename 
WHERE email LIKE 'antonio@t4j.com'

SELECT * FROM UserSession 
INNER JOIN AppContext 
ON UserSession.idAppContext = AppContext.idAppContext 
WHERE AppContext.value LIKE 'RpsJjbIZmKB221AIsrVH'

SELECT Rolename.idRolename FROM Rolename 
INNER JOIN Utilizador 
ON Rolename.idRolename = Utilizador.idRolename 
WHERE Utilizador.email LIKE 'rosa@gmails.com'

select * from rolename

select * from usersession;
