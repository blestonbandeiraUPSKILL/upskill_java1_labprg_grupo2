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


BEGIN

    INSERT INTO Candidatura
        (dataFimCandidatura, valorPretendido, numeroDias, txtApresentacao, txtMotivacao, idAnuncio, emailFreelancer)
    VALUES
        (p_dataFimCandidatura, p_valorPretendido, p_numeroDias, p_txtApresentacao, p_txtMotivacao, p_idAnuncio, p_emailFreelancer);
    

END;
/