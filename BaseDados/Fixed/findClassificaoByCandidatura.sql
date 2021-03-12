create or replace PROCEDURE findClassificacaoByCandidatura(
    p_idCandidatura Classificacao.idCandidatura%type
    )

IS
    v_count int;
    ex_Candidatura exception;

BEGIN

    SELECT count(*) INTO v_count
    FROM Candidatura
    WHERE 
        idCandidatura = p_idCandidatura;

    IF v_count = 0
    THEN
        RAISE ex_Candidatura;
    END IF;

    EXCEPTION WHEN
        ex_Candidatura
    THEN
        RAISE_APPLICATION_ERROR(-20001, 'Candidatura não existe.');

END;