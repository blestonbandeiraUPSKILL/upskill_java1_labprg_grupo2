CREATE OR REPLACE PROCEDURE createCandidatura(
    p_dataFimCandidatura candidatura.datafimcandidatura%type,
    p_valorPretendido candidatura.valorpretendido%type,
    p_numeroDias candidatura.numerodias%type,
    p_txtApresentacao candidatura.txtapresentacao%type,
    p_txtMotivacao candidatura.txtmotivacao%type,
    p_idAnuncio anuncio.idanuncio%type,
    p_emailFreelancer freelancer.email%type
    )

IS

    v_countAnuncio int;
    v_countEmailFreelancer int;
    ex_Anuncio exception;
    ex_EmailFreelancer exception;

BEGIN

    SELECT count(*) INTO v_countAnuncio
    FROM Anuncio
    WHERE idanuncio = p_idAnuncio;
    
    IF v_countAnuncio = 0
    THEN
        RAISE ex_Anuncio;
    END IF;
    
    SELECT count(*) INTO v_countEmailFreelancer
    FROM Freelancer
    WHERE email = p_emailFreelancer;
    
    IF v_countEmailFreelancer = 0
    THEN
        RAISE ex_EmailFreelancer;
    END IF;
    
    INSERT INTO Candidatura
        (dataFimCandidatura, valorPretendido, numeroDias, txtApresentacao, txtMotivacao, idAnuncio, emailFreelancer)
    VALUES
        (p_dataFimCandidatura, p_valorPretendido, p_numeroDias, p_txtApresentacao, p_txtMotivacao, p_idAnuncio, p_emailFreelancer);
    
    EXCEPTION WHEN
        ex_Anuncio OR ex_EmailFreelancer
    THEN
        RAISE_APPLICATION_ERROR(-20001, 'Dados ienxistentes');

END;
/