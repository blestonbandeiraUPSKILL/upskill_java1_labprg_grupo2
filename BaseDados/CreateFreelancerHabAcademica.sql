CREATE OR REPLACE PROCEDURE createFreelancerHabAcademica(
    p_emailFreelancer freelancer.email%type,
    p_idHabilitacaoAcademica habilitacaoacademica.idhabilitacaoacademica%type
    )

IS
    v_countFreelancer int;
    v_countHabAcademica int;
    ex_Freelancer exception;
    ex_HabAcademica exception;

BEGIN
    
    SELECT count(*) INTO v_countFreelancer
    FROM Freelancer
    WHERE email = p_emailFreelancer;
    
    IF v_countFreelancer = 0
    THEN
        RAISE ex_Freelancer;
    END IF;
    
    SELECT count(*) INTO v_countHabAcademica
    FROM HabilitacaoAcademica
    WHERE idHabilitacaoAcademica = p_idHabilitacaoAcademica;
    
    IF v_countHabAcademica = 0
    THEN 
        RAISE ex_HabAcademica;
    END IF;
    
    
    INSERT INTO FreelancerHabAcademica
        (emailFreelancer, idHabilitacaoAcademica)
    VALUES
        (p_emailFreelancer, p_idHabilitacaoAcademica);
    
    EXCEPTION WHEN
        ex_Freelancer OR ex_HabAcademica 
    THEN    
        RAISE_APPLICATION_ERROR(-20001, 'Dados Inexistentes');
    
END;
/