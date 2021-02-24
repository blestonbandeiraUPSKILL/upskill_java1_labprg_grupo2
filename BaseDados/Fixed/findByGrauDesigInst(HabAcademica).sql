CREATE OR REPLACE PROCEDURE findByGrauDesigInst (
    p_grau habilitacaoacademica.grau%type,
    p_designacaoCurso habilitacaoacademica.designacaocurso%type,
    p_nomeInstituicao habilitacaoacademica.nomeinstituicao%type,
    p_emailFreelancer FreelancerHabAcademica.emailFreelancer%type
    )

IS
    v_count int;
    ex_HabilitacaoAcademica exception;

BEGIN
    SELECT count(*) INTO v_count
    FROM HabilitacaoAcademica
    INNER JOIN FreelancerHabAcademica
        ON FreelancerHabAcademica.idHabilitacaoAcademica = HabilitacaoAcademica.idHabilitacaoAcademica
    INNER JOIN Freelancer
        ON FreelancerHabAcademica.emailFreelancer LIKE p_emailFreelancer
    WHERE 
        habilitacaoacademica.grau LIKE p_grau
        AND
        habilitacaoacademica.designacaocurso LIKE p_designacaoCurso
        AND
        habilitacaoacademica.nomeinstituicao LIKE p_nomeInstituicao;
    
    IF v_count != 0
    THEN
        RAISE ex_HabilitacaoAcademica;
    END IF;
    
    EXCEPTION WHEN
        ex_HabilitacaoAcademica
    THEN
        RAISE_APPLICATION_ERROR(-20001, 'Habilitação com estes valores já existe.');
    
        

END;
/
