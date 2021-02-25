INSERT INTO Utilizador
    (email, nome, password, rolename)
VALUES
    ('jose@marceloadvogados.pt', 'José Fontes', 'yzkrEbsU5y', 'gestor');

INSERT INTO Utilizador
    (email, nome, password, rolename)
VALUES
    ('francisca@marceloadvogados.pt', 'Francisca Cardoso', 'jaVHPVQ6Q8', 'colaborador');

INSERT INTO Utilizador
    (email, nome, password, rolename)
VALUES
    ('tomas@marceloadvogados.pt', 'Tomás Fonseca', '2r9CUb4Dqj', 'colaborador');

INSERT INTO Utilizador
    (email, nome, password, rolename)
VALUES
    ('sofia@inesengenharia.com', 'Sofia Barcelos', 'l2pie2XkY2', 'gestor');

INSERT INTO Utilizador
    (email, nome, password, rolename)
VALUES
    ('matilde@inesengenharia.com', 'Matilde Oliveira', 'Rzs6F52fYb', 'colaborador');
    
INSERT INTO Utilizador
    (email, nome, password, rolename)
VALUES
    ('eduardo@inesengenharia.com', 'Eduardo Lima', '2r9CUb4Dqj', 'colaborador');
    


-- Freelancers

INSERT INTO Utilizador
    (email, nome, password, rolename)
VALUES
    ('madalena@gmails.com', 'Madalena Rodrigues', 'teste123', 'freelancer');

INSERT INTO Utilizador
    (email, nome, password, rolename)
VALUES
    ('antonio@gmails.com', 'António Cardoso', 'teste123', 'freelancer');

INSERT INTO Utilizador
    (email, nome, password, rolename)
VALUES
    ('afonso@gmails.com', 'Afonso Curado', 'teste123', 'freelancer');

INSERT INTO Utilizador
    (email, nome, password, rolename)
VALUES
    ('alberto@gmails.com', 'Alberto Dias', 'teste123', 'freelancer');

INSERT INTO Utilizador
    (email, nome, password, rolename)
VALUES
    ('carolina@gmails.com', 'Carolina Dutra', 'teste123', 'freelancer');

INSERT INTO Utilizador
    (email, nome, password, rolename)
VALUES
    ('lourdes@gmails.com', 'Lourdes Estrada', 'teste123', 'freelancer');

INSERT INTO Utilizador
    (email, nome, password, rolename)
VALUES
    ('isabel@gmails.com', 'Isabel Feijó', 'teste123', 'freelancer');

INSERT INTO Utilizador
    (email, nome, password, rolename)
VALUES
    ('arnaldo@gmails.com', 'Arnaldo Fraga', 'teste123', 'freelancer');

INSERT INTO Utilizador
    (email, nome, password, rolename)
VALUES
    ('alexandre@gmails.com', 'Alexandre Gama', 'teste123', 'freelancer');

INSERT INTO Utilizador
    (email, nome, password, rolename)
VALUES
    ('paulo@gmails.com', 'Paulo Guerra', 'teste123', 'freelancer');

INSERT INTO Utilizador
    (email, nome, password, rolename)
VALUES
    ('ana@gmails.com', 'Ana Ávila', 'teste123', 'freelancer');

INSERT INTO Utilizador
    (email, nome, password, rolename)
VALUES
    ('bruno@gmails.com', 'Bruno Almeida', 'teste123', 'freelancer');

INSERT INTO Utilizador
    (email, nome, password, rolename)
VALUES
    ('joao@gmails.com', 'João Assis', 'teste123', 'freelancer');

INSERT INTO Utilizador
    (email, nome, password, rolename)
VALUES
    ('catarina@gmails.com', 'Catarina Barreiros', 'teste123', 'freelancer');

INSERT INTO Utilizador
    (email, nome, password, rolename)
VALUES
    ('daniel@gmails.com', 'Daniel Borja', 'teste123', 'freelancer');

INSERT INTO Utilizador
    (email, nome, password, rolename)
VALUES
    ('fernando@gmails.com', 'Fernando Brito', 'teste123', 'freelancer');
INSERT INTO Utilizador
    (email, nome, password, rolename)
VALUES
    ('joana@gmails.com', 'Joana Caiado', 'teste123', 'freelancer');

INSERT INTO Utilizador
    (email, nome, password, rolename)
VALUES
    ('maria@gmails.com', 'Maria Carneiro', 'teste123', 'freelancer');

INSERT INTO Utilizador
    (email, nome, password, rolename)
VALUES
    ('goncalo@gmails.com', 'Gonçalo Castilho', 'teste123', 'freelancer');

INSERT INTO Utilizador
    (email, nome, password, rolename)
VALUES
    ('matias@gmails.com', 'Matias Couto', 'teste123', 'freelancer');

INSERT INTO Utilizador
    (email, nome, password, rolename)
VALUES
    ('jorge@gmails.com', 'Jorge Henriques', 'teste123', 'freelancer');

INSERT INTO Utilizador
    (email, nome, password, rolename)
VALUES
    ('julio@gmails.com', 'Júlio Lacerda', 'teste123', 'freelancer');

INSERT INTO Utilizador
    (email, nome, password, rolename)
VALUES
    ('emanuel@gmails.com', 'Emanuel Lima', 'teste123', 'freelancer');

INSERT INTO Utilizador
    (email, nome, password, rolename)
VALUES
    ('rosa@gmails.com', 'Rosa Macedo', 'teste123', 'freelancer');

INSERT INTO Utilizador
    (email, nome, password, rolename)
VALUES
    ('rita@gmails.com', 'Rita Medeiros', 'teste123', 'freelancer');

INSERT INTO Utilizador
    (email, nome, password, rolename)
VALUES
    ('francisca@gmails.com', 'Francisca Morais', 'teste123', 'freelancer');

INSERT INTO Utilizador
    (email, nome, password, rolename)
VALUES
    ('carla@gmails.com', 'Carla Muniz', 'teste123', 'freelancer');

INSERT INTO Utilizador
    (email, nome, password, rolename)
VALUES
    ('armando@gmails.com', 'Armando Novais', 'teste123', 'freelancer');

INSERT INTO Utilizador
    (email, nome, password, rolename)
VALUES
    ('jose@gmails.com', 'José Ferreira', 'teste123', 'freelancer');

INSERT INTO Utilizador
    (email, nome, password, rolename)
VALUES
    ('joaquim@gmails.com', 'Joaquim Rodrigues', 'teste123', 'freelancer');

commit;

select * from utilizador;
delete from utilizador where nome like 'josé fontes';