CREATE OR REPLACE PROCEDURE createCandidatura(
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
        (valorPretendido, numeroDias, txtApresentacao, txtMotivacao, idAnuncio, emailFreelancer, dataCandidatura, dataEdicaoCandidatura)
    VALUES
        (p_valorPretendido, p_numeroDias, p_txtApresentacao, p_txtMotivacao, p_idAnuncio, p_emailFreelancer, trunc(sysdate), sysdate);
    

END;
/