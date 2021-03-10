select * from usersession;

select * from appcontext;

select * from candidatura;

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

SELECT * FROM Utilizador 
INNER JOIN Rolename 
ON Utilizador.idRolename = Rolename.idRolename 
WHERE email LIKE 'chica@gmails.com'

