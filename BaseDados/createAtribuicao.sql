CREATE OR REPLACE PROCEDURE createAtribuicao(
    p_nifOrganizacao Atribuicao.nifOrganizacao%type,
    p_referenciaTarefa Atribuicao.referenciaTarefa%type,
    p_emailFreelancer Atribuicao.emailFreelancer%type,
    p_idAnuncio Atribuicao.idAnuncio%type,
    p_dataInicioRealizacao Atribuicao.dataInicioRealizacao%type,
    p_idCandidatura Atribuicao.idCandidatura%type
    )

IS
    v_idAtribuicao Atribuicao.idAtribuicao%type;
    v_numeroAnual Atribuicao.numeroAnual%type;
    v_ano varchar(4);
    v_dataInicioRealizacao Atribuicao.dataInicioRealizacao%type;
    v_dataFimRealizacao Atribuicao.dataFimRealizacao%type;
    v_numeroDias integer;
    
BEGIN

    SELECT * INTO v_idAtribuicao
    FROM ( 
        SELECT max(idAtribuicao) as v_idAtribuicao
        FROM Atribuicao
        );
    
    SELECT to_char(sysdate, 'YYYY') into v_ano
    FROM dual;
    
    v_numeroAnual := v_ano || '-' || v_idatribuicao;
    
    v_dataInicioRealizacao := p_datainiciorealizacao + 1;
    
    SELECT numeroDias INTO v_numeroDias
    FROM Candidatura
    WHERE idCandidatura = p_idCandidatura;
    
    v_dataFimRealizacao := v_datainiciorealizacao + v_numeroDias;
    
    INSERT INTO Atribuicao
        (numeroAnual, nifOrganizacao, referenciaTarefa, emailFreelancer, dataInicioRealizacao, dataFimRealizacao, idAnuncio, dataAtribuicao, idCandidatura)
    VALUES
        (v_numeroAnual, p_nifOrganizacao, p_referenciaTarefa, p_emailFreelancer, v_dataInicioRealizacao, v_dataFimRealizacao, p_idAnuncio, sysdate, p_idcandidatura);
    
END;
/