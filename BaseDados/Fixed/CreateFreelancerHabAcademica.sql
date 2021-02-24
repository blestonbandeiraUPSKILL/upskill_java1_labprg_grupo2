CREATE OR REPLACE PROCEDURE createFreelancerHabAcademica(
    p_emailFreelancer freelancer.email%type,
    p_idHabilitacaoAcademica habilitacaoacademica.idhabilitacaoacademica%type
    )

IS

BEGIN
           
    INSERT INTO FreelancerHabAcademica
        (emailFreelancer, idHabilitacaoAcademica)
    VALUES
        (p_emailFreelancer, p_idHabilitacaoAcademica);

END;
/