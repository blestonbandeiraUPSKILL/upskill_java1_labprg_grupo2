CREATE OR REPLACE PROCEDURE createHabilitacaoAcademica(
    p_grau habilitacaoacademica.grau%type,
    p_designacaoCurso habilitacaoacademica.designacaocurso%type,
    p_nomeInstituicao habilitacaoacademica.nomeinstituicao%type,
    p_mediaCurso habilitacaoacademica.mediacurso%type, 
    p_emailFreelancer freelancer.email%type
    )

IS
    v_idHabilitacaoAcademica habilitacaoacademica.idhabilitacaoacademica%type;
    
BEGIN
    
    INSERT INTO HabilitacaoAcademica
        (grau, designacaoCurso, nomeInstituicao, mediaCurso)
    VALUES
        (p_grau, p_designacaoCurso, p_nomeInstituicao, p_mediaCurso)
    RETURNING idHabilitacaoAcademica 
    INTO v_idHabilitacaoAcademica;
    
    INSERT INTO FreelancerHabAcademica
        (emailFreelancer, idHabilitacaoAcademica)
    VALUES
        (p_emailFreelancer, v_idHabilitacaoAcademica);

END;
/