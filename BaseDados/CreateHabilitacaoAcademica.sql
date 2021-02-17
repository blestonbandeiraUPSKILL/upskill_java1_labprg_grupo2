CREATE OR REPLACE PROCEDURE createHabilitacaoAcademica(
    p_grau habilitacaoacademica.grau%type,
    p_designacaoCurso habilitacaoacademica.designacaocurso%type,
    p_nomeInstituicao habilitacaoacademica.nomeinstituicao%type,
    p_mediaCurso habilitacaoacademica.mediacurso%type
    )

IS

BEGIN
    
    INSERT INTO HabilitacaoAcademica
        (grau, designacaoCurso, nomeInstituicao, mediaCurso)
    VALUES
        (p_grau, p_designacaoCurso, p_nomeInstituicao, p_mediaCurso);

END;
/