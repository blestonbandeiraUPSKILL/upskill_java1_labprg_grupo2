CREATE OR REPLACE PROCEDURE deleteCandidatura(
    p_idCandidatura candidatura.idcandidatura%type
    )

IS
    v_valorPretendido candidatura.valorpretendido%type;
    v_numeroDias candidatura.numerodias%type;
    v_txtApresentacao candidatura.txtapresentacao%type;
    v_txtMotivacao candidatura.txtmotivacao%type;
    v_idAnuncio candidatura.idanuncio%type;
    v_emailFreelancer candidatura.emailfreelancer%type;
    v_dataCandidatura candidatura.datacandidatura%type;
    v_dataEdicaoCandidatura candidatura.dataedicaocandidatura%type;

BEGIN

    SELECT valorpretendido, numeroDias, txtApresentacao, txtMotivacao,
    idAnuncio, emailFreelancer, dataCandidatura, dataEdicaoCandidatura
    INTO v_valorPretendido, v_numeroDias, v_txtApresentacao, v_txtMotivacao, 
    v_idAnuncio, v_emailFreelancer, v_dataCandidatura, v_dataEdicaoCandidatura
    FROM Candidatura 
    WHERE idCandidatura = p_idCandidatura;
    
    INSERT INTO CandidaturaApagada
    (idCandidatura, valorpretendido, numeroDias, txtApresentacao, txtMotivacao,
    idAnuncio, emailFreelancer, dataCandidatura, dataEdicaoCandidatura)
    VALUES
    (p_idCandidatura, v_valorPretendido, v_numeroDias, v_txtApresentacao, v_txtMotivacao, 
    v_idAnuncio, v_emailFreelancer, v_dataCandidatura, v_dataEdicaoCandidatura);
    
    DELETE 
    FROM Candidatura
    WHERE idCandidatura = p_idCandidatura;
    

END;
/