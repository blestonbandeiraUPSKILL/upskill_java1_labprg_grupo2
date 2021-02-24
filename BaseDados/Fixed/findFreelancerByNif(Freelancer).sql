CREATE OR REPLACE PROCEDURE findFreelancerByNif(
    p_nif freelancer.nif%type
    )

IS
    v_count int;
    ex_Freelancer exception;
    
BEGIN
    SELECT count(*) INTO v_count
    FROM Freelancer
    WHERE nif LIKE p_nif;
    
    IF v_count != 0
    THEN
        RAISE ex_Freelancer;
    END IF;
    
    EXCEPTION WHEN
        ex_Freelancer
    THEN
        RAISE_APPLICATION_ERROR(-20001, 'Freelancer já existe.');

END;
/